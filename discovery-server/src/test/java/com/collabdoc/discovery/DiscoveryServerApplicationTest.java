package com.collabdoc.discovery;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 简单的启动测试
 * 验证Spring上下文能否正常加载
 */
@SpringBootTest
public class DiscoveryServerApplicationTest {

    @Test
    void contextLoads() {
        // 如果Spring上下文能正常加载，测试就通过
        System.out.println("Discovery Server上下文加载成功");
    }
}
