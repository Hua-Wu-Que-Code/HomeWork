package com.example.springboot.controller;

import com.example.springboot.entity.Result;
import com.example.springboot.mapper.CategoryMapper;
import com.example.springboot.service.BookService;
import com.example.springboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/category")
    @CrossOrigin
    @ResponseBody
    public Result category() {
        if (categoryService.findCategory() != null) {
            return Result.succeed(categoryService.findCategory());
        }
        return Result.fail();
    }

    @Autowired
    BookService bookService;
    @RequestMapping("/category/books")
    @CrossOrigin
    @ResponseBody
    public Result categoryBooks(Integer id) {
        if (bookService.findBooksByCategory(id) != null) {
            return Result.succeed(bookService.findBooksByCategory(id));
        }
        return Result.fail();
    }

    @RequestMapping("/category/waterBooks")
    @CrossOrigin
    @ResponseBody
    public Result categoryBooksWater(Integer id,Integer start,Integer size) {
        return Result.succeed(bookService.findBooksByCategoryWater(id,start,size));
    }



    @RequestMapping("/category/books/details")
    @CrossOrigin
    @ResponseBody
    public Result findBookById(Integer id) {
        if (bookService.findBookById(id) != null) {
            return Result.succeed(bookService.findBookById(id));
        }
        return Result.fail();
    }

    @RequestMapping("/findBookByName")
    @CrossOrigin
    @ResponseBody
    public Result findBookByName(String name) {
        if (bookService.findBookByName(name) != null) {
            return Result.succeed(bookService.findBookByName(name));
        }
        return Result.fail();
    }



}
