package com.example.code.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private Date date;
    private boolean if_use;
}
