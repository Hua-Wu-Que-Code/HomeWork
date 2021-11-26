package com.example.springboot.service;

import com.example.springboot.mapper.Shopping_cartMapper;
import com.example.springboot.mapper.Shopping_cart_itemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    @Autowired
    Shopping_cart_itemMapper shopping_cart_itemMapper;
    @Autowired
    Shopping_cartMapper shopping_cartMapper;


}
