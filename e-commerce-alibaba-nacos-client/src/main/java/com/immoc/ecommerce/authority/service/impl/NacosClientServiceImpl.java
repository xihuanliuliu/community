package com.immoc.ecommerce.authority.service.impl;

import com.alibaba.fastjson.JSON;
import com.immoc.ecommerce.authority.service.NacosClientService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class NacosClientServiceImpl implements NacosClientService {
    private static final Logger logger = LoggerFactory.getLogger(NacosClientServiceImpl.class);

    private final DiscoveryClient discoveryClient;

    public NacosClientServiceImpl(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }


    @Override
    public List<ServiceInstance> getNacosServiceInstance(String serviceId) {
        logger.info("request nacos client to get service instance info : {}", serviceId);
        return discoveryClient.getInstances(serviceId);
    }

    @Override
    public List<List<ServiceInstance>> getNacosServiceInstances(List<String> serviceIds) {
        logger.info("request nacos client to get service instance infos : {}", JSON.toJSONString(serviceIds));
        List<List<ServiceInstance>> lists = new ArrayList<>(serviceIds.size());
//        for (String s : serviceIds) {
//            lists.add(discoveryClient.getInstances(s));
//        }
        serviceIds.forEach(
                s -> lists.add(discoveryClient.getInstances(s))
        );
        return lists;
    }

    /**
     * 使用主角实现 Hystrix 请求合并
     **/
    @Override
    @HystrixCollapser(
            batchMethod = "findNacosClientInfos",  //
            scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
            collapserProperties = {
                    @HystrixProperty(name = "timerDelayInMilliseconds", value = "300")
            }
    )
    public Future<List<ServiceInstance>> findNacosClientInfo(String serviceId) {
        // 系统运行正常，不会走和这个方法
        throw new RuntimeException("This method body should not ne executed!");
    }

    @Override
    public List<List<ServiceInstance>> findNacosClientInfos(List<String> serviceIds) {
        logger.info("coming in find nacos client infos : [{}]", JSON.toJSONString(serviceIds));
        return getNacosServiceInstances(serviceIds);
    }
}
