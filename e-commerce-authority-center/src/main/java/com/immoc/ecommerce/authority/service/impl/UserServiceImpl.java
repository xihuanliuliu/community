package com.immoc.ecommerce.authority.service.impl;

import com.immoc.ecommerce.authority.service.UserService;
import com.immoc.ecommerce.authority.entity.EcommerceUser;
import com.immoc.ecommerce.authority.mapper.EcommerceUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EcommerceUserMapper ecommerceUserMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUser(EcommerceUser ecommerceUser) {
        ecommerceUserMapper.save(ecommerceUser);
    }

    @Override
    public EcommerceUser findByUsername(String username){
        return ecommerceUserMapper.findByUsername(username);
    }

    @Override
    public EcommerceUser findByUsernameAndPassword(String username, String password) {
        return ecommerceUserMapper.findByUsernameAndPassword(username, password);
    }

}
