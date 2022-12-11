package com.design.service.impl;

import com.design.dao.BookDao;
import com.design.domain.Book;
import com.design.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public Book getById(Integer id) {
        return bookDao.getById(id);
    }

    @Override
    public boolean insertBook(Book book) {
        return bookDao.insertBook(book)>0;
    }

    @Override
    public boolean updateBook(Book book) {
        return bookDao.updateBook(book)>0;
    }

    @Override
    public boolean deleteById(Integer id) {
        return bookDao.deleteById(id)>0;
    }
}
