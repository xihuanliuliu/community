package com.immoc.ecommerce.authority.service;

import com.imooc.ecommerce.vo.UsernameAndPassword;

public interface JwtService {

    /**
     * 生成token，使用默认的时间
     */
    String generateToken(String username, String password) throws Exception;
    /**
     * 生成token
     */
    String generateToken(String username, String password, int expire) throws Exception;
    /**
     * 解析token
     */
    String registerUserAndGenerateToken(UsernameAndPassword usernameAndPassword) throws Exception;
}
