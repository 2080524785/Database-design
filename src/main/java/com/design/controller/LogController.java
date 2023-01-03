package com.design.controller;

import com.alibaba.fastjson.JSONObject;
import com.design.domain.Borrow;
import com.design.domain.Code;
import com.design.domain.Result;
import com.design.service.BorrowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private BorrowService borrowService;

    @PostMapping("/page")
    public Result getAll(@RequestBody Map<String, JSONObject> param){
        JSONObject page=param.get("page");
        JSONObject sort=param.get("order");
        String order = sort.isEmpty() ? "" : sort.getString("orderProp")+" "+(sort.getBoolean("orderAsc").booleanValue()?"asc":"desc");
        PageHelper.offsetPage(page.getInteger("offset"), page.getInteger("limit"));
        PageHelper.orderBy(order);
        List<Borrow.BorrowInfo> borrowInfoList = borrowService.getAllInfo(param.get("query"));
        PageInfo<Borrow.BorrowInfo> borrowPageInfo = new PageInfo<>(borrowInfoList);
        Integer code = borrowInfoList!=null? Code.GET_OK:Code.GET_ERR;
        String msg = borrowInfoList !=null? "查询结果成功！":"查询结果失败，未找到该数据！";
        JSONObject data = new JSONObject();
        data.put("records",borrowPageInfo.getList());
        data.put("currentPage",borrowPageInfo.getPageNum());
        data.put("pageSize",borrowPageInfo.getPageSize());
        data.put("total",borrowPageInfo.getTotal());
        return new Result(code,data,msg);
    }
}
