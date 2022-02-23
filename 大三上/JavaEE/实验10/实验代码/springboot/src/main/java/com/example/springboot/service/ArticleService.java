package com.example.springboot.service;

import com.example.springboot.entity.Article;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.ArticleMapper;
import com.example.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    public List<Article> findArticle(String uid,String start, String size) {
        return articleMapper.getArticle(uid,(Integer.parseInt(start) - 1) * Integer.parseInt(size),Integer.parseInt(size));
    }

    public int getAll(String uid) {
        return articleMapper.getAll(uid);
    }
}
