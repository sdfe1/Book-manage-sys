package com.zll.common.exception.base;

import com.zll.common.enumeration.CommonErrorCodeEnum;
import lombok.Getter;

import java.text.MessageFormat;

@Getter
public class BaseException extends RuntimeException{

    private final int code;

    public BaseException(CommonErrorCodeEnum errorCodeEnum, String msg) {
        super(msg);
        this.code = errorCodeEnum.getCode();
    }

    // 通过枚举初始化
    public BaseException(CommonErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.code = errorCodeEnum.getCode();
    }


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return super.getMessage();
    }
}
