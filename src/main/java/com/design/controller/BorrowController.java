package com.design.controller;

import com.design.domain.Book;
import com.design.domain.Borrow;
import com.design.domain.Code;
import com.design.domain.Result;
import com.design.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

//    List<Book> bookList = bookService.getAll();
//    Integer code = bookList !=null? Code.GET_OK:Code.GET_ERR;
//    String msg = bookList !=null? "查询结果成功！":"查询结果失败，未找到该数据！";
//        return new Result(code,bookList,msg);
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
    public Result getStudent(){
        List<Borrow> borrowList = borrowService.getReturn();
        Integer code = borrowList!=null? Code.GET_OK:Code.GET_ERR;
        String msg = borrowList !=null? "查询结果成功！":"查询结果失败，未找到该数据！";
        return new Result(code,borrowList,msg);
    }

}
