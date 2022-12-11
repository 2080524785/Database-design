package com.design.service;

import com.design.domain.Book;
import com.design.domain.Student;

import java.util.List;

public interface StudentService {
    /**
     * 查询所有学生记录
     * @return
     */
    public List<Student> getAll();

    /**
     * 按照ID来查找书籍
     * @param sno
     * @return
     */
    public Student getBySno(String sno);

    /**
     * 提供Book数据后，执行添加到数据库
     * @param student
     * @return
     */
    public boolean insertStudent(Student student);

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
}
