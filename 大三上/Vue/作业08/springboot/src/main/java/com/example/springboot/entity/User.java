package com.example.springboot.entity;

import lombok.Data;

@Data
public class User {
    private String name;
    private String passwd;

    public User(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }
}
