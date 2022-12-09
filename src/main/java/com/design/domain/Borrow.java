package com.design.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;


@Data
public class Borrow {
    private Integer SN;
    private Integer id;
    private String sno;
    private Date borrow_time;
    private Date return_time;
    private Integer fine;
}
