package com.zll.server.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.exception.SaTokenException;
import com.zll.common.exception.base.BaseException;
import com.zll.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public Result handleBaseException(BaseException ex) {
        return Result.error(ex.getCode(), ex.getMsg());
    }



    @ExceptionHandler({
            NotLoginException.class,
            NotRoleException.class,
            NotPermissionException.class
    })
    public Result handleSaTokenException(SaTokenException ex) {

        return Result.error(ex.getCode(), ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationException(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return Result.error(400, "参数错误: " + errorMsg);
    }

}
