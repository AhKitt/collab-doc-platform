package com.collabdoc.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册中心主类
 *
 * @EnableEurekaServer 注解：启用Eureka服务器功能
 * 作用：
 * 1. 启动一个Eureka服务注册中心
 * 2. 提供REST API供服务注册和发现
 * 3. 管理服务实例的心跳和状态
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApplication.class, args);
    }
}
