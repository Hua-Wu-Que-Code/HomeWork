package com.example.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.springboot.entity.User;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{name} and passwd=#{passwd}")
    User findUser(String name,String passwd);
}
