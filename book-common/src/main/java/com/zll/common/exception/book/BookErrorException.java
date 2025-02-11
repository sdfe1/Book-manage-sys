package com.zll.common.exception.book;

import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.base.BaseException;

public class BookErrorException extends BaseException {
    public BookErrorException(CommonErrorCodeEnum errorCodeEnum, String msg) {
        super(errorCodeEnum, msg);
    }
}
