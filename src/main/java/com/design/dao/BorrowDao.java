package com.design.dao;

import com.design.domain.Book;
import com.design.domain.BookNum;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BorrowDao {
    @Select("select * from ;")
    public List<Book> getAll();

    @Select("select * from Book_info where id=#{id};")
    public List<Book> getById(String sno);

    // 删除记录 基本不使用
    @Delete("delete from Book_info where id=#{id};")
    public int deleteById(Integer id);

    @Insert("insert into Book_info (name,time,pub,locate) values(#{name},#{time},#{pub},#{locate})")
    public int insertBook(Book book);

    @Update("update Book_info set name = #{name}, time = #{time}, pub = #{pub}, locate=#{locate} where id = #{id}")
    public int updateBook(Book book);

    @Select("select name,time,pub,locate,count(*) num from Book_info group by (name,pub)")
    public BookNum getAllGroupByNamePub();

    @Select("select name,time,pub,locate,count(*) num from Book_info group by (name,pub) having (name,pub)=(#{name},#{pub})")
    public BookNum getByNameGroupByNamePub(String name,String pub);
}
