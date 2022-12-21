package com.design.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
public class Book {
    private Integer id;
    private String name;
    private Date time;
    private String pub;
    private String locate;

    @Data
    public static class BookNum{
        private String name;
        private String pub;
        private Date time;
        private Integer nowBorrow;
        private Integer allBorrow;
        private Integer inventory;
        private Integer num;

        public BookNum(){

        }

        public BookNum(String name,String pub,Date time,Integer nowBorrow,Integer allBorrow,Integer inventory,Integer num){
            this.name=name;
            this.pub=pub;
            this.time=time;
            this.nowBorrow=nowBorrow;
            this.allBorrow=allBorrow;
            this.inventory=inventory;
            this.num=num;
        }

        public BookNum(JSONObject query) throws ParseException {
        if(!query.isEmpty()){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            this.name=query.containsKey("name")&&query.getString("name")!=""? query.getString("name") :null;
            this.time=query.containsKey("time")? format.parse(query.get("time")+"-01-01") :null;
            this.pub=query.containsKey("pub")&&query.getString("pub")!=""? query.getString("pub") :null;
        }
    }
    }

    @Data
    public static class BookBorrow{
        private Integer SN;
        private Integer id;
        private String name;
        private Date time;
        private String pub;
        private String locate;
        private Timestamp borrow_time;
    }
    @Data
    public static class BookBorrowInfo{
        private Integer id;
        private String name;
        private Date time;
        private String pub;
        private String locate;
        private Boolean is_Borrow;
    }

    public Book(Integer id, String name, Date time, String pub, String locate) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.pub = pub;
        this.locate = locate;
    }

    public Book() {
    }

    public Book(JSONObject query) throws ParseException {
        if(!query.isEmpty()){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            this.id=query.containsKey("id")? query.getInteger("id") :null;
            this.name=query.containsKey("name")&&query.getString("name")!=""? query.getString("name") :null;
            this.time=query.containsKey("time")? format.parse(query.get("time")+"-01-01") :null;
            this.pub=query.containsKey("pub")&&query.getString("pub")!=""? query.getString("pub") :null;
            this.locate=query.containsKey("locate")&&query.getString("locate")!=""? query.getString("locate") :null;
        }
    }
}
