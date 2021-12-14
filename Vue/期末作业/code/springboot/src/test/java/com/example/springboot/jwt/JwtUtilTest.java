package com.example.springboot.jwt;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    JwtUtil jwtUtil = new JwtUtil();
    @Test
    public void testSign() {
        String s = jwtUtil.generateToken(3);
        System.out.println(s);

        System.out.println(jwtUtil.parseToken(s));
    }

}