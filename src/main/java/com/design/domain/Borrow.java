package com.design.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;


@Data
public class Borrow {
    private Integer SN;
    private Integer id;
    private String sno;
    private Timestamp borrow_time;
    private Timestamp return_time;
    private Integer fine;
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

}
