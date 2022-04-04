package com.imooc.ecommerce.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * <h1>通用响应对象定义</h1>
 * {
 *     "code": 0,
 *     "message": "",
 *     "data": {}
 * }
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(CommonResponse.class);


    /** 错误码 */
    private String code;

    /** 错误消息 */
    private String message;

    /** 泛型响应数据 */
    private T Data;


    /** success **/
    public static <T> CommonResponse<T> success(){
        CommonResponse response = new CommonResponse();
        response.setMessage(ResponseEnum.OK.getMessage());
        response.setCode(ResponseEnum.OK.getCode());
        return response;
    }

    public static <T> CommonResponse<T> success(T data){
        CommonResponse<T> response = new CommonResponse<>();
        response.setData(data);
        response.setCode(ResponseEnum.OK.getCode());
        response.setMessage(ResponseEnum.OK.getMessage());
        return response;
    }

    /** fail **/
    public static <T> CommonResponse<T> showFailMsg(String msg){
        logger.error(msg);
        CommonResponse<T> response = new CommonResponse<>();
        response.setMessage(msg);
        response.setCode(ResponseEnum.SHOW_FAIL_MESSAGE.getCode());
        return response;
    }

    public static <T> CommonResponse<T> fail(ResponseEnum responseEnum){
        logger.error(responseEnum.toString());
        CommonResponse<T> response = new CommonResponse<>();
        response.setMessage(response.getMessage());
        response.setCode(responseEnum.getCode());
        return response;
    }

    public static <T> CommonResponse<T> fail(ResponseEnum responseEnum, T data){
        logger.error(responseEnum.toString());
        CommonResponse<T> response = new CommonResponse<>();
        response.setMessage(response.getMessage());
        response.setCode(responseEnum.getCode());
        response.setData(data);
        return response;
    }
    public static <T> CommonResponse<T> fail(String code, T data){
        CommonResponse<T> response = new CommonResponse<>();
        response.setMessage(response.getMessage());
        response.setCode(code);
        response.setData(data);
        return response;
    }

    public static <T> CommonResponse<T> transform(CommonResponse<?> oldCommonResponse){
        logger.error(oldCommonResponse.toString());
        CommonResponse<T> response = new CommonResponse<>();
        response.setMessage(oldCommonResponse.getMessage());
        response.setCode(oldCommonResponse.getCode());
        return response;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", Data=" + Data +
                '}';
    }
}
