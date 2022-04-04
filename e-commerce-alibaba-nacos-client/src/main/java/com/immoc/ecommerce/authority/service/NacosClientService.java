package com.immoc.ecommerce.authority.service;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
import java.util.concurrent.Future;

public interface NacosClientService {

    List<ServiceInstance> getNacosServiceInstance(String serviceId);

    List<List<ServiceInstance>> getNacosServiceInstances(List<String> serviceIds);

    Future<List<ServiceInstance>> findNacosClientInfo(String serviceId);

    List<List<ServiceInstance>> findNacosClientInfos(List<String> serviceIds);

}
