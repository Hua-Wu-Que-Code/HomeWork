package com.example.springboot.mapper;

import com.example.springboot.entity.Collection;
import org.apache.ibatis.annotations.Select;

public interface CollectionMapper {
    @Select("select * from user_collection where uid =#{uid}")
    Collection ifCollectedByUserId(Integer uid);
}
