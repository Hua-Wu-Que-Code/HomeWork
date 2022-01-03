package com.example.springboot.entity;

import lombok.Data;

@Data
public class Shopping_cart_item {

    private Integer id;

    private Integer shoppingCartId;

    private Integer bookId;

    private Integer num;


    //每一项纪录有包含有一个对象
    private Book book;


}