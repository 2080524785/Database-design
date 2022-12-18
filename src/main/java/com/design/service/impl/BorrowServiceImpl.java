package com.design.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.design.dao.BookDao;
import com.design.dao.BorrowDao;
import com.design.dao.StudentDao;
import com.design.domain.Book;
import com.design.domain.Borrow;
import com.design.domain.Student;
import com.design.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowDao borrowDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Borrow> getAll(JSONObject query) {
        return borrowDao.getAll(new Borrow(query));
    }

    @Override
    public List<Borrow> getBorrow() {
        return borrowDao.getBorrow();
    }

    @Override
    public List<Book.BookBorrow> getBySnoBorrow(String sno) {
        return borrowDao.getBySnoNoReturn(sno);
    }

    @Override
    public List<Borrow> getReturn() {
        return borrowDao.getReturn();
    }

    @Override
    public List<Borrow> getBySnoBorrowOverExcept(String sno) {
        return borrowDao.getBySnoBorrowOverExcept(sno);
    }

    @Override
    public List<Borrow.BorrowNoReturn> getBySnoNoReturn(String sno) {
        return borrowDao.getBySnoBorrow(sno);
    }

    @Override
    public List<Book> getAllBookNoBorrow() {
        return borrowDao.getAllBookNoBorrow();
    }



    @Override
    public Boolean insertBorrow(Integer id, String sno) {
        return borrowDao.insertBorrow(id,sno)>0;
    }


    @Override
    public Boolean updateReturn(Integer SN) {
        Borrow borrow = borrowDao.getBySN(SN);
        Student student = studentDao.getBySno(borrow.getSno());
        long day = ((new Date(System.currentTimeMillis())).getTime()-borrow.getBorrow_time().getTime())/(24*3600*1000)-student.getLimit_day();
        long fine = day>0?day*2:0;
        if(fine>0&&student.getLimit_day()>10){
            studentDao.updateStudentSubLimitDay(student.getSno());
        }else if (fine==0&&student.getLimit_day()<30){
            studentDao.updateStudentAddLimitDay(student.getSno());
        }
        return borrowDao.updateBorrow(SN, (int) fine)>0;
    }

    @Override
    public Boolean updateReturnList(List<Integer> SNList) {
        List<Borrow> borrowList = new ArrayList<Borrow>();
        int sum=0;
        for(Integer SN:SNList){
            Borrow borrow = borrowDao.getBySN(SN);
            Student student = studentDao.getBySno(borrow.getSno());
            long day = ((new Date(System.currentTimeMillis())).getTime()-borrow.getBorrow_time().getTime())/(24*3600*1000)-student.getLimit_day();
            long fine = day>0?day*2:0;
            if(fine>0&&student.getLimit_day()>10){
                studentDao.updateStudentSubLimitDay(student.getSno());
            }else if (fine==0&&student.getLimit_day()<30){
                studentDao.updateStudentAddLimitDay(student.getSno());
            }
            sum+=borrowDao.updateBorrow(SN, (int) fine);
        }
        return sum==borrowList.size();
    }

    @Override
    public Boolean reBorrowBook(Integer SN) {
        Borrow borrow = borrowDao.getBySN(SN);
        Student student = studentDao.getBySno(borrow.getSno());
        long day = ((new Date(System.currentTimeMillis())).getTime()-borrow.getBorrow_time().getTime())/(24*3600*1000)-student.getLimit_day();
        long fine = day>0?day*2:0;
        if(fine>0&&student.getLimit_day()>10){
            studentDao.updateStudentSubLimitDay(student.getSno());
        }else if (fine==0&&student.getLimit_day()<30){
            studentDao.updateStudentAddLimitDay(student.getSno());
        }
        Book book = bookDao.getById(borrow.getId());
        int flag = borrowDao.updateBorrow(SN, (int) fine);
        flag += borrowDao.insertBorrow(borrow.getId(),borrow.getSno());
        return flag==2;
    }

    @Override
    public Boolean reBorrowBookList(List<Integer> SNList) {
        List<Borrow> borrowList = new ArrayList<Borrow>();
        int sum=0;
        for(Integer SN:SNList){
            Borrow borrow = borrowDao.getBySN(SN);
            Student student = studentDao.getBySno(borrow.getSno());
            long day = ((new Date(System.currentTimeMillis())).getTime()-borrow.getBorrow_time().getTime())/(24*3600*1000)-student.getLimit_day();
            long fine = day>0?day*2:0;
            if(fine>0&&student.getLimit_day()>10){
                studentDao.updateStudentSubLimitDay(student.getSno());
            }else if (fine==0&&student.getLimit_day()<30){
                studentDao.updateStudentAddLimitDay(student.getSno());
            }
            Book book = bookDao.getById(borrow.getId());
            int flag = borrowDao.updateBorrow(SN, (int) fine);
            flag += borrowDao.insertBorrow(borrow.getId(),borrow.getSno());
            if(flag==2) sum+=1;
        }
        return sum==borrowList.size();
    }


}
