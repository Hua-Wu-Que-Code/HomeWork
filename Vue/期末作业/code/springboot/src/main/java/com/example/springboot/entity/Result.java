package com.example.springboot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    final static int SUCCESS = 100;
    final static int FAILURE = 101;
    final static int NO_PERMISSION = 102;
    private Integer code;
    private String message;
    private Object data;

    Result( Integer code, String message, Object data ){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result succeed(Object ret) {
        return new Result(Result.SUCCESS, "OK", ret);
    }
    public static Result succeed() {
        return new Result(Result.SUCCESS, "OK", null);
    }
    public static Result fail() {
        return new Result(Result.FAILURE, "调用失败", null);
    }
    public static Result fail( String s) {
        return new Result(Result.FAILURE, s, null);
    }
    public static Result fail( String s, Object ret) {
        return new Result(Result.FAILURE, s, ret);
    }
    public static Result permissionDeny() {
        return new Result(Result.NO_PERMISSION,"没有权限",null);
    }
}