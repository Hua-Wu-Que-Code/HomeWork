package com.example.springboot.entity;

import lombok.Data;

@Data
public class UserVO {
    private String name;
    private String token;
    private String privilege;
    private String avatar;

    public UserVO(String name, String token,String privilege,String avatar) {
        this.name = name;
        this.token = token;
        this.privilege = privilege;
        this.avatar = avatar;
    }
}
