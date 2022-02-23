package com.example.code.service;

import com.example.code.entity.User;
import com.example.code.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "users")
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Cacheable(key = "#p0")
    public User selectUser(String id) {
        System.out.println("select from datebase");
        return userMapper.findById(id);
    }

    @CacheEvict(key = "#p0",allEntries = true)
    public void deleteById(String id) {
        System.out.println("delete");
        userMapper.deleteById(id);
    }
}
