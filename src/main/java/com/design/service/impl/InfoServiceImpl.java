package com.design.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.design.dao.InfoDao;
import com.design.domain.Book;
import com.design.domain.Info;
import com.design.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    private InfoDao infoDao;

    @Override
    public JSONObject getDataBookBorrow() throws ParseException {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar d = Calendar.getInstance();
        d.setTime(beginDate);
        List<Info> infoList = infoDao.getAllBookDataBorrow();
        List<String> date = new ArrayList<String>();
        List<Integer> num = new ArrayList<Integer>();
        for(Info info:infoList){
            date.add(info.getDate());
            num.add(info.getNumber());
        }
        JSONObject data=new JSONObject();
        data.put("date",date);
        data.put("num",num);
        return data;

    }
    @Override
    public List<Book.BookNum> getBorrowInfo(JSONObject query) {
        return infoDao.getBorrowInfo(new Book.BookNum(query));
    }

    @Override
    public JSONObject getDataStuBorrow() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar d = Calendar.getInstance();
        d.setTime(beginDate);

        List<Info> infoList = infoDao.getAllStuDataBorrow();
        List<String> date = new ArrayList<String>();
        List<Integer> num = new ArrayList<Integer>();
        for(Info info:infoList){
            date.add(info.getDate());
            num.add(info.getNumber());
        }
        JSONObject data=new JSONObject();
        data.put("date",date);
        data.put("num",num);
        return data;
    }

    @Override
    public List<Info.BookRank> getRankBookBorrowOneYear(){
        return infoDao.getYearRankBookBorrow();
    }
    @Override
    public List<Info.BookRank> getRankBookBorrowOneMonth(){
        return infoDao.getMonthRankBookBorrow();
    }
    @Override
    public List<Info.BookRank> getRankBookBorrowOneWeek(){
        return infoDao.getWeekRankBookBorrow();
    }
    @Override
    public List<Info.BookRank> getRankBookBorrowOneDay(){
        return infoDao.getDayRankBookBorrow();
    }

    @Override
    public Integer getSumStu() {
        return infoDao.getSumStu();
    }

    @Override
    public Integer getSumBook() {
        return infoDao.getSumBook();
    }

    @Override
    public Integer getSumBorrow() {
        return infoDao.getSumBorrow();
    }

    @Override
    public Integer getSumFine() {
        return infoDao.getSumFine();
    }

    @Override
    public Integer getTodayFine() {
        return infoDao.getTodayFine();
    }

}
