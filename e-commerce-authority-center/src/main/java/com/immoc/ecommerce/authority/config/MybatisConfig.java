package com.immoc.ecommerce.authority.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = {"com.immoc.ecommerce.authority.mapper"})
@Configuration
public class MybatisConfig {
}
