package com.collabdoc.common.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BusinessExceptionTest {

    @Test
    void testExceptionCreation() {
        // 测试基本创建
        BusinessException ex = new BusinessException("USER_NOT_FOUND", "用户不存在");

        assertEquals("USER_NOT_FOUND", ex.getCode());
        assertEquals("用户不存在", ex.getMessage());
        assertNull(ex.getCause());  // 没有原因

        System.out.println("基本异常创建测试通过");
    }

    @Test
    void testExceptionWithCause() {
        // 测试包含原因的异常
        RuntimeException cause = new RuntimeException("数据库连接失败");
        BusinessException ex = new BusinessException(
                "SYSTEM_ERROR",
                "系统异常，请联系管理员",
                cause
        );

        assertEquals("SYSTEM_ERROR", ex.getCode());
        assertEquals("系统异常，请联系管理员", ex.getMessage());
        assertSame(cause, ex.getCause());  // 是同一个原因对象

        System.out.println("带原因的异常创建测试通过");
    }

    @Test
    void testExceptionIsRuntimeException() {
        // 验证确实是RuntimeException的子类
        BusinessException ex = new BusinessException("TEST", "测试");

        assertTrue(ex instanceof RuntimeException);
        System.out.println("异常类型验证通过");
    }
}