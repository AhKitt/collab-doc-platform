package com.collabdoc.user.service;

import com.collabdoc.common.exception.BusinessException;
import com.collabdoc.common.exception.ErrorCode;
import com.collabdoc.user.dto.CreateUserRequest;
import com.collabdoc.user.dto.UserDTO;
import com.collabdoc.user.entity.User;
import com.collabdoc.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务
 */
@Slf4j
@Service
@RequiredArgsConstructor // 自动注入final字段
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserDTO createUser(CreateUserRequest request) {
        log.info("创建用户: {}", request.getUsername());

        // 检查用户名是否已存在
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException(ErrorCode.USERNAME_EXISTS);
        }

        // 检查邮箱是否已存在
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException(ErrorCode.EMAIL_EXISTS);
        }

        // 创建用户实体
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .displayName(request.getDisplayName())
                .avatar("default-avatar.png")
                .build();

        // 保存到数据库
        User savedUser = userRepository.save(user);
        log.info("用户创建成功，ID: {}", savedUser.getId());

        return convertToDTO(savedUser);
    }

    /**
     * 根据ID获取用户
     */
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        return convertToDTO(user);
    }

    /**
     * 根据用户名获取用户
     */
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        return convertToDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * 实体转DTO
     */
    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }
}
