package com.design.dao;

import com.design.domain.Book;
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
    @Select("select SN,id,sno,borrow_time from Borrow where sno=#{sno} and return_time=null;")
    public List<Borrow.BorrowNoReturn> getBySnoBorrow(String sno);
    @Select("select * from Borrow where return_time!=null;")
    public List<Borrow> getReturn();


    @Select("select * from Book_info where id not exists(select id from Borrow where return_time=null)")
    public List<Book> getAllBookNoBorrow();

    @Select("SELECT * from Borrow where Borrow.sno=#{sno} and return_time is NULL and DATEDIFF(CURRENT_DATE,convert(borrow_time,date))>(SELECT limit_day from Stu_info where Stu_info.sno=Borrow.sno);")
    public List<Borrow> getBorrowOverExcept(String sno);


    // 删除记录 基本不使用
    @Delete("delete from Borrow where SN=#{SN};")
    public int deleteBySN(Integer SN);

    @Insert("insert into Borrow (id,sno,borrow_time) values(#{id},#{sno},#{borrow_time})")
    public int insertBorrow(Borrow.BorrowNoReturn borrowNoReturn);

    @Update("update Borrow set name = #{name}, time = #{time}, pub = #{pub}, locate=#{locate} where SN = #{SN}")
    public int updateBorrow(Borrow borrow);

    @Select("select * from Book_num")
    public List<Book.BookNum> getAllGroupByNamePub();

    @Select("select * from Book_num where (name,pub)=(#{name},#{pub})")
    public Book.BookNum getNumGroupByNamePub(String name, String pub);



}
