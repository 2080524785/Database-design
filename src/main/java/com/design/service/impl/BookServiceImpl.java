package com.design.service.impl;

import com.design.dao.BookDao;
import com.design.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl {
    @Autowired
    private BookDao bookDao;

    public List<Book> getAll() {
        return bookDao.getAll();
    }
}
