package com.design.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.design.dao.BookDao;
import com.design.dao.BorrowDao;
import com.design.dao.StudentDao;
import com.design.domain.Book;
import com.design.domain.Borrow;
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
    public Boolean insertBorrow(Book book, String sno) {
        return borrowDao.insertBorrow(new Borrow.BorrowNoReturn(book,sno))>0;
    }

    @Override
    public Boolean insertBorrowList(List<Book> bookList, String sno) {
        int sum=0;
        for(Book book:bookList){
            sum += borrowDao.insertBorrow(new Borrow.BorrowNoReturn(book,sno));
        }
        return sum==bookList.size();
    }

    @Override
    public Boolean updateReturn(Borrow.BorrowNoReturn borrowNoReturn) {
        Borrow borrow = new Borrow(borrowNoReturn);

        long day = ((borrow.getReturn_time().getTime()-borrow.getBorrow_time().getTime())/(24*3600*1000)-studentDao.getBySno(borrow.getSno()).getLimit_day());
        if(day>0){
            borrow.setFine(day*2);
        }else{
            borrow.setFine(0);
        }
        return borrowDao.updateBorrow(borrow)>0;
    }

    @Override
    public Boolean updateReturnList(List<Borrow.BorrowNoReturn> borrowNoReturnList) {
        List<Borrow> borrowList = new ArrayList<Borrow>();
        int sum=0;
        for(Borrow.BorrowNoReturn borrowNoReturn:borrowNoReturnList){
            Borrow borrow = new Borrow(borrowNoReturn);
            long day = ((borrow.getReturn_time().getTime()-borrow.getBorrow_time().getTime())/(24*3600*1000)-studentDao.getBySno(borrow.getSno()).getLimit_day());
            if(day>0){
                borrow.setFine(day*2);
            }else{
                borrow.setFine(0);
            }
            borrowList.add(borrow);
            sum+=borrowDao.updateBorrow(borrow);
        }
        return sum==borrowList.size();
    }

    @Override
    public Boolean reBorrowBook(Borrow.BorrowNoReturn borrowNoReturn) {
        Borrow borrow = new Borrow(borrowNoReturn);
        long day = ((borrow.getReturn_time().getTime()-borrow.getBorrow_time().getTime())/(24*3600*1000)-studentDao.getBySno(borrow.getSno()).getLimit_day());
        if(day>0){
            borrow.setFine(day*2);
        }else{
            borrow.setFine(0);
        }
        Book book = bookDao.getById(borrow.getId());
        int flag = borrowDao.updateBorrow(borrow);
        flag += borrowDao.insertBorrow(new Borrow.BorrowNoReturn(book,borrow.getSno()));
        return flag==2;
    }

    @Override
    public Boolean reBorrowBookList(List<Borrow.BorrowNoReturn> borrowNoReturnList) {
        List<Borrow> borrowList = new ArrayList<Borrow>();
        int sum=0;
        for(Borrow.BorrowNoReturn borrowNoReturn:borrowNoReturnList){
            Borrow borrow = new Borrow(borrowNoReturn);
            long day = ((borrow.getReturn_time().getTime()-borrow.getBorrow_time().getTime())/(24*3600*1000)-studentDao.getBySno(borrow.getSno()).getLimit_day());
            if(day>0){
                borrow.setFine(day*2);
            }else{
                borrow.setFine(0);
            }
            Book book = bookDao.getById(borrow.getId());
            int flag = borrowDao.updateBorrow(borrow);
            flag += borrowDao.insertBorrow(new Borrow.BorrowNoReturn(book,borrow.getSno()));
            if(flag==2) sum+=1;
        }
        return sum==borrowList.size();
    }


}
