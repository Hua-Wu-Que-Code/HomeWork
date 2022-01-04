package com.example.springboot.controller;

import com.alibaba.fastjson.JSONObject;
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
            UserVO userVO = new UserVO(loginUser.getUsername(),JwtUtil.generateToken(loginUser.getId()),loginUser.getPrivilege(),loginUser.getAvatar());
            return Result.succeed(userVO);
        }
        return Result.fail();
    }


    @PostMapping("/toAddUserTrack")
    @CrossOrigin
    @ResponseBody
    public Result addUserTrack(String token,Integer bid) {

        Integer uid = JwtUtil.parseToken(token);
        if (userService.findUserTrack(uid, bid) != null) {
            return Result.succeed(userService.updateUserTrack(uid,bid));
        } else {
            return Result.succeed(userService.updateUserTrack(uid,bid));
        }
    }

    @PostMapping("/toGetUserTrack")
    @CrossOrigin
    @ResponseBody
    public Result getUserTrack(String token) {

        Integer uid = JwtUtil.parseToken(token);
        if (userService.getUserTrack(uid).size() != 0) {
            return Result.succeed(userService.getUserTrack(uid));
        } else return Result.succeed(0);
    }

    @PostMapping("/toAlertAvatar")
    @CrossOrigin
    @ResponseBody
    public Result toAlertAvatar(@RequestBody Object playLoad) {

        System.out.println("我要打印啦");

        System.out.println("我打印完啦");
        return Result.succeed(1);
        /*if (userService.alertAvatar(uid,base) != 0) {
            return Result.succeed("修改成功");
        } else return Result.fail();*/
    }


}
