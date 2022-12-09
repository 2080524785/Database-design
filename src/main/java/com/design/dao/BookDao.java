package com.design.dao;

import com.design.domain.Book;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookDao {

    @Select("select * from Book_info;")
    public List<Book> getAll();
}
