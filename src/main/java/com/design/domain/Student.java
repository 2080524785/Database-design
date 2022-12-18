package com.design.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class Student {
    private String sno;
    private String name;
    private String dep;
    private String pro;
    private int limit_num=5;
    private int limit_day=30;

    public Student() {
    }

    public Student(String sno, String name, String dep, String pro) {
        this.sno = sno;
        this.name = name;
        this.dep = dep;
        this.pro = pro;
    }

    public Student(String sno, String name, String dep, String pro, int limit_num, int limit_day) {
        this.sno = sno;
        this.name = name;
        this.dep = dep;
        this.pro = pro;
        this.limit_num = limit_num;
        this.limit_day = limit_day;
    }

    public Student(JSONObject query) {
        if(!query.isEmpty()){
            this.sno=query.containsKey("sno")||query.getString("sno")==""? query.getString("sno") :null;
            this.name=query.containsKey("name")||query.getString("name")==""? query.getString("name") :null;
            this.dep=query.containsKey("dep")||query.getString("dep")==""? query.getString("dep") :null;
            this.pro=query.containsKey("pro")||query.getString("pro")==""? query.getString("pro") :null;
        }
    }
}
