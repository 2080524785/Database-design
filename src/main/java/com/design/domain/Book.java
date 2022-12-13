package com.design.domain;

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
    public class BookNum{
        private String name;
        private String pub;
        private Timestamp time;
        private Integer num;
    }
}
