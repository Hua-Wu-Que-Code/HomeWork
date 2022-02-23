package com.example.springboot.service;

import com.example.springboot.entity.Book;
import com.example.springboot.entity.Collection;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.mapper.CollectionMapper;
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

    @Autowired
    CollectionMapper collectionMapper;
    public Collection findIfCollected(Integer uid, Integer bid) {
        return collectionMapper.ifCollectedByUserId(uid,bid);
    }

    public int toCollect(Integer uid,Integer bid,double original) {
        return collectionMapper.toCollect(uid, bid, original);
    }

    public int toCancelCollect(Integer uid,Integer bid) {
        return collectionMapper.toCancelCollect(uid, bid);
    }

    public List<Collection> getCollection(Integer uid) {
        return collectionMapper.getAllCollection(uid);
    }
}
