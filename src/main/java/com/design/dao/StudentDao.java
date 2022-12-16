package com.design.dao;

import com.design.domain.Book;
import com.design.domain.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentDao {
    @Select("select * from Stu_info where sno=#{sno};")
    public Student getBySno(String Sno);

    @Select({" <script>" +
            " SELECT * FROM Stu_info " +
            " <where> 1=1 " +
            " <if test=\" sno!=null \" >  AND sno = #{sno}</if> " +
            " <if test=\" name!=null \" >  AND name LIKE concat('%',#{name},'%')</if> " +
            " <if test=\" dep!=null \" >  AND dep LIKE concat('%',#{dep},'%')</if> " +
            " <if test=\" pro!=null \" >  AND pro LIKE concat('%',#{pro},'%')</if> " +
            " </where>" +
            " </script>"
    })
    public List<Student> getAll(Student student);

    @Update("update Stu_info set name=#{name}, dep=#{dep}, pro=#{pro} where sno=#{sno}")
    public int updateStudent(Student student);

    @Insert("insert into Stu_info (sno,name,dep,pro,limit_num,limit_day) values(#{sno}, #{name}, #{dep}, #{pro},'5','30')")
    public int insertStudent(Student student);

    @Delete("delete from Stu_info where sno=#{sno}")
    public int deleteBySno(String sno);

    @Select("SELECT * from Stu_info where name like CONCAT('%',#{str},'%') or dep LIKE CONCAT('%',#{str},'%') or pro LIKE CONCAT('%',#{str},'%') or sno LIKE CONCAT('%',#{str},'%');")
    public List<Student> getFuzzySearch(String str);
}
