package com.immoc.ecommerce.authority.controller;

import com.alibaba.fastjson.JSON;
import com.immoc.ecommerce.authority.service.JwtService;
import com.imooc.ecommerce.util.CommonResponse;
import com.imooc.ecommerce.vo.JwtToken;
import com.imooc.ecommerce.vo.UsernameAndPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authority")
public class AuthorityController {
    private static final Logger logger = LoggerFactory.getLogger(AuthorityController.class);

    @Autowired
    private JwtService jwtService;

    /**
     * 从授权中心中获取 token（其实就是登录功能），切返回同一的包装信息
     *
     * @param usernameAndPassword
     * @return
     * @throws Exception
     */
    @PostMapping("/token")
    public CommonResponse<JwtToken> token(@RequestBody UsernameAndPassword usernameAndPassword) throws Exception {
        logger.info("request to get token with param : [{}]", JSON.toJSONString(usernameAndPassword));
        JwtToken jwtToken = new JwtToken();
        jwtToken.setToken(jwtService.generateToken(usernameAndPassword.getUsername(), usernameAndPassword.getPassword()));
        return CommonResponse.success(jwtToken);
    }

    /**
     * 注册用户并返回当前注册用户的token，只返回token
     * @param usernameAndPassword
     * @return
     */
    @PostMapping("/register")
    public JwtToken register(@RequestBody UsernameAndPassword usernameAndPassword) throws Exception {
        logger.info("request to get token with param : [{}]", JSON.toJSONString(usernameAndPassword));
        JwtToken jwtToken = new JwtToken();
        jwtToken.setToken(jwtService.registerUserAndGenerateToken(usernameAndPassword));
        return jwtToken;
    }
}
