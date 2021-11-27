package com.example.springboot.mapper;

import com.example.springboot.entity.Shopping_cart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Shopping_cartMapper {
    Integer findShoppingCartId(Integer userId);

    Shopping_cart findShoppingCartById(Integer cartId);
}