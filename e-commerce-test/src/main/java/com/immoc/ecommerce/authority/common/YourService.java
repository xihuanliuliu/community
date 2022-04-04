package com.immoc.ecommerce.authority.common;

import org.springframework.stereotype.Service;

@Service("yourService")
public class YourService implements TestService {
    @Override
    public void test() {
        System.out.println("your service test");
    }
}
