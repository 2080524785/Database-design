package com.design.service.impl;

import com.design.dao.BookDao;
import com.design.dao.BorrowDao;
import com.design.domain.Book;
import com.design.domain.Borrow;
import com.design.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowDao borrowDao;


    @Override
    public List<Borrow> getAll() {
        return borrowDao.getAll();
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
//        int day = new Date(borrow.getReturn_time().getTime()) - new Date(borrow.getBorrow_time().getTime());
        return null;
    }


}
