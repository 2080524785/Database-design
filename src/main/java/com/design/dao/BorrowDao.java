package com.design.dao;

import com.design.domain.Borrow;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BorrowDao {

    @Select("select * from Borrow;")
    public List<Borrow> getAll();

    @Select("select * from Borrow where return_time=null;")
    public List<Borrow> getBorrow();

    @Select("select * from Borrow where return_time!=null;")
    public List<Borrow> getReturn();

    @Select("select * from Borrow where SN=#{SN};")
    public List<Borrow> getBySN(String sno);

    @Select("select * from Borrow where name=#{name};")
    public List<Borrow> getByName(String name);

    @Select("select * from Borrow where sno=#{sno};")
    public List<Borrow> getBySno(String sno);

    @Select("select * from Borrow where id=#{id};")
    public List<Borrow> getById(Integer id);

    @Select("select * from Borrow where pub=#{pub};")
    public List<Borrow> getById(String pub);

    // 删除记录 基本不使用
    @Delete("delete from Borrow where SN=#{SN};")
    public int deleteBySN(Integer SN);

    @Insert("insert into Borrow (name,time,pub,locate) values(#{name},#{time},#{pub},#{locate})")
    public int insertBorrow(Borrow borrow);

    @Update("update Borrow set name = #{name}, time = #{time}, pub = #{pub}, locate=#{locate} where SN = #{SN}")
    public int updateBorrow(Borrow borrow);

    @Select("select count(*) num from Borrow group by (name,pub)")
    public int getAllGroupByNamePub();

    @Select("select count(*) num from Borrow group by (name,pub) having (name,pub)=(#{name},#{pub})")
    public int getByNameGroupByNamePub(String name,String pub);
}
