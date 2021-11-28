package com.example.springboot.controller;


import com.example.springboot.entity.Result;
import com.example.springboot.entity.Shopping_cart;
import com.example.springboot.entity.Shopping_cart_item;
import com.example.springboot.jwt.JwtUtil;
import com.example.springboot.mapper.Shopping_cartMapper;
import com.example.springboot.mapper.Shopping_cart_itemMapper;
import com.example.springboot.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ShoppingCartController {

    Integer time = 0;

    @Autowired
    ShoppingCartService shoppingCartService;

    @RequestMapping("/shoppingCart/add")
    @CrossOrigin
    @ResponseBody
    /**
     * id:商品id
     * token:在服务端解码
     */
    public Result addShoppingCartItem(Integer id ,String token) {
        Integer userId = JwtUtil.parseToken(token);
        int flag = shoppingCartService.addItem(userId,id);
        if (flag != 0) {
            return Result.succeed(flag);
        }
        return Result.fail();
    }

    @RequestMapping("/shoppingCart")
    @CrossOrigin
    @ResponseBody
    /**
     * 查询购物车
     */
    public Result initShoppingCart(String token) {
        //得到了用户id
        Integer userId = JwtUtil.parseToken(token);
        Shopping_cart cart = shoppingCartService.findShoppingCart(userId);
        if (cart != null) {
            System.out.println("第"+time+"次购物车的数量是"+cart.getItems().size());
            time++;
            return Result.succeed(cart);
        }

        return Result.fail();

    }
}
