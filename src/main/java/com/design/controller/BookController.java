package com.design.controller;

import com.design.domain.Book;
import com.design.domain.Code;
import com.design.domain.Result;
import com.design.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    // 展示全部书籍信息
    @GetMapping
    public Result getAll() {
        List<Book> bookList = bookService.getAll();
        Integer code = bookList !=null? Code.GET_OK:Code.GET_ERR;
        String msg = bookList !=null? "查询全部结果成功!":"查询结果失败！";
        return new Result(code,bookList,msg);
    }
    // 按照ID查找书籍信息
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Book book = bookService.getById(id);
        Integer code = book !=null? Code.GET_OK:Code.GET_ERR;
        String msg = book !=null? "根据ID查询结果成功！":"查询结果失败！";
        return new Result(code,book,msg);
    }
//    模糊搜索 书名 id 时间 出版社 等信息
    @GetMapping("/search/{str}")
    public Result getByIdF(@PathVariable String str) {
        List<Book> bookList = bookService.getAllFuzzySearch(str);
        Integer code = bookList !=null? Code.GET_OK:Code.GET_ERR;
        String msg = bookList !=null? "查询搜索结果成功！":"查询结果失败！";
        return new Result(code,bookList,msg);
    }

//    保存传递的书籍信息
    @PostMapping
    public Result save(@RequestBody Book book) {
        boolean flag = bookService.insertBook(book);
        String msg = flag? "保存结果成功！":"保存结果失败！";
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,null, msg);
    }
//    保存传递的书籍列表信息
    @PostMapping("/list")
    public Result saveList(@RequestBody List<Book> bookList) {
        boolean flag = bookService.insertBookList(bookList);
        String msg = flag? "保存结果成功！":"保存结果失败！";
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,null, msg);
    }
//    修改传递的书籍信息
    @PutMapping
    public Result update(@RequestBody Book book) {
        boolean flag = bookService.updateBook(book);
        String msg = flag? "更新结果成功！":"更新结果失败！";
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,null, msg);
    }
//    按照ID删除某本书
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = bookService.deleteById(id);
        String msg = flag? "删除结果成功！":"删除结果失败！";
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,null, msg);
    }
//    按照ID列表删除书籍
    @DeleteMapping("/list")
    public Result delete(@RequestBody List<Integer> idList) {
        boolean flag = bookService.deleteByIdList(idList);
        String msg = flag? "删除结果成功！":"删除结果失败！";
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,null, msg);
    }
}
