package com.example.springboot.service;

import com.example.springboot.entity.Shopping_cart;
import com.example.springboot.entity.Shopping_cart_item;
import com.example.springboot.mapper.Shopping_cartMapper;
import com.example.springboot.mapper.Shopping_cart_itemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
    @Autowired
    Shopping_cart_itemMapper shopping_cart_itemMapper;
    @Autowired
    Shopping_cartMapper shopping_cartMapper;

    public Shopping_cart findShoppingCart(Integer userId){
        Integer shoppingCartId = shopping_cartMapper.findShoppingCartId(userId);
        Shopping_cart cart = shopping_cartMapper.findShoppingCartById(shoppingCartId);
        return  cart;
    }

    public int addItem(Integer userId,Integer bookId) {
        Integer shoppingCartId = shopping_cartMapper.findShoppingCartId(userId);
        List<Shopping_cart_item> items = shopping_cart_itemMapper.findItemsByIdAndCartId(shoppingCartId,bookId);
        if (items.size() != 0) {
            //更新数据
            return shopping_cart_itemMapper.updateItem(shoppingCartId,bookId);
        } else {
            //插入数据
            return shopping_cart_itemMapper.addItem(shoppingCartId,bookId);
        }
    }

}
