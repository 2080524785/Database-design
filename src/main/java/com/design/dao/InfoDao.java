package com.design.dao;

import com.design.domain.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface InfoDao {

    @Select("SELECT t.date,coalesce(u.number,0) 'number' from(SELECT subdate(CURRENT_DATE, numlist.id) AS 'date' FROM (SELECT DISTINCT x.i + y.i * 10 + z.i * 100 AS id FROM num x,num y,num z ORDER BY id) AS numlist WHERE subdate(CURRENT_DATE, numlist.id) > date_sub(CURRENT_DATE,interval 1 year)) t LEFT JOIN (SELECT DATE(Borrow.borrow_time)as date,count(1) number FROM Borrow GROUP BY DATE(Borrow.borrow_time)) u on t.date = u.date ORDER BY t.date"
    )
    public List<Map<String,Integer>> getAllBookDataBorrow();
    @Select("SELECT t.date,coalesce(u.number,0) 'number' from(SELECT subdate(CURRENT_DATE, numlist.id) AS 'date' FROM (SELECT DISTINCT x.i + y.i * 10 + z.i * 100 AS id FROM num x,num y,num z ORDER BY id) AS numlist WHERE subdate(CURRENT_DATE, numlist.id) > date_sub(CURRENT_DATE,interval 1 year)) t LEFT JOIN (SELECT DATE(Borrow.borrow_time)as date,count(1) number FROM Borrow,Book_info where name=#{name} and pub=#{pub} and Book_info.id=Borrow.id GROUP BY DATE(Borrow.borrow_time)) u on t.date = u.date ORDER BY t.date")
    public List<Map<String,Integer>> getOneBookDataBorrow(@Param("name")String name, @Param("pub")String pub);

}
