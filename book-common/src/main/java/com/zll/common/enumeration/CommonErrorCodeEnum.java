package com.zll.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorCodeEnum {

    NOT_FOUND(404, " 找不到资源"),
    ALREADY_EXISTS(409, " 已经存在"),
    UNAUTHORIZED(403, "没有权限访问"),
    INVALID_REQUEST(400, "不合法的请求"),

    ACCOUNT_DISABLED(423, "账户已禁用");

    private final Integer code;
    private final String msg;


}
