package com.example.springboot.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String passwd;
    private String privilege;

    public User(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }
}
