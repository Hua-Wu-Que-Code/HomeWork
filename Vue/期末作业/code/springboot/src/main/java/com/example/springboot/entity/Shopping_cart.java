package com.example.springboot.entity;

import lombok.Data;

import java.util.List;

@Data
public class Shopping_cart {

    private Integer id;


    private Integer userId;


    private Double total;

    private List<Shopping_cart_item> items;

}