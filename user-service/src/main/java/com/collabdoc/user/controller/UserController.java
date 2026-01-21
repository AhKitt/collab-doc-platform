package com.collabdoc.user.controller;

import com.collabdoc.common.dto.ApiResponse;
import com.collabdoc.user.dto.CreateUserRequest;
import com.collabdoc.user.dto.UserDTO;
import com.collabdoc.user.entity.User;
import com.collabdoc.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 创建用户
     * POST /api/users
     */
    @PostMapping
    public ApiResponse<UserDTO> createUser(@Valid @RequestBody CreateUserRequest request) {
        log.info("收到创建用户请求: {}", request.getUsername());
        UserDTO user = userService.createUser(request);
        return ApiResponse.success(user);
    }

    /**
     * 获取所有用户
     * GET /api/users
     */
    @GetMapping
    public ApiResponse<List<UserDTO>> getAllUsers() {
        log.info("收到获取所有用户请求");
        List<UserDTO> users = userService.getAllUsers();
        return ApiResponse.success(users);
    }

    /**
     * 根据ID获取用户
     * GET /api/users/{id}
     */
    @GetMapping("/{id}")
    public ApiResponse<UserDTO> getUserById(@PathVariable Long id) {
        log.info("收到获取用户请求，ID: {}", id);
        UserDTO user = userService.getUserById(id);
        return ApiResponse.success(user);
    }

    /**
     * 根据用户名获取用户
     * GET /api/users/by-username/{username}
     */
    @GetMapping("/by-username/{username}")
    public ApiResponse<UserDTO> getUserByUsername(@PathVariable String username) {
        log.info("收到获取用户请求，用户名: {}", username);
        UserDTO user = userService.getUserByUsername(username);
        return ApiResponse.success(user);
    }
}
