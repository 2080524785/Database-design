package com.design.dao;

import com.design.domain.Book;
import com.design.domain.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentDao {
    @Select("select * from Stu_info where sno=#{sno}")
    public Student getBySno(String Sno);

    @Select("select * from Stu_info")
    public List<Student> getAll();

    @Update("update Stu_info set name=#{name}, dep=#{dep}, pro=#{pro} where sno=#{sno}")
    public int updateStudent(Student student);

    @Insert("insert into Stu_info (sno,name,dep,pro,limit_num,limit_day) values(#{sno}, #{name}, #{dep}, #{pro},'5','30')")
    public int insertStudent(Student student);

    @Delete("delete from Stu_info where sno=#{sno}")
    public int deleteBySno(String sno);

    @Select("SELECT * from Stu_info where name like CONCAT('%',#{str},'%') or dep LIKE CONCAT('%',#{str},'%') or pro LIKE CONCAT('%',#{str},'%') or sno LIKE CONCAT('%',#{str},'%');")
    public List<Student> getFuzzySearch(String str);
}
