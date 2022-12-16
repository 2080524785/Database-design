package com.design.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.design.domain.Result;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/Info")
public class InfoController {

    @PostMapping("/page")
    public Result BookInfo(@RequestBody Map<String, JSONObject> param){
        JSONObject sort = param.get("order");
        JSONObject page=param.get("page");
        PageHelper.startPage(page.getInteger("offset"), page.getInteger("limit"),sort.isEmpty()?"":sort.getString("orderProp")+" "+sort.getString("orderAsc"));
        return null;

    }
}
