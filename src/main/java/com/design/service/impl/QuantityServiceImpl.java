package com.design.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.design.dao.QuantityDao;
import com.design.domain.Book;
import com.design.service.QuantityService;
import com.design.service.impl.QuantityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityServiceImpl implements QuantityService {

    @Autowired
    private QuantityDao quantityDao;


    @Override
    public List<Book.BookNum> getBorrowInfo() {
        return quantityDao.getBorrowInfo();
    }
}
