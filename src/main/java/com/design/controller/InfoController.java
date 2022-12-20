package com.design.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.design.domain.Borrow;
import com.design.domain.Code;
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
    // 获得一个长度为365的列表，对应着日期和数据，用作画图处理
    // 获得所有书或者某中书的每日借书情况
    @PostMapping("/book/data")
    public Result BookInfoEveryDay(@RequestBody JSONObject param) throws ParseException {
        JSONObject data;
        if(param.isEmpty()){
            data=infoService.getDataBorrow(null,null);
        }else{
            data=infoService.getDataBorrow(param.getString("name"),param.getString("pub"));
        }
        Integer code = ((List<Integer>)data.get("num")).size()==365? Code.GET_OK:Code.GET_ERR;
        String msg = ((List<Integer>)data.get("num")).size()==365? "查询结果成功！":"查询结果失败！";
        return new Result(code,data,msg);
    }

}
