package com.immoc.ecommerce.authority.service.impl;

import com.alibaba.fastjson.JSON;
import com.immoc.ecommerce.authority.constant.AuthorityConstant;
import com.immoc.ecommerce.authority.entity.EcommerceUser;
import com.immoc.ecommerce.authority.mapper.EcommerceUserMapper;
import com.immoc.ecommerce.authority.service.JwtService;
import com.immoc.ecommerce.authority.service.UserService;
import com.imooc.ecommerce.constant.CommonConstant;
import com.imooc.ecommerce.vo.LoginUserInfo;
import com.imooc.ecommerce.vo.UsernameAndPassword;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import javax.xml.crypto.Data;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class JwtServiceImpl implements JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

    @Autowired
    private EcommerceUserMapper userMapper;

    @Override
    public String generateToken(String username, String password) throws Exception {

        return generateToken(username, password, 0);
    }

    @Override
    public String generateToken(String username, String password, int expire) throws Exception {
        // 查询用户
        EcommerceUser ecommerceUser = userMapper.findByUsernameAndPassword(username, password);
        if (ecommerceUser == null) {
            logger.error("can not find user : [{}], [{}]", username, password);
            return null;
        }
        // Token塞入对象
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setId(ecommerceUser.getId());
        loginUserInfo.setUsername(ecommerceUser.getUsername());

        if (expire <= 0) {
            expire = AuthorityConstant.DEFAULT_EXPIRE_DAY;
        }

        // 计算超时时间
        ZonedDateTime zonedDateTime = LocalDateTime.now().plus(expire, ChronoUnit.DAYS)
                .atZone(ZoneId.systemDefault());
        Date expireDate = Date.from(zonedDateTime.toInstant());

        return Jwts.builder()
                // jwt payload --> K V
                .claim(CommonConstant.JWT_USER_INFO_KEY, JSON.toJSONString(loginUserInfo))
                // jwt id
                .setId(UUID.randomUUID().toString())
                // jwt 过期时间
                .setExpiration(expireDate)
                // jwt 签名
                .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public String registerUserAndGenerateToken(UsernameAndPassword usernameAndPassword) throws Exception {
        // 用户是否存在
        EcommerceUser oldUser = userMapper.findByUsername(usernameAndPassword.getUsername());
        if (oldUser != null){
            logger.error("user [{}] is register", usernameAndPassword.getUsername());
        }

        EcommerceUser ecommerceUser = new EcommerceUser();
        ecommerceUser.setUsername(usernameAndPassword.getUsername());
        ecommerceUser.setPassword(usernameAndPassword.getPassword());
        ecommerceUser.setExtraInfo("{}");
        ecommerceUser.setSalt(UUID.randomUUID().toString());

        // 注册一个新用户
        userMapper.save(ecommerceUser);
        logger.info("user [{}] register success", usernameAndPassword.getUsername());

        // 生成token
        return generateToken(usernameAndPassword.getUsername(), usernameAndPassword.getPassword());
    }


    /**
     * <h2>根据本地存储的私钥获取到 PrivateKey 对象</h2>
     * */
    private PrivateKey getPrivateKey() throws Exception {

        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                new BASE64Decoder().decodeBuffer(AuthorityConstant.PRIVATE_KEY));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(priPKCS8);
    }
}
