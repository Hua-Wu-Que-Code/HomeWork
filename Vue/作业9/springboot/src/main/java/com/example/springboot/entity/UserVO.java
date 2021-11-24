package com.example.springboot.entity;

import lombok.Data;

@Data
public class UserVO {
    private String name;
    private String token;
    private String privilege;

    public UserVO(String name, String token,String privilege) {
        this.name = name;
        this.token = token;
        this.privilege = privilege;
    }
}
