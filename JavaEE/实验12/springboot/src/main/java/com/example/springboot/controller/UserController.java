package com.example.springboot.controller;

import com.example.springboot.entity.Result;
import com.example.springboot.entity.User;
import com.example.springboot.entity.UserVO;
import com.example.springboot.jwt.JwtUtil;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    @CrossOrigin
    @ResponseBody
    public Result login(@RequestBody User user) {
        User loginUser = userService.findUser(user);
        if (loginUser != null) {
            UserVO userVO = new UserVO(loginUser.getUsername(),JwtUtil.generateToken(loginUser.getId()),loginUser.getPrivilege());
            return Result.succeed(userVO);
        }
        return Result.fail();
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}
