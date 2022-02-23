package com.example.springboot.controller;

import com.example.springboot.entity.RespBean;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/api/login")
    @ResponseBody
    public RespBean find(@RequestBody  User user) {
        String accNum = user.getAccountName();
        String passwd = user.getPasswd();
        RespBean respBean;
        User u = userService.findUser(accNum,passwd);
        if (userService.findUser(accNum,passwd) != null) {
            respBean = RespBean.builder().status("200").msg("登录成功").user(u).build();
        } else {
            respBean = RespBean.builder().status("404").msg("登录失败").build();
        }
        return respBean;
    }

    @RequestMapping("/hello")
    public User findUser() {
        return userService.findUser("00000001","admin");
    }


}
