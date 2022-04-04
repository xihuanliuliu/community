package com.imooc.ecommerce.util;

import com.alibaba.fastjson.JSON;
import com.imooc.ecommerce.constant.CommonConstant;
import com.imooc.ecommerce.vo.LoginUserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;

public class TokenParseUtil {


    public static LoginUserInfo parseUserInfoFromToken(String token) throws Exception {
        if(token == null){
            return null;
        }
        Jws<Claims> claimsJws = parseToken(token, getPublicKey());
        Claims body = claimsJws.getBody();
        // 如果Token 已经过期了，返回null
        if(body.getExpiration().before(Calendar.getInstance().getTime())){
            return null;
        }

        // 返回token中保存的用户信息
        LoginUserInfo userInfo = JSON.parseObject(body.get(CommonConstant.JWT_USER_INFO_KEY).toString(), LoginUserInfo.class);
        return userInfo;
    }

    /**
     * 通过公钥去解析token
     * @param token
     * @param publicKey
     * @return
     */
    public static Jws<Claims> parseToken(String token, PublicKey publicKey){
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    /**
     * 根据本地存储的公钥获取 PublicKey
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey() throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
                new BASE64Decoder().decodeBuffer(CommonConstant.PUBLIC_KEY)
        );
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }

}
