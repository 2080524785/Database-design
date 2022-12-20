package com.design.service;

import com.alibaba.fastjson.JSONObject;
import com.design.domain.Info;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface InfoService {
    public JSONObject getDataBookBorrow(String name, String pub) throws ParseException;
    public JSONObject getDataStuBorrow(String sno);
    public List<Info.BookRank> getRankBookBorrowOneYear();

    public Integer getSumStu();
    public Integer getSumBook();
    public Integer getSumBorrow();
    public Integer getSumFine();
    public Integer getTodayFine();


}
