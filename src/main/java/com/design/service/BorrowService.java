package com.design.service;

import com.alibaba.fastjson.JSONObject;
import com.design.domain.Book;
import com.design.domain.Borrow;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BorrowService {
    /**
     * 获得所有图书借阅记录
     * @return
     */
    public List<Borrow> getAll(JSONObject query);
    /**
     * 获得所有借出但并未归还图书借阅记录
     * @return
     */
    public List<Borrow> getBorrow();
    public List<Book.BookBorrow> getBySnoBorrow(String sno);
    /**
     * 获得所有归还图书借阅记录
     * @return
     */
    public List<Borrow> getReturn();
    /**
     * 根据Sno获得某学生的超期未还数据
     *
     */
    public List<Borrow> getBySnoBorrowOverExcept(String sno);
    /**
     * 获取某学生未归还书籍
     */
    public List<Borrow.BorrowNoReturn> getBySnoNoReturn(String sno);
    /**
     * 获取未被借出的书籍清单
     */
    public List<Book> getAllBookNoBorrow();
    /**
     * 添加借出记录
     */
    public Boolean insertBorrow(Book book,String sno);
    public Boolean insertBorrowList(List<Book> book,String sno);
    /**
     * 归还书籍
     */
    public Boolean updateReturn(Borrow.BorrowNoReturn borrowNoReturn);
    public Boolean updateReturnList(List<Borrow.BorrowNoReturn> borrowNoReturn);
    /**
     * 续借书籍
     */
    @Transactional
    public Boolean reBorrowBook(Borrow.BorrowNoReturn borrowNoReturn);
    @Transactional
    public Boolean reBorrowBookList(List<Borrow.BorrowNoReturn> borrowNoReturnList);
}
