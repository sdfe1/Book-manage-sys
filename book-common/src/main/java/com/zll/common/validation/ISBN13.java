package com.zll.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * ISBN13校验注解
 */
@Documented
@Constraint(validatedBy = ISBN13Validator.class) // 指定校验器
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ISBN13 {
    String message() default "ISBN格式无效"; // 默认错误提示

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
