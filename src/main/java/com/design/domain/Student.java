package com.design.domain;

import lombok.Data;

@Data
public class Student {
    private String sno;
    private String name;
    private String dep;
    private String pro;
    private int limit_num;
    private int limit_day;
}
