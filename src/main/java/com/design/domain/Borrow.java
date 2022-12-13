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
    public class BorrowNoReturn{
        private Integer SN;
        private Integer id;
        private String sno;
        private Timestamp borrow_time;
    }
}
