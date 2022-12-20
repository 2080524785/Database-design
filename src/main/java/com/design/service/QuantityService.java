package com.design.service;

import com.alibaba.fastjson.JSONObject;
import com.design.domain.Book;

import java.util.List;

public interface QuantityService {

    /**
     *查询某种图书数量信息
     * @param
     * @return
     */
    public List<Book.BookNum> getBorrowInfo();
}
