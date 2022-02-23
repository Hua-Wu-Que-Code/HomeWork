package com.example.springboot.mapper;

import com.example.springboot.entity.UserTrack;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserTrackMapper {

    @Insert("insert into user_track(bid,uid) values (#{bid},#{uid})")
    int addUserTrack(Integer uid,Integer bid);

    @Select("select * from user_track where uid =#{uid} and bid=#{bid}")
    UserTrack findUserTrack(Integer uid, Integer bid);

    @Update("update user_track set frequency = frequency+1 where uid =#{uid} and bid=#{bid}")
    int updateUserTrack(Integer uid,Integer bid);

    List<UserTrack> getUserTrack(Integer uid);
}
