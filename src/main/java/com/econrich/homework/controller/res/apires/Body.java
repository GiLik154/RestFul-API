package com.econrich.homework.controller.res.apires;

import lombok.Getter;

@Getter
public class Body {
    private int totalCount;
    private int numOfRows;
    private int pageNo;
    private Items items;
    private String dataType;
}
