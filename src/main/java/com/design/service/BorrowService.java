package com.design.service;

import com.design.domain.Book;
import com.design.domain.Borrow;

import java.util.List;

public interface BorrowService {
    /**
     * 获得所有图书借阅记录
     * @return
     */
    public List<Borrow> getAll();
    /**
     * 获得所有借出但并未归还图书借阅记录
     * @return
     */
    public List<Borrow> getBorrow();
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
}
