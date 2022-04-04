package com.imooc.ecommerce.advice;

import com.imooc.ecommerce.util.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResponse<String> handlerCommerceException(HttpServletRequest request, Exception ex) {
        logger.info("test exception response");
        CommonResponse<String> response = new CommonResponse<>();
        response.setMessage(ex.getMessage());
        response.setCode("10001");
        logger.error("commerce service has error: [{}]", ex.getMessage());
        return response;
    }

}
