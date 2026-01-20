package com.collabdoc.common.exception;

/**
 * 错误码枚举
 */
public enum ErrorCode {
    // 通用错误
    SUCCESS("00000", "成功"),
    SYSTEM_ERROR("B0001", "系统异常"),
    PARAM_VALIDATION_ERROR("A0001", "参数校验失败"),
    UNAUTHORIZED("A0002", "未授权"),
    FORBIDDEN("A0003", "禁止访问"),
    RESOURCE_NOT_FOUND("A0004", "资源不存在"),

    // 用户相关
    USER_NOT_FOUND("U0001", "用户不存在"),
    USERNAME_EXISTS("U0002", "用户名已存在"),
    EMAIL_EXISTS("U0003", "邮箱已存在"),
    INVALID_CREDENTIALS("U0004", "用户名或密码错误"),

    // 文档相关
    DOCUMENT_NOT_FOUND("D0001", "文档不存在"),
    NO_PERMISSION("D0002", "无权限操作"),

    // 协同编辑相关
    OPERATION_CONFLICT("C0001", "操作冲突");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据错误码查找枚举
     */
    public static ErrorCode fromCode(String code) {
        for (ErrorCode errorCode : values()) {
            if (errorCode.code.equals(code)) {
                return errorCode;
            }
        }
        return SYSTEM_ERROR; // 默认返回系统错误
    }

    /**
     * 判断是否成功
     */
    public boolean isSuccess() {
        return this == SUCCESS;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
