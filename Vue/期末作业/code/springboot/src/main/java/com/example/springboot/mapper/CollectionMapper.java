package com.example.springboot.mapper;

import com.example.springboot.entity.Collection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectionMapper {

    @Select("select * from user_collection where uid =#{uid} and bid=#{bid}")
    Collection ifCollectedByUserId(Integer uid,Integer bid);

    @Insert(" insert into user_collection(bid,uid,original) values (#{bid},#{uid},#{original})")
    int toCollect(Integer uid,Integer bid, double original);

    @Delete("delete from user_collection where uid =#{uid} and bid=#{bid}")
    int toCancelCollect(Integer uid,Integer bid);

    List<Collection> getAllCollection(Integer uid);
}
