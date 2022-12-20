package com.design.dao;

import com.design.domain.Book;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuantityDao {

    @Select(" <script>" +
            " SELECT * FROM Borrow_num " +
            " <where> 1=1 " +
            " <if test=\" name !=null \" >  AND name LIKE concat('%',#{name},'%')</if> " +
            " <if test=\"  pub!=null \" >  AND pub LIKE concat('%',#{pub},'%')</if> " +
            " <if test=\"  time!=null \" >  AND YEAR(time) =#{time}</if> " +
            " </where>" +
            " </script>"
    )

    @Select("select * from Borrow_num")
    public List<Book.BookNum> getBorrowInfo();
}
