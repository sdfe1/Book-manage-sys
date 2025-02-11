package com.zll.common.exception.auth;

import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.base.BaseException;

public class AccountNameException extends BaseException {


    public AccountNameException(CommonErrorCodeEnum errorCodeEnum, String msg) {
        super(errorCodeEnum, msg);
    }

}
