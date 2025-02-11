package com.zll.common.exception.category;

import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.base.BaseException;

public class CategoryErrorException extends BaseException {
    public CategoryErrorException(CommonErrorCodeEnum errorCodeEnum, String msg) {
        super(errorCodeEnum, msg);
    }
}
