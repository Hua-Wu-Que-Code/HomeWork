package com.example.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookMapper bookMapper;
    public List<Book> findBooksByCategory(Integer id) {
        if (id == 0) {
            return bookMapper.findBooksAll();
        } else {
            return bookMapper.findBooksByCategory(id);
        }

    }

    public Book findBookById(Integer id) {
        return bookMapper.findBooksById(id);
    }
}
