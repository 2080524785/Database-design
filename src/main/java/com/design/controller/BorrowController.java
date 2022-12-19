package com.design.controller;

import com.alibaba.fastjson.JSONObject;
import com.design.domain.*;
import com.design.service.BookService;
import com.design.service.BorrowService;
import com.design.service.StudentService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/pageInfo")
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

    @PostMapping("/page")
    public Result getStudent(@RequestBody Map<String,JSONObject> param){
        JSONObject page=param.get("page");
        JSONObject sort=param.get("order");
        String order = sort.isEmpty() ? "" : sort.getString("orderProp")+" "+(sort.getBoolean("orderAsc").booleanValue()?"asc":"desc");
        String sno = (String) param.get("query").get("sno");
        Student student = studentService.getBySno(sno);
        // 获得该学生 目前借阅书籍  SN,id,name,time,pub,locate,borrow_time, 按照借出时间排序，升序
        PageHelper.offsetPage(page.getInteger("offset"), page.getInteger("limit"));
        PageHelper.orderBy(order);
        List<Book.BookBorrow> bookBorrowList= borrowService.getBySnoBorrow(sno);
        PageInfo<Book.BookBorrow> bookBorrowPageInfo = new PageInfo<>(bookBorrowList);
        Integer code = student!=null? Code.GET_OK:Code.GET_ERR;
        String msg = student !=null? "查询结果成功！":"查询结果失败，没有该学生！";
        JSONObject data = new JSONObject();
        data.put("records",bookBorrowList);
        data.put("currentPage",bookBorrowPageInfo.getPageNum());
        data.put("pageSize",bookBorrowPageInfo.getPageSize());
        data.put("total",bookBorrowPageInfo.getTotal());
        return new Result(code,data,msg);
    }



    // 学生借书
    @PostMapping("/add")
    public Result StudentBorrow(@RequestBody Map<String,Object> params) {
        Boolean flag = borrowService.insertBorrow((Integer) params.get("id"),(String) params.get("sno"));
        String msg = flag? "借书成功！":"借书失败！";
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,null, msg);
    }

    // 还书，相当于从列表中删除借书信息
    @PostMapping("/delete")
    public Result StudentReturn(@RequestParam("SN") Integer SN){
        Boolean flag = borrowService.updateReturn(SN);
        String msg = flag? "还书成功！":"还书失败！";
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,null, msg);
    }
    @PostMapping("/batchDelete")
    public Result StudentReturnList(@RequestBody Map<String,List<Integer>> params){
        List<Integer> SNList = params.get("SNs");
        Boolean flag = borrowService.updateReturnList(SNList);
        String msg = flag? "还书成功！":"还书失败！";
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,null, msg);
    }

    // 续借
    @PostMapping("/rebor")
    public Result StudentReBorrow(@RequestParam(value = "SN") Integer SN){
        Boolean flag = borrowService.reBorrowBook(SN);
        String msg = flag? "更新结果成功！":"更新结果失败！";
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,null, msg);
    }
    @PostMapping("/batchRebor")
    public Result StudentReBorrow(@RequestBody Map<String,List<Integer>> params){
        List<Integer> SNList = params.get("ids");
        Boolean flag = borrowService.reBorrowBookList(SNList);
        String msg = flag? "更新结果成功！":"更新结果失败！";
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,null, msg);
    }

}
