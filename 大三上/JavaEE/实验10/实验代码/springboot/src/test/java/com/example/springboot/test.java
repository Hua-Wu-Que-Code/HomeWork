package com.example.springboot;

import java.util.UUID;

public class test {
    public static void main(String[] args) {

        for (int i = 0;i < 10;i++)
            System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
    }
}
