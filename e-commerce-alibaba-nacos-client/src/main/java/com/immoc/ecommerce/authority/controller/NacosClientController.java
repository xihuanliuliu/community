package com.immoc.ecommerce.authority.controller;

import com.immoc.ecommerce.authority.service.NacosClientService;
import com.imooc.ecommerce.util.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nacoscleint")
public class NacosClientController {
    private static final Logger logger = LoggerFactory.getLogger(NacosClientController.class);

    @Autowired
    private NacosClientService nacosClientService;


    @GetMapping("")
    public CommonResponse<List<ServiceInstance>> logNacosClientInfo(@RequestParam(defaultValue = "e-commerce-nacos-client") String serviceId) {
        logger.info("coming in log nacos client info: {}", serviceId);
        return CommonResponse.success(nacosClientService.getNacosServiceInstance(serviceId));
    }

}
