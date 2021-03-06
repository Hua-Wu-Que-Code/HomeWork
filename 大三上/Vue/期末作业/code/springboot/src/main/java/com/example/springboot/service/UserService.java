package com.example.springboot.service;



import com.example.springboot.mapper.UserMapper;
import com.example.springboot.mapper.UserTrackMapper;
import com.example.springboot.entity.User;
import com.example.springboot.entity.UserTrack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User findUser(User user) {
        String name = user.getUsername();
        String passwd = user.getPasswd();
        return userMapper.findUser(name,passwd);
    }

    @Autowired
    UserTrackMapper userTrackMapper;

    public int addUserTrack(Integer uid,Integer bid) {
        return userTrackMapper.addUserTrack(uid,bid);
    }

    public UserTrack findUserTrack(Integer uid, Integer bid) {
        return userTrackMapper.findUserTrack(uid, bid);
    }

    public int updateUserTrack(Integer uid,Integer bid) {
        return userTrackMapper.updateUserTrack(uid, bid);
    }

    public List<UserTrack> getUserTrack(Integer uid) {return userTrackMapper.getUserTrack(uid);}

    public int alertAvatar(Integer uid,String avatar) {
        User user = userMapper.queryUser(uid);
        if (user.getAvatar() != null) {
            return userMapper.alertAvatar(uid,avatar);
        } else {
            return userMapper.addAvatar(uid,avatar);
        }
    }
}
