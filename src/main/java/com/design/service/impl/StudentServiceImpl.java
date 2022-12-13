package com.design.service.impl;

import com.design.dao.StudentDao;
import com.design.dao.StudentDao;
import com.design.domain.Book;
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
    public List<Student> getAllFuzzySearch(String str) {
        return studentDao.getFuzzySearch(str);
    }

    @Override
    public boolean insertStudent(Student student) {
        return studentDao.insertStudent(student)>0;
    }

    @Override
    public boolean insertStudentList(List<Student> studentList) {
        int sum=0;
        for(Student student:studentList){
            sum += studentDao.insertStudent(student);
        }
        return sum==studentList.size();
    }

    @Override
    public boolean updateStudent(Student Student) {
        return studentDao.updateStudent(Student)>0;
    }

    @Override
    public boolean deleteBySno(String sno) {
        return studentDao.deleteBySno(sno)>0;
    }
    @Override
    public boolean deleteBySnoList(List<String> snoList) {
        int sum=0;
        for(String sno:snoList){
            sum += studentDao.deleteBySno(sno);
        }
        return sum==snoList.size();
    }

}
