package com.example.springboot.mapper;

import com.example.springboot.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    List<Article> getArticle(String uid,int start,int size);

    int getAll(String uid);
}
