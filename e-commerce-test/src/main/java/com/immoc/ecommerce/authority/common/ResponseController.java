package com.immoc.ecommerce.authority.common;

import com.imooc.ecommerce.exception.AuthException;
import com.imooc.ecommerce.util.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ResponseController {
    private static final Logger logger = LoggerFactory.getLogger(ResponseController.class);
    @GetMapping(path = "/response/success")
    public CommonResponse getResponseInfo() {
        User user = new User();
        user.setAddress("beijing");
        user.setName("zhang");
        user.setAge(12);
        return CommonResponse.success(user);
    }

    @GetMapping(path = "/response/fail")
    public CommonResponse getResponseFailInfo() {
        User user = new User();
        user.setAddress("beijing");
        user.setName("zhang");
        user.setAge(12);
        return CommonResponse.fail("600", user);
    }
    @GetMapping(path = "/response/exception")
    public CommonResponse getResponseExceptionInfo() {
        User user = new User();
        user.setAddress("beijing");
        user.setName("zhang");
        user.setAge(12);
        logger.error("test exception");
        throw new AuthException("抛出异常");
    }
}
