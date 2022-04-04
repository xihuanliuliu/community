package com.imooc.ecommerce;

import com.immoc.ecommerce.authority.WebApplication;
import com.immoc.ecommerce.authority.common.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = WebApplication.class)
@RunWith(SpringRunner.class)
public class UserTest {

//    @Resource(type = YourService.class)
//    @Autowired
//    @Qualifier("myService")
    @Resource(name = "yourService")
    private TestService testService;

    @Test
    public void setTestService() {
        testService.test();
    }
}
