package com.collabdoc.common.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorCodeTest {

    @Test
    void testErrorCodeProperties() {
        // 测试枚举的基本属性
        assertEquals("U0001", ErrorCode.USER_NOT_FOUND.getCode());
        assertEquals("用户不存在", ErrorCode.USER_NOT_FOUND.getMessage());

        assertEquals("A0002", ErrorCode.UNAUTHORIZED.getCode());
        assertEquals("未授权", ErrorCode.UNAUTHORIZED.getMessage());

        System.out.println("错误码属性测试通过");
    }

    @Test
    void testFromCodeMethod() {
        // 测试根据code查找枚举
        ErrorCode errorCode = ErrorCode.fromCode("U0001");
        assertEquals(ErrorCode.USER_NOT_FOUND, errorCode);

        // 测试不存在的code
        ErrorCode defaultCode = ErrorCode.fromCode("XXXXX");
        assertEquals(ErrorCode.SYSTEM_ERROR, defaultCode);

        System.out.println("fromCode方法测试通过");
    }

    @Test
    void testIsSuccessMethod() {
        // 测试isSuccess方法
        assertTrue(ErrorCode.SUCCESS.isSuccess());
        assertFalse(ErrorCode.USER_NOT_FOUND.isSuccess());

        System.out.println("isSuccess方法测试通过");
    }

    @Test
    void testBusinessExceptionWithErrorCode() {
        // 测试使用ErrorCode创建BusinessException
        BusinessException ex = new BusinessException(ErrorCode.USER_NOT_FOUND);

        assertEquals("U0001", ex.getCode());
        assertEquals("用户不存在", ex.getMessage());

        System.out.println("BusinessException与ErrorCode结合测试通过");
    }
}