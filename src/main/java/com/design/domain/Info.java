package com.design.domain;

import lombok.Data;

@Data
public class Info {
    String date;
    int number;
    @Data
    public static class BookRank{
        String name;
        Integer number;
    }


}
