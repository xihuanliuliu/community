package com.immoc.ecommerce.authority.common;

import org.springframework.stereotype.Service;

@Service("myService")
public class MyService implements TestService {

    @Override
    public void test() {
        System.out.println("my service test");
    }
}
