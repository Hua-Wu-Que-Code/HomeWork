package com.example.springboot.controller;


import com.example.springboot.entity.Result;
import com.example.springboot.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @RequestMapping("/shoppingCart/add")
    @CrossOrigin
    @ResponseBody

    public Result addShoppingCartItem(Integer id) {
        return Result.fail();
    }
}
