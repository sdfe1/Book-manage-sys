package com.zll.common.exception.auth;

import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.base.BaseException;

public class AccountLockedException extends BaseException {

    public AccountLockedException(CommonErrorCodeEnum errorCodeEnum, String msg) {
        super(errorCodeEnum, msg);
    }

}
