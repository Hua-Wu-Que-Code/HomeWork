package com.example.springboot.controller;

import com.example.springboot.entity.Article;
import com.example.springboot.entity.User;
import com.example.springboot.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @CrossOrigin
    @PostMapping("/api/article")
    @ResponseBody
    public Map getArticles(@RequestBody Map<String,String> user) {
        Map map = new HashMap();
        map.put("limit",articleService.findArticle(user.get("id"),user.get("currentPage"),user.get("pageSize")));
        map.put("total",articleService.getAll(user.get("id")));
        return map;
    }
}
