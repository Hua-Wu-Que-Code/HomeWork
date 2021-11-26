package com.example.springboot.service;


import com.example.springboot.mapper.UserMapper;
import com.example.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User findUser(User user) {
        String name = user.getName();
        String passwd = user.getPasswd();
        return userMapper.findUser(name,passwd);
    }
}
