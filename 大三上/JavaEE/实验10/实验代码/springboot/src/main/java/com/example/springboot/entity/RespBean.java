package com.example.springboot.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespBean {
    private String status;
    private String msg;
    private User user;
}
