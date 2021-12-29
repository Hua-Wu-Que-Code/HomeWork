package com.example.springboot.entity;

import lombok.Data;

import java.util.List;

@Data
public class Collection {
    private Integer id;
    private Integer bid;
    private Integer uid;

    private Book book;
}
