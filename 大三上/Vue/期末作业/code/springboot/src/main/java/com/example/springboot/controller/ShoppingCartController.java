package com.example.springboot.controller;


import com.example.springboot.entity.Book;
import com.example.springboot.entity.Result;
import com.example.springboot.entity.Shopping_cart;
import com.example.springboot.jwt.JwtUtil;
import com.example.springboot.service.BookService;
import com.example.springboot.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ShoppingCartController {


    @Autowired
    ShoppingCartService shoppingCartService;

    @RequestMapping("/shoppingCart/add")
    @CrossOrigin
    @ResponseBody
    /**
     * id:商品id
     * token:在服务端解码
     * 添加购物车
     */
    public Result addShoppingCartItem(Integer id ,String token) {
        Integer userId = JwtUtil.parseToken(token);
        int flag = shoppingCartService.addItem(userId,id);
        if (flag != 0) {
            return Result.succeed(flag);
        }
        return Result.fail();
    }

    @RequestMapping("/shoppingCart/delete")
    @CrossOrigin
    @ResponseBody
    /**
     * id:商品id
     * token:在服务端解码
     * 删除购物车
     */
    public Result deleteShoppingCartItem(Integer id ,String token) {
        Integer userId = JwtUtil.parseToken(token);
        int flag = shoppingCartService.deleteItem(userId,id);
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
        return Result.succeed(cart);

    }


    @Autowired
    BookService bookService;
    @RequestMapping("/getBuyBooks")
    @CrossOrigin
    @ResponseBody
    public Result getBuyBooks(String buys) {
        System.out.println("开始打印啦");
        String[] book = buys.split(",");
        List<Book> bookList = new ArrayList<>();
        for (String b:book) {
            Integer bid = Integer.parseInt(b);
            bookList.add(bookService.findBookById(bid));
        }
        return Result.succeed(bookList);
    }
}
