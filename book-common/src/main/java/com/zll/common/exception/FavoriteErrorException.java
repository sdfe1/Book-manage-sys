package com.zll.common.exception;

import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.base.BaseException;

public class FavoriteErrorException extends BaseException {
    public FavoriteErrorException(CommonErrorCodeEnum errorCodeEnum, String msg) {
        super(errorCodeEnum, msg);
    }
}
