package com.example.springboot.service;

import com.example.springboot.entity.Category;
import com.example.springboot.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    public List<Category> findCategory() {
        return categoryMapper.findCategory();
    }
}
