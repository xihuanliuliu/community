package com.imooc.ecommerce.util;



public enum ResponseEnum {
    /** success **/
    OK("200", "ok"),
    /** 给前端页面显示的错误 **/
    SHOW_FAIL_MESSAGE("600", "show fail message");

    private final String code;
    private final String message;
    ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ResponseEnum{" + "code='" + code + '\'' + ", msg='" + message + '\'' + "} " + super.toString();
    }
}
