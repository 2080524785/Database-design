package com.design.dao;

import com.design.domain.Book;
import com.design.domain.Borrow;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BorrowDao {

    @Select({" <script>" +
            " SELECT * FROM Book_info " +
            " <where> 1=1 " +
            " <if test=\" sno !=null  \" >  AND sno =#{sno}</if> " +
            " <if test=\" name !=null \" >  AND name =#{name}</if> " +
            " <if test=\"  dep!=null \" >  AND dep =#{dep}</if> " +
            " <if test=\"  pro!=null \" >  AND pro =#{pro}</if> " +
            " </where>" +
            " <if test=\"  pro!=null \" >  AND pro =#{pro}</if> " +
            "order by tb_car.id ASC"+
            " </script>"
    })
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
    public List<Borrow> getBySnoBorrowOverExcept(String sno);

    @Insert("insert into Borrow (id,sno,borrow_time) values(#{id},#{sno},#{borrow_time})")
    public int insertBorrow(Borrow.BorrowNoReturn borrowNoReturn);

    // 删除记录 基本不使用
    @Delete("delete from Borrow where SN=#{SN};")
    public int deleteBySN(Integer SN);



    @Update("update Borrow set return_time=#{return_time}, fine=#{fine} where SN = #{SN}")
    public int updateBorrow(Borrow borrow);

    @Select("select * from Book_num")
    public List<Book.BookNum> getAllGroupByNamePub();

    @Select("select * from Book_num where (name,pub)=(#{name},#{pub})")
    public Book.BookNum getNumGroupByNamePub(String name, String pub);



}
