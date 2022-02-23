package com.example.springboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{name} and passwd=#{passwd}")
    User findUser(String name,String passwd);

    @Insert("insert into user(avatar) values (#{avatar}) where id = #{uid}")
    int addAvatar(Integer uid,String avatar);

    @Update("update user set avatar =#{avatar} where id ={uid}")
    int alertAvatar(Integer uid,String avatar);

    @Select("select * from user where id=#{uid}")
    User queryUser(Integer uid);

}
