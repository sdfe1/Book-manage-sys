package com.zll.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 通用错误码枚举
 */
@Getter
@AllArgsConstructor
public enum CommonErrorCodeEnum {

    NOT_FOUND(404, " 找不到资源"),
    ALREADY_EXISTS(400, "已经存在"),
    UNAUTHORIZED(403, "没有权限访问"),
    INVALID_REQUEST(400, "不合法的请求"),
    ACCOUNT_DISABLED(423, "账户已禁用"),
    CONCURRENT_CONFLICT(409, " 请求与当前资源状态冲突");
    private final Integer code;
    private final String msg;
}
