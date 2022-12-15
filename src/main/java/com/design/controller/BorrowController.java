package com.design.controller;

import com.alibaba.fastjson.JSONObject;
import com.design.domain.*;
import com.design.service.BookService;
import com.design.service.BorrowService;
import com.design.service.StudentService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private BookService bookService;

    @GetMapping
    public Result getAll(){
        List<Borrow> borrowList = borrowService.getAll();
        Integer code = borrowList!=null? Code.GET_OK:Code.GET_ERR;
        String msg = borrowList !=null? "查询结果成功！":"查询结果失败，未找到该数据！";
        return new Result(code,borrowList,msg);
    }

    @GetMapping("/noreturn")
    public Result getBorrow(){
        List<Borrow> borrowList = borrowService.getBorrow();
        Integer code = borrowList!=null? Code.GET_OK:Code.GET_ERR;
        String msg = borrowList !=null? "查询结果成功！":"查询结果失败，未找到该数据！";
        return new Result(code,borrowList,msg);
    }

    @GetMapping("/return")
    public Result getReturn(){
        List<Borrow> borrowList = borrowService.getReturn();
        Integer code = borrowList!=null? Code.GET_OK:Code.GET_ERR;
        String msg = borrowList !=null? "查询结果成功！":"查询结果失败，未找到该数据！";
        return new Result(code,borrowList,msg);
    }

    @GetMapping("/{sno}")
    public Result getStudent(@PathVariable String sno){
        Student student = studentService.getBySno(sno);
        List<Borrow> borrowList = borrowService.getBySnoBorrowOverExcept(sno);
        List<Book> bookList = borrowService.getAllBookNoBorrow();
        Integer code = student!=null? Code.GET_OK:Code.GET_ERR;
        String msg = student !=null? "查询结果成功！":"查询结果失败，未找到该数据！";
        int exception = borrowList.size();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Student",student);
//        传递可借书籍数据
        jsonObject.put("book",bookList);
        // 传递超期数据
        jsonObject.put("OverExcept",exception);
        if(exception>0){
            jsonObject.put("OverExceptList",borrowList);
        }
        return new Result(code,jsonObject,msg);
    }

    @PostMapping("/{sno}/bor/list")
    public Result StudentBorrow(@RequestBody List<Book> bookList, @PathVariable String sno) {
        Boolean flag = borrowService.insertBorrowList(bookList, sno);
        String msg = flag? "保存结果成功！":"保存结果失败！";
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,null, msg);
    }
    @PostMapping("/{sno}/bor")
    public Result StudentBorrow(@RequestBody Book book, @PathVariable String sno) {
        Boolean flag = borrowService.insertBorrow(book, sno);
        String msg = flag? "保存结果成功！":"保存结果失败！";
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,null, msg);
    }
    @PutMapping("/{sno}/ret")
    public Result StudentReturn(@RequestBody Borrow.BorrowNoReturn borrowNoReturn){
        Boolean flag = borrowService.updateReturn(borrowNoReturn);
        String msg = flag? "更新结果成功！":"更新结果失败！";
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,null, msg);
    }
    @PutMapping("/{sno}/ret/list")
    public Result StudentReturn(@RequestBody List<Borrow.BorrowNoReturn> borrowNoReturnList){
        Boolean flag = borrowService.updateReturnList(borrowNoReturnList);
        String msg = flag? "更新结果成功！":"更新结果失败！";
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,null, msg);
    }

    @PutMapping("/{sno}/rebor")
    public Result StudentReBorrow(@RequestBody Borrow.BorrowNoReturn borrowNoReturn){
        Boolean flag = borrowService.reBorrowBook(borrowNoReturn);
        String msg = flag? "更新结果成功！":"更新结果失败！";
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,null, msg);
    }
    @PutMapping("/{sno}/rebor/list")
    public Result StudentReBorrow(@RequestBody List<Borrow.BorrowNoReturn> borrowNoReturnList){
        Boolean flag = borrowService.reBorrowBookList(borrowNoReturnList);
        String msg = flag? "更新结果成功！":"更新结果失败！";
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,null, msg);
    }

}
