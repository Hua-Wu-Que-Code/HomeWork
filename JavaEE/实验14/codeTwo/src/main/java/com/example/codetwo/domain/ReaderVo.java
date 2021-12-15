package com.example.codetwo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ReaderVo {
    public  static final String Table = "t_reader";

    private String name;
    private String address;
    private Integer age;
    private double account;
    private boolean ifUse;
    private Integer sex;
    private Date date;
}
