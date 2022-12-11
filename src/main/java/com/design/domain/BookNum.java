package com.design.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class BookNum {

    private String name;
    private Date time;
    private String pub;
    private String locate;
    private Integer num;
}
