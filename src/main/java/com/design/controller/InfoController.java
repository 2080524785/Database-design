package com.design.controller;


import com.alibaba.fastjson.JSONObject;
import com.design.domain.*;
import com.design.service.BorrowService;
import com.design.service.InfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private BorrowService borrowService;
    @Autowired
    private InfoService infoService;


    //查询图书的数量信息，库存量等等
    @PostMapping("/book/num")
    public Result getBookBorrowInfo(@RequestBody Map<String, JSONObject> param) throws ParseException {
        JSONObject page=param.get("page");
        JSONObject sort=param.get("order");
        String order = sort.isEmpty() ? "" : sort.getString("orderProp")+" "+(sort.getBoolean("orderAsc") ?"asc":"desc");
        PageHelper.offsetPage(page.getInteger("offset"), page.getInteger("limit"));
        PageHelper.orderBy(order);
        List<Book.BookNum> bookNumList = infoService.getBookBorrowInfo(param.get("query"));
        //  BookNum 类中包含  借出量 总借出量 库存量 总量
        PageInfo<Book.BookNum> borrowNumPageInfo = new PageInfo<>(bookNumList);
        Integer code = bookNumList!=null ? Code.GET_OK : Code.GET_ERR;
        String msg = bookNumList!=null ? "查询结果成功" : "查询结果失败";

        JSONObject data = new JSONObject();
        data.put("records",borrowNumPageInfo.getList());
        data.put("currentPage",borrowNumPageInfo.getPageNum());
        data.put("pageSize",borrowNumPageInfo.getPageSize());
        data.put("total",borrowNumPageInfo.getTotal());

        return new Result(code,data,msg);
    }
    //查询学生的显示信息
    @PostMapping("/stu/num")
    public Result getStuBorrowInfo(@RequestBody Map<String, JSONObject> param) {
        JSONObject page=param.get("page");
        JSONObject sort=param.get("order");
        String order = sort.isEmpty() ? "" : sort.getString("orderProp")+" "+(sort.getBoolean("orderAsc") ?"asc":"desc");
        PageHelper.offsetPage(page.getInteger("offset"), page.getInteger("limit"));
        PageHelper.orderBy(order);
        List<Student.StudentInfo> studentInfoList = infoService.getStuBorrowInfo(param.get("query"));
        //  BookNum 类中包含  借出量 总借出量 库存量 总量
        PageInfo<Student.StudentInfo> borrowNumPageInfo = new PageInfo<>(studentInfoList);
        Integer code = studentInfoList!=null ? Code.GET_OK : Code.GET_ERR;
        String msg = studentInfoList!=null ? "查询结果成功" : "查询结果失败";

        JSONObject data = new JSONObject();
        data.put("records",borrowNumPageInfo.getList());
        data.put("currentPage",borrowNumPageInfo.getPageNum());
        data.put("pageSize",borrowNumPageInfo.getPageSize());
        data.put("total",borrowNumPageInfo.getTotal());

        return new Result(code,data,msg);
    }



    // 获得一个长度为365的列表，对应着日期和数据，用作画图处理
    // 获得所有书或者某中书的每日借书情况
    @GetMapping("/book/data")
    public Result BookBorrowInfoEveryDay() throws ParseException {
        JSONObject data=infoService.getDataBookBorrow();
        Integer code = data.get("num")!=null? Code.GET_OK:Code.GET_ERR;
        String msg = data.get("num")!=null? "查询结果成功！":"查询结果失败！";
        return new Result(code,data,msg);
    }
    // 获得一个长度为365的列表，对应着日期和数据，用作画图处理
    // 获得所有学生或者某个学生的每日借书情况
    @GetMapping("/stu/data")
    public Result StuBorrowInfoEveryDay(@RequestBody JSONObject param) throws ParseException {
        List<Long[]> data=infoService.getDataStuBorrow();
        Integer code = data!=null? Code.GET_OK:Code.GET_ERR;
        String msg = data!=null? "查询结果成功！":"查询结果失败！";
        return new Result(code,data,msg);
    }

    // 获得图书借出量排名以及图书名字 借出量  rank name num
    @GetMapping("/book/rank")
    public Result BookBorrowInfoYearRank(@RequestParam(value="value") String parma) {
        List<Info.BookRank> bookRankList=null;
        if(Objects.equals(parma, "year")){
            bookRankList = infoService.getRankBookBorrowOneYear();
        }else if(Objects.equals(parma, "month")){
            bookRankList = infoService.getRankBookBorrowOneMonth();
        }else if(Objects.equals(parma, "week")){
            bookRankList = infoService.getRankBookBorrowOneWeek();
        }else if(Objects.equals(parma, "day")){
            bookRankList = infoService.getRankBookBorrowOneDay();
        }
        Integer code = bookRankList!=null? Code.GET_OK:Code.GET_ERR;
        String msg = bookRankList!=null? "查询结果成功！":"查询结果失败！";
        return new Result(code,bookRankList,msg);
    }

    // 获得注册学生总数
    @GetMapping("/sum/stu")
    public Result SumStuInfo() {
        Integer num = infoService.getSumStu();
        Integer code = num!=null? Code.GET_OK:Code.GET_ERR;
        String msg = num!=null? "查询结果成功！":"查询结果失败！";
        return new Result(code,num,msg);
    }
    // 获得书籍总数
    @GetMapping("/sum/book")
    public Result SumBookInfo() {
        Integer num = infoService.getSumBook();
        Integer code = num!=null? Code.GET_OK:Code.GET_ERR;
        String msg = num!=null? "查询结果成功！":"查询结果失败！";
        return new Result(code,num,msg);
    }
    // 获得借阅记录总数
    @GetMapping("/sum/borrow")
    public Result SumBorrowInfo() {
        Integer num = infoService.getSumBorrow();
        Integer code = num!=null? Code.GET_OK:Code.GET_ERR;
        String msg = num!=null? "查询结果成功！":"查询结果失败！";
        return new Result(code,num,msg);
    }
    // 获得累计罚金总数
    @GetMapping("/sum/fine")
    public Result SumFineInfo() {
        Integer num = infoService.getSumFine();
        Integer code = num!=null? Code.GET_OK:Code.GET_ERR;
        String msg = num!=null? "查询结果成功！":"查询结果失败！";
        return new Result(code,num,msg);
    }
    // 获得今天罚金总数
    @GetMapping("/today/fine")
    public Result TodayFineInfo() {
        Integer num = infoService.getTodayFine();
        Integer code = num!=null? Code.GET_OK:Code.GET_ERR;
        String msg = num!=null? "查询结果成功！":"查询结果失败！";
        return new Result(code,num,msg);
    }



}
