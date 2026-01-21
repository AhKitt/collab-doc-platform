package com.collabdoc.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 用户服务主类
 *
 * @EnableDiscoveryClient 注解：
 * 1. 启用服务发现客户端功能
 * 2. 自动向Eureka注册自己
 * 3. 可以从Eureka获取其他服务地址
 */
@SpringBootApplication
@EnableDiscoveryClient // 启用服务发现
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
