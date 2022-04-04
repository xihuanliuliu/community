package com.immoc.ecommerce.authority.mapper;

import com.immoc.ecommerce.authority.entity.EcommerceUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EcommerceUserMapper {

    EcommerceUser findByUsername(String username);

    EcommerceUser findByUsernameAndPassword(String username, String password);

    void updatePassword(String username, String newPassword);

    /**
     * 保存
     *
     * @param ecommerceUser
     */
    void save(@Param("ecommerceUser") EcommerceUser ecommerceUser);
}
