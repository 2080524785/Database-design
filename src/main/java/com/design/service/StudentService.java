package com.design.service;

import com.alibaba.fastjson.JSONObject;
import com.design.domain.Book;
import com.design.domain.Student;

import java.util.List;

public interface StudentService {
    /**
     * 查询所有学生记录
     * @return
     */
    public List<Student> getAll(JSONObject query);

    /**
     * 按照ID来查找书籍
     * @param sno
     * @return
     */
    public Student getBySno(String sno);

    /**
     * 模糊搜索
     * @param str
     * @return
     */
    public List<Student> getAllFuzzySearch(String str);
    /**
     * 提供Book数据后，执行添加到数据库
     * @param student
     * @return
     */
    public boolean insertStudent(Student student);
    public boolean insertStudentList(List<Student> studentList);
    /**
     * 更新数据库中该Book数据
     * @param student
     * @return
     */
    public boolean updateStudent(Student student);

    /**
     * 按照ID来删除该书籍
     * @param sno
     * @return
     */
    public boolean deleteBySno(String sno);
    public boolean deleteBySnoList(List<String> snoList);

}
