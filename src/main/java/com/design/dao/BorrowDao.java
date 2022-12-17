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
            " SELECT * FROM Borrow " +
            " <where> 1=1 " +
            " <if test=\" SN !=null \" >  AND SN =#{SN}</if> " +
            " <if test=\" id !=null \" >  AND id =#{id}</if> " +
            " <if test=\" sno !=null  \" >  AND sno =#{sno}</if> " +
            " <if test=\" borrow_time !=null \" >  AND borrow_time =#{borrow_time}</if> " +
            " <if test=\"  return_time!=null \" >  AND return_time =#{return_time}</if> " +
            " </where>" +
            " </script>"
    })
    public List<Borrow> getAll(Borrow borrow);

    @Select("select * from Borrow where return_time=null;")
    public List<Borrow> getBorrow();
    @Select("select SN,id,sno,borrow_time from Borrow where sno=#{sno} and return_time=null;")
    public List<Borrow.BorrowNoReturn> getBySnoBorrow(String sno);
    @Select("select * from Borrow where return_time!=null;")
    public List<Borrow> getReturn();
    @Select("select Book_info.*,Borrow.borrow_time from Borrow,Book_info where return_time is null and Borrow.sno=#{sno} and Book_info.id=Borrow.id ORDER BY borrow_time ASC;")
    public List<Book.BookBorrow> getBySnoNoReturn(String sno);

    @Select("select * from Book_info where id not in(select id from Borrow where return_time=null)")
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
