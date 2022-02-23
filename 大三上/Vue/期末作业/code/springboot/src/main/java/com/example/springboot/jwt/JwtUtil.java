package com.example.springboot.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

public class JwtUtil {
    final static String key = "testJwtSaltKey"; //签名
    final static Integer ttl = 24*60*60*1000;   //有效时间

    static public String generateToken( Integer id ) {
        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                //jwt的id
                .setId(id.toString())
                //setIssuedAt用于设置签发时间
                .setIssuedAt(new Date())
                //过期时间：当前系统时间+一整天
                .setExpiration(new Date(System.currentTimeMillis()+ttl))
                //签名算法  key就是自定义的签名
                .signWith(SignatureAlgorithm.HS512, key)
                //拼接
                .compact();
        return token;
    }

    static public Integer parseToken( String token ) {
        /**
         * 通过token进行解析 然后 得到一个id
         */
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return Integer.parseInt(claims.getId());

        /**
         * 标准写法
         */
        /*String token = "";
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(key).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();*/
    }
}