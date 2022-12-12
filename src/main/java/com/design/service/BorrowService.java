package com.design.service;

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

}
