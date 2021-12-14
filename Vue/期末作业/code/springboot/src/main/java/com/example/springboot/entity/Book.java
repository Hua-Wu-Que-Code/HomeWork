package com.example.springboot.entity;

import lombok.Data;

@Data
public class Book {
    private int id;
    private String name;
    private String author;
    private String publisher;
    private Double price;
    private String img;
    private String description;
    private Integer category;
}
