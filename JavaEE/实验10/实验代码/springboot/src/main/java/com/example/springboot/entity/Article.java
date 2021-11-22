package com.example.springboot.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private String id;
    private String text;
    private String uid;
    private String title;
    private String author;
    private String date;
    private String img;
    private String tag;
    private String category;
}
