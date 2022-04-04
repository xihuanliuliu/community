package com.imooc.ecommerce.util;

import org.springframework.util.Base64Utils;

import java.security.MessageDigest;


public class DigestUtil {


    public static String generatePassword(String salt, String inputPassword) {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(salt.substring(0, 8));
            builder.append(inputPassword);
            builder.append(salt.substring(8));
            byte[] md5Bytes = MessageDigest.getInstance("md5").digest(builder.toString().getBytes());
            return Base64Utils.encodeToString(md5Bytes);
        } catch (Exception e) {
            throw new RuntimeException("密码加密异常");
        }
    }


}


