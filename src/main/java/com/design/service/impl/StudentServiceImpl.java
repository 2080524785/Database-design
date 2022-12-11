package com.design.service.impl;

import com.design.dao.StudentDao;
import com.design.dao.StudentDao;
import com.design.domain.Student;
import com.design.domain.Student;
import com.design.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    public List<Student> getAll() {
        return studentDao.getAll();
    }

    @Override
    public Student getBySno(String sno) {
        return studentDao.getBySno(sno);
    }

    @Override
    public boolean insertStudent(Student student) {
        return studentDao.insertStudent(student)>0;
    }

    @Override
    public boolean updateStudent(Student Student) {
        return studentDao.updateStudent(Student)>0;
    }

    @Override
    public boolean deleteBySno(String sno) {
        return studentDao.deleteBySno(sno)>0;
    }
}
