package com.design.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Getter
@Setter

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
}
