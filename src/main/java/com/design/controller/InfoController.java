package com.design.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.design.domain.Borrow;
import com.design.domain.Code;
import com.design.domain.Info;
import com.design.domain.Result;
import com.design.service.BorrowService;
import com.design.service.InfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private BorrowService borrowService;
    @Autowired
    private InfoService infoService;
    //
    // 与BorrowController里getALL方法一样，只是在前端传参里  query{"name":"bookname","pub":"bookpub"} order{"orderProp":"borrow_time","orderAsc":"false"}
    // 获得某种书(可以含有多本书)的借阅记录
    @PostMapping("/book/page")
    public Result BookInfo(@RequestBody Map<String, JSONObject> param){
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
    //
    // 在前端传参里  query{"sno":"sno"} order{"orderProp":"borrow_time","orderAsc":"false"}
    // 获得某个学生或者全部学生的借阅记录(全部学生query:{})
    @PostMapping("/stu/page")
    public Result StuInfo(@RequestBody Map<String, JSONObject> param){
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
        //  这个总数可以表示学生结束总数
        data.put("total",borrowPageInfo.getTotal());
        return new Result(code,data,msg);
    }
    // 获得一个长度为365的列表，对应着日期和数据，用作画图处理
    // 获得所有书或者某中书的每日借书情况
    @PostMapping("/book/data")
    public Result BookBorrowInfoEveryDay(@RequestBody JSONObject param) throws ParseException {
        JSONObject data;
        if(param.isEmpty()){
            data=infoService.getDataBookBorrow(null,null);
        }else{
            data=infoService.getDataBookBorrow(param.getString("name"),param.getString("pub"));
        }
        Integer code = ((List<Integer>)data.get("num")).size()==365? Code.GET_OK:Code.GET_ERR;
        String msg = ((List<Integer>)data.get("num")).size()==365? "查询结果成功！":"查询结果失败！";
        return new Result(code,data,msg);
    }
    // 获得一个长度为365的列表，对应着日期和数据，用作画图处理
    // 获得所有学生或者某个学生的每日借书情况
    @PostMapping("/stu/data")
    public Result StuBorrowInfoEveryDay(@RequestBody JSONObject param) throws ParseException {
        JSONObject data;
        if(param.isEmpty()){
            data=infoService.getDataStuBorrow(null);
        }else{
            data=infoService.getDataStuBorrow(param.getString("sno"));
        }
        Integer code = ((List<Integer>)data.get("num")).size()==365? Code.GET_OK:Code.GET_ERR;
        String msg = ((List<Integer>)data.get("num")).size()==365? "查询结果成功！":"查询结果失败！";
        return new Result(code,data,msg);
    }

    // 获得图书借出量排名以及图书名字 借出量  rank name num
    @GetMapping("/book/rank")
    public Result BookBorrowInfoYearRank(@RequestParam(value="value") String value) {
        List<Info> bookRankList=null;
        if(value=="year"){
            bookRankList = infoService.getRankBookBorrowOneYear();
        }else if( value=="month"){
            bookRankList = infoService.getRankBookBorrowOneMonth();
        }else if( value=="week"){
            bookRankList = infoService.getRankBookBorrowOneWeek();
        }else if( value=="day"){
            bookRankList = infoService.getRankBookBorrowOneDay();
        }
        Integer code = bookRankList==null? Code.GET_OK:Code.GET_ERR;
        String msg = bookRankList==null? "查询结果成功！":"查询结果失败！";
        return new Result(code,bookRankList,msg);
    }

    // 获得注册学生总数
    @GetMapping("/sum/stu")
    public Result SumStuInfo() {
        Integer num = infoService.getSumStu();
        Integer code = num==null? Code.GET_OK:Code.GET_ERR;
        String msg = num==null? "查询结果成功！":"查询结果失败！";
        return new Result(code,num,msg);
    }
    // 获得书籍总数
    @GetMapping("/sum/book")
    public Result SumBookInfo() {
        Integer num = infoService.getSumBook();
        Integer code = num==null? Code.GET_OK:Code.GET_ERR;
        String msg = num==null? "查询结果成功！":"查询结果失败！";
        return new Result(code,num,msg);
    }
    // 获得借阅记录总数
    @GetMapping("/sum/borrow")
    public Result SumBorrowInfo() {
        Integer num = infoService.getSumBorrow();
        Integer code = num==null? Code.GET_OK:Code.GET_ERR;
        String msg = num==null? "查询结果成功！":"查询结果失败！";
        return new Result(code,num,msg);
    }
    // 获得累计罚金总数
    @GetMapping("/sum/fine")
    public Result SumFineInfo() {
        Integer num = infoService.getSumFine();
        Integer code = num==null? Code.GET_OK:Code.GET_ERR;
        String msg = num==null? "查询结果成功！":"查询结果失败！";
        return new Result(code,num,msg);
    }
    // 获得今天罚金总数
    @GetMapping("/today/fine")
    public Result TodayFineInfo() {
        Integer num = infoService.getTodayFine();
        Integer code = num==null? Code.GET_OK:Code.GET_ERR;
        String msg = num==null? "查询结果成功！":"查询结果失败！";
        return new Result(code,num,msg);
    }



}
