package com.collabdoc.user.controller;

import com.collabdoc.common.dto.ApiResponse;
import com.collabdoc.user.dto.CreateUserRequest;
import com.collabdoc.user.dto.UserDTO;
import com.collabdoc.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * 用户控制器测试
 * 使用@WebMvcTest只测试Web层，不启动完整Spring上下文
 */
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;  // 模拟HTTP请求

    @Autowired
    private ObjectMapper objectMapper;  // JSON转换工具

    @MockBean
    private UserService userService;  // 模拟Service（不测试真实逻辑）

    @Test
    void testCreateUser() throws Exception {
        // prepare test data
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("testuser");
        request.setEmail("user@gmail.com");
        request.setPassword("abcd12345");
        request.setDisplayName("User 1");

        // mock service return result
        UserDTO mockUser = UserDTO.builder()
                .id(1L)
                .username("testuser")
                .email("user@gmail.com")
                .displayName("User 1")
                .build();

        when(userService.createUser(any(CreateUserRequest.class)))
                .thenReturn(mockUser);

        // send POST request
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                // validate response
                .andExpect(status().isOk())  // HTTP 200
                .andExpect(jsonPath("$.success").value(true))  // 响应体中的success字段
                .andExpect(jsonPath("$.data.username").value("testuser"))
                .andExpect(jsonPath("$.data.email").value("user@gmail.com"));
    }
}
