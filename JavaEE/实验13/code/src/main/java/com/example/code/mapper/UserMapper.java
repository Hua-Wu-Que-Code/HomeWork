package com.example.code.mapper;

import com.example.code.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from cache_user where id=#{id}")
    User findById(String id);

    @Delete("delete from cache_user where id=#{id}")
    int deleteById(String id);
}
