package com.example.springboot.entity;

import lombok.Data;

@Data
public class User {
    private String id;
    private String accountName;
    private String passwd;
    private String nickName;
    private Integer authority;

}
