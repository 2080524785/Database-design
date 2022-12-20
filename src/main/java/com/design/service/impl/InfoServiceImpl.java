package com.design.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.design.dao.InfoDao;
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
    public JSONObject getDataBookBorrow(String name, String pub) throws ParseException {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar d = Calendar.getInstance();
        d.setTime(beginDate);
        Boolean flag=name==null&&pub==null?false:true;
        List<Info> infoList;
        if(!flag){
            infoList = infoDao.getAllBookDataBorrow();
        }else{
            infoList = infoDao.getOneBookDataBorrow(name,pub);
        }
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
    public JSONObject getDataStuBorrow(String sno) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar d = Calendar.getInstance();
        d.setTime(beginDate);
        Boolean flag=sno==null?false:true;
        List<Info> infoList;
        if(!flag){
            infoList = infoDao.getAllStuDataBorrow();
        }else{
            infoList = infoDao.getOneStuDataBorrow(sno);
        }
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
        List<Info.BookRank> bookRankList = infoDao.getYearRankBookBorrow();
        return bookRankList;
    }
    @Override
    public List<Info.BookRank> getRankBookBorrowOneMonth(){
        List<Info.BookRank> bookRankList = infoDao.getMonthRankBookBorrow();
        return bookRankList;
    }
    @Override
    public List<Info.BookRank> getRankBookBorrowOneWeek(){
        List<Info.BookRank> bookRankList = infoDao.getWeekRankBookBorrow();
        return bookRankList;
    }
    @Override
    public List<Info.BookRank> getRankBookBorrowOneDay(){
        List<Info.BookRank> bookRankList = infoDao.getDayRankBookBorrow();
        return bookRankList;
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
