package com.design.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;


@Getter
@Setter
@ToString
public class Borrow {
    private Integer SN;
    private Integer id;
    private String sno;
    private Timestamp borrow_time;
    private Timestamp return_time;
    private long fine;
    @Data
    public static class BorrowNoReturn{
        private Integer SN;
        private Integer id;
        private String sno;
        private Timestamp borrow_time;
        public BorrowNoReturn(Book book, String sno){
            this.SN=null;
            this.id=book.getId();
            this.sno=sno;
            this.borrow_time=new Timestamp(System.currentTimeMillis());
        }
    }
    @Data
    public static class BorrowInfo{
        private Integer SN;
        private Integer id;
        private String sno;
        private Timestamp borrow_time;
        private Timestamp return_time;
        private long fine;

        private String bookname;
        private Date time;
        private String pub;
        private String locate;


        private String stuname;
        private String dep;
        private String pro;
        private int limit_day=30;
    }
    public Borrow(BorrowNoReturn borrowNoReturn){
        this.SN=borrowNoReturn.SN;
        this.id=borrowNoReturn.id;
        this.sno=borrowNoReturn.sno;
        this.borrow_time = borrowNoReturn.borrow_time;
        this.return_time=new Timestamp(System.currentTimeMillis());
    }

    public Borrow(Integer SN, Integer id, String sno, Timestamp borrow_time, Timestamp return_time, long fine) {
        this.SN = SN;
        this.id = id;
        this.sno = sno;
        this.borrow_time = borrow_time;
        this.return_time = return_time;
        this.fine = fine;
    }
    public Borrow(Integer SN, Integer id, String sno, Timestamp borrow_time) {
        this.SN = SN;
        this.id = id;
        this.sno = sno;
        this.borrow_time = borrow_time;
    }
    public Borrow(JSONObject query){

        if(!query.isEmpty()){
            this.SN=query.containsKey("SN")? query.getInteger("SN") :null;
            this.id=query.containsKey("id")? query.getInteger("id") :null;
            this.sno=query.containsKey("sno")&&query.getString("sno")!=""? query.getString("sno") :null;
            this.borrow_time=query.containsKey("borrow_time")? new Timestamp(query.getDate("borrow_time").getTime()) :null;
            this.return_time=query.containsKey("return_time")? new Timestamp(query.getDate("return_time").getTime()) :null;
        }
    }
    public Borrow(){

    }
}
