package com.design.dao;

import com.design.domain.Book;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookDao {

    @Select("select name,count(*) from Book_info group by name")
    public List<Book> getAll();
}
