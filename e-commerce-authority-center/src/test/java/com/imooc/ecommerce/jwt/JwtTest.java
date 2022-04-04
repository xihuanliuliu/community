package com.imooc.ecommerce.jwt;

import com.immoc.ecommerce.authority.AuthorityCenterApplication;
import com.immoc.ecommerce.authority.controller.AuthorityController;
import com.immoc.ecommerce.authority.service.JwtService;
import com.immoc.ecommerce.authority.service.UserService;
import com.imooc.ecommerce.util.TokenParseUtil;
import com.imooc.ecommerce.vo.LoginUserInfo;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = AuthorityCenterApplication.class)
@RunWith(SpringRunner.class)
public class JwtTest {
    private static final Logger logger = LoggerFactory.getLogger(JwtTest.class);

    @Autowired
    private JwtService jwtService;

    @Test
    public void testToken() throws Exception {
        String token = jwtService.generateToken("zhangjing", "" +
                "e10adc3949ba59abbe56e057f20f883e");
        logger.info(" jwt token is : [{}]", token);

        LoginUserInfo userInfo = TokenParseUtil.parseUserInfoFromToken(token);
        System.out.println("user: [ " + userInfo.getUsername() +" ] , id: [ " + userInfo.getId() + " ]");
    }
}
