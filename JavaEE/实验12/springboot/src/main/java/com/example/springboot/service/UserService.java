package com.example.springboot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User findUser(User user) {
        String name = user.getUsername();
        String passwd = user.getPasswd();
        return userMapper.findUser(name,passwd);
    }
}
