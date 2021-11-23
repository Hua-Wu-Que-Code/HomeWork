package com.example.springboot.controller;

import com.example.springboot.entity.Result;
import com.example.springboot.entity.User;
import com.example.springboot.entity.UserVO;
import com.example.springboot.jwt.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
public class UserController {

    private final Integer ID = 5;
    private final String USERNAME = "花无缺";
    private final String PASSWD = "123456";

    @PostMapping("/login")
    @CrossOrigin
    @ResponseBody
    public Result login(@RequestBody User user) {

        if (user.getName().equals(USERNAME) && user.getPasswd().equals(PASSWD)) {
            //添加token
            UserVO vo = new UserVO(user.getName(),JwtUtil.generateToken(ID));
            return Result.succeed(vo);
        }
        return Result.fail();
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}
