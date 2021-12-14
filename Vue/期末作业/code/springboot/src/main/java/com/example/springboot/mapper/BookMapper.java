package com.example.springboot.mapper;

import com.example.springboot.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select * from book where category =#{id}")
    List<Book> findBooksByCategory(Integer id);

    @Select("select * from book where category =#{id} limit #{start},#{size}")
    List<Book> findBooksByCategoryWater(Integer id,Integer start,Integer size);

    @Select("select * from book where id =#{id}")
    Book findBooksById(Integer id);

    @Select("select * from book ")
    List<Book> findBooksAll();


    @Select("select * from book where name like '%${name}%'")
    List<Book> findBookByName(String name);
}
