package com.design.controller;

import com.alibaba.fastjson.JSONObject;
import com.design.domain.Book;
import com.design.domain.Borrow;
import com.design.domain.Code;
import com.design.domain.Result;
import com.design.service.QuantityService;
import com.design.service.impl.QuantityServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book/bookNum")
public class QuantityController {

    @Autowired
    private QuantityService quantityService;

    //查询图书的数量信息
    @PostMapping("/num")
    public Result getBorrowInfo(@RequestBody Map<String, JSONObject> param) {
        JSONObject page=param.get("page");
        JSONObject sort=param.get("order");
        String order = sort.isEmpty() ? "" : sort.getString("orderProp")+" "+(sort.getBoolean("orderAsc").booleanValue()?"asc":"desc");
        PageHelper.offsetPage(page.getInteger("offset"), page.getInteger("limit"));
        PageHelper.orderBy(order);
        List<Book.BookNum> bookNumList = quantityService.getBorrowInfo();

        PageInfo<Book.BookNum> borrowNumPageInfo = new PageInfo<>(bookNumList);
        Integer code = bookNumList!=null ? Code.GET_OK : Code.GET_ERR;
        String msg = bookNumList!=null ? "查询结果成功" : "查询结果失败";

        JSONObject data = new JSONObject();
        data.put("records",borrowNumPageInfo.getList());
        data.put("currentPage",borrowNumPageInfo.getPageNum());
        data.put("pageSize",borrowNumPageInfo.getPageSize());
        data.put("total",borrowNumPageInfo.getTotal());

        return new Result(code,bookNumList,msg);
    }
}
