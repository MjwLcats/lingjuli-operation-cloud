package com.lingjuli.identity.domain;

import com.lingjuli.foundation.core.error.ErrorCode;

public enum AuthErrorCode implements ErrorCode {
    INVALID_CREDENTIALS("AUTH-LOGIN-001", "账号或密码错误"),
    ACCOUNT_DISABLED("AUTH-LOGIN-002", "账号已停用，请联系管理员"),
    INVALID_SESSION("AUTH-SESSION-001", "登录状态无效或已过期");

    private final String code;
    private final String message;

    AuthErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
