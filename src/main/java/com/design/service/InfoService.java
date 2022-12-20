package com.design.service;

import com.alibaba.fastjson.JSONObject;
import com.design.domain.Info;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface InfoService {
    public JSONObject getDataBorrow(String name, String pub) throws ParseException;

    public List<Info.BookRank> getRankBookBorrowOneYear();

}
