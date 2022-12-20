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
    public JSONObject getDataBorrow(String name, String pub) throws ParseException {
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
    public List<Info.BookRank> getRankBookBorrowOneYear(){
        List<Info.BookRank> bookRankList = infoDao.getRankBookBorrow();
        return bookRankList;
    }
}
