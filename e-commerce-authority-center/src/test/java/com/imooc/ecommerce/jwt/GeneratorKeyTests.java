package com.imooc.ecommerce.jwt;

import com.immoc.ecommerce.authority.AuthorityCenterApplication;
import org.checkerframework.checker.units.qual.K;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

@SpringBootTest(classes = AuthorityCenterApplication.class)
@RunWith(SpringRunner.class)
public class GeneratorKeyTests {

    @Test
    public void generatorKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        //生成公钥和私钥
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //获取公钥和私钥对象
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        System.out.println("public key: " + publicKey);
        System.out.println("private key: " + privateKey);


    }
}
