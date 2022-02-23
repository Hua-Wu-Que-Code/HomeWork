package com.example.springboot.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserTrack {
    private Integer id;
    private Integer bid;
    private Integer uid;
    private Date time;
    private Book book;
    private Integer frequency;
}
