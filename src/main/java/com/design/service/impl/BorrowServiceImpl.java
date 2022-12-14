package com.design.service.impl;

import com.design.dao.BookDao;
import com.design.dao.BorrowDao;
import com.design.domain.Book;
import com.design.domain.Borrow;
import com.design.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Borrow> getBorrowOverExcept(String sno) {
        return borrowDao.getBorrowOverExcept(sno);
    }

    @Override
    public List<Borrow.BorrowNoReturn> getBySnoNoReturn(String sno) {
        return borrowDao.getBySnoBorrow(sno);
    }

    @Override
    public List<Book> getAllBookNoBorrow() {
        return borrowDao.getAllBookNoBorrow();
    }


}
