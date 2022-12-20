package com.design.dao;

import com.design.domain.Book;
import com.design.domain.Info;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface InfoDao {

    @Select("SELECT t.date,coalesce(u.number,0) 'number' from(SELECT subdate(CURRENT_DATE, numlist.id) AS 'date' FROM (SELECT DISTINCT x.i + y.i * 10 + z.i * 100 AS id FROM num x,num y,num z ORDER BY id) AS numlist WHERE subdate(CURRENT_DATE, numlist.id) > date_sub(CURRENT_DATE,interval 1 year)) t LEFT JOIN (SELECT DATE(Borrow.borrow_time)as date,count(1) number FROM Borrow GROUP BY DATE(Borrow.borrow_time)) u on t.date = u.date ORDER BY t.date")
    public List<Info> getAllBookDataBorrow();
    @Select("SELECT t.date,coalesce(u.number,0) 'number' from(SELECT subdate(CURRENT_DATE, numlist.id) AS 'date' FROM (SELECT DISTINCT x.i + y.i * 10 + z.i * 100 AS id FROM num x,num y,num z ORDER BY id) AS numlist WHERE subdate(CURRENT_DATE, numlist.id) > date_sub(CURRENT_DATE,interval 1 year)) t LEFT JOIN (SELECT DATE(Borrow.borrow_time)as date,count(1) number FROM Borrow,Book_info where name=#{name} and pub=#{pub} and Book_info.id=Borrow.id GROUP BY DATE(Borrow.borrow_time)) u on t.date = u.date ORDER BY t.date")
    public List<Info> getOneBookDataBorrow(@Param("name")String name, @Param("pub")String pub);

    @Select("SELECT t.date,coalesce(u.number,0) 'number' from(SELECT subdate(CURRENT_DATE, numlist.id) AS 'date' FROM (SELECT DISTINCT x.i + y.i * 10 + z.i * 100 AS id FROM num x,num y,num z ORDER BY id) AS numlist WHERE subdate(CURRENT_DATE, numlist.id) > date_sub(CURRENT_DATE,interval 1 year)) t LEFT JOIN (SELECT DATE(Borrow.borrow_time)as date,count(DISTINCT sno) number FROM Borrow GROUP BY DATE(Borrow.borrow_time)) u on t.date = u.date ORDER BY t.date")
    public List<Info> getAllStuDataBorrow();
    @Select("SELECT t.date,coalesce(u.number,0) 'number' from(SELECT subdate(CURRENT_DATE, numlist.id) AS 'date' FROM (SELECT DISTINCT x.i + y.i * 10 + z.i * 100 AS id FROM num x,num y,num z ORDER BY id) AS numlist WHERE subdate(CURRENT_DATE, numlist.id) > date_sub(CURRENT_DATE,interval 1 year)) t LEFT JOIN (SELECT DATE(Borrow.borrow_time)as date,count(DISTINCT sno) number FROM Borrow WHERE sno=#{sno} GROUP BY DATE(Borrow.borrow_time)) u on t.date = u.date ORDER BY t.date")
    public List<Info> getOneStuDataBorrow(String sno);

    @Select("SELECT  Book_info.name,count(*) number from Borrow,Book_info where DATE(borrow_time)>DATE_SUB(CURDATE(), INTERVAL 1 YEAR) and Book_info.id=Borrow.id GROUP BY Book_info.name,Book_info.pub,Book_info.time  ORDER BY num DESC limit 5")
    public List<Info> getYearRankBookBorrow();
    @Select("SELECT  Book_info.name,count(*) number from Borrow,Book_info where DATE(borrow_time)>DATE_SUB(CURDATE(), INTERVAL 1 MONTH) and Book_info.id=Borrow.id GROUP BY Book_info.name,Book_info.pub,Book_info.time  ORDER BY num DESC limit 5")
    public List<Info> getMonthRankBookBorrow();
    @Select("SELECT  Book_info.name,count(*) number from Borrow,Book_info where DATE(borrow_time)>DATE_SUB(CURDATE(), INTERVAL 1 WEEK) and Book_info.id=Borrow.id GROUP BY Book_info.name,Book_info.pub,Book_info.time  ORDER BY num DESC limit 5")
    public List<Info> getWeekRankBookBorrow();
    @Select("SELECT  Book_info.name,count(*) number from Borrow,Book_info where DATE(borrow_time)=CURRENT_DATE and Book_info.id=Borrow.id GROUP BY Book_info.name,Book_info.pub,Book_info.time  ORDER BY num DESC limit 5")
    public List<Info> getDayRankBookBorrow();

    @Select("SELECT count(sno) from Stu_info ")
    public Integer getSumStu();
    @Select("SELECT count(id) from Book_info ")
    public Integer getSumBook();
    @Select("SELECT count(SN) from Borrow ")
    public Integer getSumBorrow();
    @Select("SELECT sum(fine) from Borrow")
    public Integer getSumFine();
    @Select("SELECT sum(fine) from Borrow where DATE(borrow_time)=CURRENT_DATE")
    public Integer getTodayFine();


}
