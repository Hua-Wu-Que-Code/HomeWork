package com.example.springboot.mapper;

import com.example.springboot.entity.Shopping_cart_item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Shopping_cart_itemMapper {
    List<Shopping_cart_item> findItemsById(Integer shoppingCartId);

    List<Shopping_cart_item> findItemsByIdAndCartId(Integer shoppingCartId,Integer bookId);

    int addItem(Integer shoppingCartId,Integer bookId);
    int updateItem(Integer shoppingCartId,Integer bookId);
    int deleteItem(Integer shoppingCartId,Integer bookId);
}