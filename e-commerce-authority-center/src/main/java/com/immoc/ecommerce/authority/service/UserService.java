package com.immoc.ecommerce.authority.service;

import com.immoc.ecommerce.authority.entity.EcommerceUser;

public interface UserService {

    void saveUser(EcommerceUser ecommerceUser);

    EcommerceUser findByUsername(String username);

    EcommerceUser findByUsernameAndPassword(String username, String password);
}
