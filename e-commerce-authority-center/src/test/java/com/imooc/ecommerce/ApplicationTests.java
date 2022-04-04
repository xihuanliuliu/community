package com.imooc.ecommerce;

import cn.hutool.crypto.digest.MD5;
import com.immoc.ecommerce.authority.AuthorityCenterApplication;
import com.immoc.ecommerce.authority.entity.EcommerceUser;
import com.immoc.ecommerce.authority.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = AuthorityCenterApplication.class)
@RunWith(SpringRunner.class)
public class ApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    public void testSaveAndFind() {
        EcommerceUser ecommerceUser = new EcommerceUser();
        ecommerceUser.setId(3L);
        ecommerceUser.setUsername("zhangjing3");
        ecommerceUser.setPassword("123456");
        ecommerceUser.setSalt("salt");
        userService.saveUser(ecommerceUser);

        EcommerceUser ecommerceUser1 = userService.findByUsername("zhangjing3");
        System.out.println(ecommerceUser1.toString());
    }
}
