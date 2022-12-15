package com.design.dao;

import com.design.domain.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BookDao {

    @Select({" <script>" +
            " SELECT * FROM Book_info " +
            " <where> 1=1 " +
            " <if test=\" id !=null  \" >  AND id LIKE concat('%',#{id},'%')</if> " +
            " <if test=\" name !=null \" >  AND name LIKE concat('%',#{name},'%')</if> " +
            " <if test=\"  time!=null \" >  AND time =#{time}</if> " +
            " <if test=\"  pub!=null \" >  AND pub LIKE concat('%',#{pub},'%')</if> " +
            " <if test=\"  locate!=null \" >  AND locate LIKE concat('%',#{locate},'%')</if> " +
            " </where>" +
            " </script>"
    })
    public List<Book> getAll(Book book);

    @Select("select * from Book_info where id=#{id};")
    public Book getById(Integer id);

    @Delete("delete from Book_info where id=#{id};")
    public int deleteById(Integer id);

    @Insert("insert into Book_info (name,time,pub,locate) values(#{name},#{time},#{pub},#{locate})")
    public int insertBook(Book book);

    @Update("update Book_info set name = #{name}, time = #{time}, pub = #{pub}, locate=#{locate} where id = #{id}")
    public int updateBook(Book book);

    @Select("SELECT * from Book_info where name like CONCAT('%',#{str},'%') or pub LIKE CONCAT('%',#{str},'%') or locate LIKE CONCAT('%',#{str},'%') or id LIKE CONCAT('%',#{str},'%') or time LIKE CONCAT('%',#{str},'%');")
    public List<Book> getFuzzySearch(String str);



}
