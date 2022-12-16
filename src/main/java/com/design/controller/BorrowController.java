package com.design.controller;

import com.alibaba.fastjson.JSONObject;
import com.design.domain.*;
import com.design.service.BookService;
import com.design.service.BorrowService;
import com.design.service.StudentService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private BookService bookService;

    @PostMapping("/page")
    public Result getAll(@RequestBody Map<String,JSONObject> param){
        JSONObject page=param.get("page");
        JSONObject sort=param.get("order");
        String order = sort.isEmpty() ? "" : sort.getString("orderProp")+" "+(sort.getBoolean("orderAsc").booleanValue()?"asc":"desc");
        PageHelper.offsetPage(page.getInteger("offset"), page.getInteger("limit"));
        PageHelper.orderBy(order);
        List<Borrow> borrowList = borrowService.getAll(param.get("query"));
        PageInfo<Borrow> borrowPageInfo = new PageInfo<>(borrowList);
        Integer code = borrowList!=null? Code.GET_OK:Code.GET_ERR;
        String msg = borrowList !=null? "查询结果成功！":"查询结果失败，未找到该数据！";
        JSONObject data = new JSONObject();
        data.put("records",borrowPageInfo.getList());
        data.put("currentPage",borrowPageInfo.getPageNum());
        data.put("pageSize",borrowPageInfo.getPageSize());
        data.put("total",borrowPageInfo.getTotal());
        return new Result(code,data,msg);
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

    @PostMapping("/stuInfo")
    public Result getStudent(@RequestBody Map<String,String> params){
        Student student = studentService.getBySno(params.get("sno"));
        List<Borrow> borrowList = borrowService.getBySnoBorrowOverExcept(params.get("sno"));
        List<Book> bookList = borrowService.getAllBookNoBorrow();
        Integer code = student!=null? Code.GET_OK:Code.GET_ERR;
        String msg = student !=null? "查询结果成功！":"查询结果失败，没有该学生！";
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
