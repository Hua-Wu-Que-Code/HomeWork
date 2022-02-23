package com.example.springboot.entity;

import lombok.Data;

@Data
public class UserVO {
    private String name;
    private String token;

    public UserVO(String name, String token) {
        this.name = name;
        this.token = token;
    }
}
