package com.design.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.design.dao.BookDao;
import com.design.domain.Book;
import com.design.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    public List<Book> getAll(JSONObject query) throws ParseException {
        return bookDao.getAll(new Book(query));
    }

    @Override
    public Book.BookBorrowInfo getInfoBySno(Integer id) {
        return bookDao.getInfoById(id);
    }


    @Override
    public List<Book> getAllFuzzySearch(String str) {
        return bookDao.getFuzzySearch(str);
    }
    @Override
    public boolean insertBook(Book book) {
        return bookDao.insertBook(book)>0;
    }

    @Override
    public boolean insertBookList(List<Book> bookList) {
        int sum=0;
        for(Book book:bookList){
            sum += bookDao.insertBook(book);
        }
        return sum==bookList.size();
    }

    @Override
    public boolean updateBook(Book book) {
        return bookDao.updateBook(book)>0;
    }

    @Override
    public boolean deleteById(Integer id) {
        return bookDao.deleteById(id)>0;
    }
    @Override
    public boolean deleteByIdList(List<Integer> idList) {
        int sum=0;
        for(Integer id:idList){
            sum += bookDao.deleteById(id);
        }
        return sum==idList.size();
    }



}
