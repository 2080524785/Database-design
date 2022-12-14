package com.design.controller;

import com.design.domain.*;
import com.design.service.BookService;
import com.design.service.BorrowService;
import com.design.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

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
        List<Object> borrowList = Collections.singletonList(borrowService.getAll());
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
    public Result StudentReturn(@RequestBody Borrow borrow){
    return  null;
    }

}
