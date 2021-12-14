package com.example.springboot.service;

import com.example.springboot.entity.Book;
import com.example.springboot.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Book> findBooksByCategoryWater(Integer id,Integer start,Integer size) {
        if (id == 0) {
            List<Book> books = bookMapper.findBooksAll();
            if ((start-1)*size >= books.size()) {
                return null;
            } else {
                List<Book> returnBooks = books.subList((start - 1) * size, start * size);
                return returnBooks;
            }
        } else {
            return bookMapper.findBooksByCategoryWater(id,(start-1)*size,size);
        }

    }

    public Book findBookById(Integer id) {
        return bookMapper.findBooksById(id);
    }
    public List<Book> findBookByName(String name) {
        return bookMapper.findBookByName(name);
    }

}
