package com.collabdoc.common.dto;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApiResponseTest {

    @Test
    void testSuccessResponse() {
        ApiResponse<String> response = ApiResponse.success("test data");

        assertTrue(response.isSuccess());
        assertEquals("SUCCESS", response.getCode());
        assertEquals("操作成功", response.getMessage());
        assertEquals("test data", response.getData());
        assertNotNull(response.getTimestamp());
    }

    @Test
    void testErrorResponse() {
        ApiResponse<Void> response = ApiResponse.error("ERROR_CODE", "错误信息");

        assertFalse(response.isSuccess());
        assertEquals("ERROR_CODE", response.getCode());
        assertEquals("错误信息", response.getMessage());
        assertNull(response.getData());
    }
}
