package com.design.controller;

import com.design.domain.Student;
import com.design.domain.Code;
import com.design.domain.Result;
import com.design.service.StudentService;
import com.design.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stu")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // 展示全部书籍信息
    @GetMapping
    public Result getAll() {
        List<Student> studentList = studentService.getAll();
        Integer code = studentList !=null? Code.GET_OK:Code.GET_ERR;
        String msg = studentList !=null? "查询全部结果成功！":"查询结果失败，未找到该数据！";
        return new Result(code,studentList,msg);
    }
    // 通过学号获取某学生信息
    @GetMapping("/{sno}")
    public Result getById(@PathVariable String sno) {
        Student student = studentService.getBySno(sno);
        Integer code = student !=null? Code.GET_OK:Code.GET_ERR;
        String msg = student !=null? "根据学号查询结果成功！":"查询结果失败！";
        return new Result(code,student,msg);
    }
    // 模糊搜索 学号 系别 专业 等信息
    @GetMapping("/search/{str}")
    public Result getFuzzySearch(@PathVariable String str) {
        List<Student> studentList = studentService.getAllFuzzySearch(str);
        Integer code = studentList !=null? Code.GET_OK:Code.GET_ERR;
        String msg = studentList !=null? "模糊查询结果成功！":"查询结果失败，未找到该数据！";
        return new Result(code,studentList,msg);
    }

    // 单例保存一个学生类
    @PostMapping
    public Result save(@RequestBody Student student) {
        boolean flag = studentService.insertStudent(student);
        String msg = flag? "保存结果成功！":"保存结果失败！";
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,null, msg);
    }
    // 保存学生类列表
    @PostMapping("/list")
    public Result saveList(@RequestBody List<Student> studentList) {
        boolean flag = studentService.insertStudentList(studentList);
        String msg = flag? "保存结果成功！":"保存结果失败！";
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,null, msg);
    }
    // 传入学生信息，并进行修改
    @PutMapping
    public Result update(@RequestBody Student student) {
        boolean flag = studentService.updateStudent(student);
        String msg = flag? "更新结果成功！":"更新结果失败！";
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,null, msg);
    }
    // 按照学号删除某学生
    @DeleteMapping("/{sno}")
    public Result delete(@PathVariable String sno) {
        boolean flag = studentService.deleteBySno(sno);
        String msg = flag? "删除结果成功！":"删除结果失败！";
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,null, msg);
    }
    // 按照学号列表删除学生
    @DeleteMapping("/list")
    public Result delete(List<String> snoList) {
        boolean flag = studentService.deleteBySnoList(snoList);
        String msg = flag? "删除结果成功！":"删除结果失败！";
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,null, msg);
    }
}
