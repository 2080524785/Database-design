package com.design.dao;

import com.design.domain.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BookDao {

    @Select("select * from Book_info;")
    public List<Book> getAll();


    @Select("select * from Book_info where id=#{id};")
    public Book getById(Integer id);

    @Delete("delete from Book_info where id=#{id};")
    public int deleteById(Integer id);

    @Insert("insert into Book_info (name,time,pub,locate) values(#{name},#{time},#{pub},#{locate})")
    public int insertBook(Book book);

    @Update("update Book_info set name = #{name}, time = #{time}, pub = #{pub}, locate=#{locate} where id = #{id}")
    public int updateBook(Book book);

    @Select("SELECT * from Book_info where name like '%#{str}%' or pub LIKE '%#{str}%' or locate LIKE '%#{str}%' or id LIKE '%#{str}%' or time LIKE '%#{str}%';")
    public List<Book> getFuzzySearch(String str);



}
