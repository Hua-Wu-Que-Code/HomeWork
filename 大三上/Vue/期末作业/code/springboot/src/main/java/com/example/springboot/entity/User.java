package com.example.springboot.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String passwd;
    private String privilege;
    private String avatar;

    public User(String name, String passwd) {
        this.username = name;
        this.passwd = passwd;
    }
}
