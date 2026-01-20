package com.collabdoc.common.util;

import java.util.UUID;

/**
 * ID生成工具类
 */
public class IdGenerator {

    /**
     * ID生成工具类
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成文档ID
     */
    public static String generateDocumentId() {
        return "doc_" + generateUUID();
    }

    /**
     * 生成用户ID
     */
    public static String generateUserId() {
        return "user_" + generateUUID();
    }
}
