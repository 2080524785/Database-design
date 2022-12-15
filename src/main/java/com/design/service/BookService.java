package com.design.service;

import com.alibaba.fastjson.JSONObject;
import com.design.domain.Book;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BookService {
    /**
     * 查询所有书籍记录
     * @return
     */
    public List<Book> getAll(JSONObject query);



    /**
     * 提供Book数据后，执行添加到数据库
     * @param book
     * @return
     */
    public boolean insertBook(Book book);
    public boolean insertBookList(List<Book> bookList);
    /**
     * 更新数据库中该Book数据
     * @param book
     * @return
     */
    public boolean updateBook(Book book);

    /**
     * 按照ID来删除该书籍
     * @param id
     * @return
     */
    public boolean deleteById(Integer id);
    public boolean deleteByIdList(List<Integer> idList);


    /**
     * 模糊搜索书名出版社和
     * @param str
     * @return
     */
    public List<Book> getAllFuzzySearch(String str);
}
