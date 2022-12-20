package com.design.dao;

import com.design.domain.Book;
import com.design.domain.Borrow;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BorrowDao {

    @Select({" <script>" +
            " SELECT Borrow.*,Book_info.name bookname,Book_info.time,Book_info.pub,Book_info.locate,Stu_info.name stuname,Stu_info.dep,Stu_info.pro,Stu_info.limit_day FROM Borrow,Book_info,Stu_info" +
            " <where> Borrow.id=Book_info.id AND Borrow.sno=Stu_info.sno AND Borrow.return_time is null" +
            " <if test=\" SN !=null \" >  AND SN =#{SN}</if> " +
            " <if test=\" id !=null \" >  AND id =#{id}</if> " +
            " <if test=\" sno !=null  \" >  AND sno =#{sno}</if> " +
            " <if test=\" borrow_time !=null \" >  AND Borrow.borrow_time &gt;= #{borrow_time}</if> " +
            " <if test=\" return_time !=null \" >  AND Borrow.return_time &lt;= #{return_time}</if> " +
            " </where>" +
            " </script>"
    })
    public List<Borrow.BorrowInfo> getAllInfo(Borrow borrow);
    @Select({" <script>" +
            " SELECT * FROM Borrow" +
            " <where> 1=1 " +
            " <if test=\" SN !=null \" >  AND SN =#{SN}</if> " +
            " <if test=\" Borrow.id !=null \" >  AND Borrow.id =#{id}</if> " +
            " <if test=\" Borrow.sno !=null  \" >  AND Borrow.sno =#{sno}</if> " +
            " <if test=\" Borrow.borrow_time !=null \" >  AND DATE(Borrow.borrow_time) =#{borrow_time}</if> " +
            " <if test=\" Borrow.return_time!=null \" >  AND  DATE(Borrow.return_time) =#{return_time}</if> " +
            " </where>" +
            " </script>"
    })
    public List<Borrow> getAll(Borrow borrow);

    @Select("select * from Borrow where return_time=null;")
    public List<Borrow> getBorrow();
    @Select("select * from Borrow where SN=#{SN};")
    public Borrow getBySN(Integer SN);
    @Select("select SN,id,sno,borrow_time from Borrow where sno=#{sno} and return_time=null;")
    public List<Borrow.BorrowNoReturn> getBySnoBorrow(String sno);
    @Select("select * from Borrow where return_time!=null;")
    public List<Borrow> getReturn();
    @Select("select Borrow.SN,Book_info.*,Borrow.borrow_time from Borrow,Book_info where return_time is null and Borrow.sno=#{sno} and Book_info.id=Borrow.id ORDER BY borrow_time ASC")
    public List<Book.BookBorrow> getBySnoNoReturn(String sno);

    @Select("select * from Book_info where id not in(select id from Borrow where return_time=null)")
    public List<Book> getAllBookNoBorrow();

    @Select("SELECT * from Borrow where Borrow.sno=#{sno} and return_time is NULL and DATEDIFF(CURRENT_DATE,convert(borrow_time,date))>(SELECT limit_day from Stu_info where Stu_info.sno=Borrow.sno);")
    public List<Borrow> getBySnoBorrowOverExcept(String sno);

    @Insert("insert into Borrow (id,sno,borrow_time) values(#{id},#{sno},CURRENT_TIMESTAMP())")
    public int insertBorrow(@Param("id")Integer id,@Param("sno")String sno);

    // 删除记录 基本不使用
    @Delete("delete from Borrow where SN=#{SN};")
    public int deleteBySN(Integer SN);



    @Update("update Borrow set return_time=CURRENT_TIMESTAMP(), fine=#{fine} where SN = #{SN}")
    public int updateBorrow(@Param("SN")Integer SN, @Param("fine")Integer fine);

    @Select("select * from Book_num")
    public List<Book.BookNum> getAllGroupByNamePub();

    @Select("select * from Book_num where (name,pub)=(#{name},#{pub})")
    public Book.BookNum getNumGroupByNamePub(String name, String pub);



}
