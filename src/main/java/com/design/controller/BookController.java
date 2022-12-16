package com.design.controller;

import com.alibaba.fastjson.JSONObject;
import com.design.domain.Book;
import com.design.domain.Code;
import com.design.domain.Result;
import com.design.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    // 展示全部书籍信息
    @PostMapping("/page")
    public Result getAll(@RequestBody Map<String, JSONObject> param) {
         JSONObject page=param.get("page");
        JSONObject sort=param.get("order");
        PageHelper.startPage(page.getInteger("offset"), page.getInteger("limit"),sort.isEmpty()?"":sort.getString("orderProp")+" "+sort.getString("orderAsc"));
        List<Book> bookList = bookService.getAll(param.get("query"));
        PageInfo<Book> bookPageInfo = new PageInfo<>(bookList);
        Integer code = bookList !=null? Code.GET_OK:Code.GET_ERR;
        String msg = bookList !=null? "查询全部结果成功!":"查询结果失败！";
        JSONObject data = new JSONObject();
        System.out.println(bookList);
        data.put("records",bookList);
        data.put("currentPage",bookPageInfo.getPageNum());
        data.put("pageSize",bookPageInfo.getPageSize());
        data.put("total",bookPageInfo.getTotal());
        return new Result(code,data,msg);
    }

////    模糊搜索 书名 id 时间 出版社 等信息
//    @GetMapping("/search/{str}")
//    public Result getById(@PathVariable String str) {
//        List<Book> bookList = bookService.getAllFuzzySearch(str);
//        Integer code = bookList !=null? Code.GET_OK:Code.GET_ERR;
//        String msg = bookList !=null? "查询搜索结果成功！":"查询结果失败！";
//        return new Result(code,bookList,msg);
//    }

//    保存传递的书籍信息
    @PostMapping("/add")
    public Result save(@RequestBody Book book) {
        boolean flag = bookService.insertBook(book);
        String msg = flag? "保存结果成功！":"保存结果失败！";
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,null, msg);
    }

//    修改传递的书籍信息
    @PostMapping("/update")
    public Result update(@RequestBody Book book) {
        boolean flag = bookService.updateBook(book);
        String msg = flag? "更新结果成功！":"更新结果失败！";
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,null, msg);
    }
////    按照ID删除某本书
//    @DeleteMapping("/{id}")
//    public Result delete(@PathVariable Integer id) {
//        boolean flag = bookService.deleteById(id);
//        String msg = flag? "删除结果成功！":"删除结果失败！";
//        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,null, msg);
//    }
//    按照ID列表删除书籍
    @PostMapping("/delete")
    public Result delete(@RequestParam("id") Integer id) {
        boolean flag = bookService.deleteById(id);
        String msg = flag? "删除结果成功！":"删除结果失败！";
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,null, msg);
    }
    @PostMapping("/batchDelete")
    public Result deleteList(@RequestBody Map<String,List<Integer>> param) {
        List<Integer> idList = param.get("ids");
        boolean flag = bookService.deleteByIdList(idList);
        String msg = flag? "删除结果成功！":"删除结果失败！";
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,null, msg);
    }
}
