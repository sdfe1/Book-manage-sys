package com.zll.common.exception.base;

import com.zll.common.enumeration.CommonErrorCodeEnum;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private Integer code;

    public BaseException(CommonErrorCodeEnum errorCodeEnum, String msg) {
        super(msg);
        this.code = errorCodeEnum.getCode();
    }


    public Integer getCode() {
        return code;
    }
}
