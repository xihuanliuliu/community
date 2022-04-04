package com.imooc.ecommerce.exception;

/** 自定义异常 **/
public class AuthException extends RuntimeException{

    public AuthException(String message){
        super(message);
    }

}
