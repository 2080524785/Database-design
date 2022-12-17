package com.design.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

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
        private Integer num;
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

    public Book(Integer id, String name, Date time, String pub, String locate) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.pub = pub;
        this.locate = locate;
    }

    public Book() {
    }

    public Book(JSONObject query) {
        if(!query.isEmpty()){
            this.id=query.containsKey("id")? query.getInteger("id") :null;
            this.name=query.containsKey("name")&&query.getString("name")!=""? query.getString("name") :null;
            this.time=query.containsKey("time")? (Date) query.get("time"+"-01-01") :null;
            this.pub=query.containsKey("pub")&&query.getString("pub")!=""? query.getString("pub") :null;
            this.locate=query.containsKey("locate")&&query.getString("locate")!=""? query.getString("locate") :null;
        }
    }
}
