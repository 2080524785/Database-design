package com.design.service;

import com.alibaba.fastjson.JSONObject;
import com.design.domain.Book;
import com.design.domain.Info;
import com.design.domain.Student;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface InfoService {
    public JSONObject getDataBookBorrow() throws ParseException;
    public List<Long[]> getDataStuBorrow() throws ParseException;
    public List<Info.BookRank> getRankBookBorrowOneYear();
    public List<Info.BookRank> getRankBookBorrowOneMonth();
    public List<Info.BookRank> getRankBookBorrowOneWeek();
    public List<Info.BookRank> getRankBookBorrowOneDay();

    public Integer getSumStu();
    public Integer getSumBook();
    public Integer getSumBorrow();
    public Integer getSumFine();
    public Integer getTodayFine();
    /**
     *查询某种图书数量信息
     * @param
     * @return
     */
    public List<Book.BookNum> getBookBorrowInfo(JSONObject query) throws ParseException;
    public List<Student.StudentInfo> getStuBorrowInfo(JSONObject query);


}
