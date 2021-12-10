package com.example.code.controller;

import com.example.code.entity.User;
import com.example.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class RedisController {
    @Autowired
    UserService userService;

    @RequestMapping("/{id}")
    public User ForTest(@PathVariable String id) {
        User user = userService.selectUser(id);
        if (user!=null) {
            System.out.println("User Id:"+user.getName());
        } else {
            System.out.println("Can't find user");
        }
        return user;
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        userService.deleteById(id);
        return "delete success";
    }
}
