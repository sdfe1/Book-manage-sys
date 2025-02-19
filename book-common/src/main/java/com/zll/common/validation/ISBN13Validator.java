package com.zll.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * ISBN13校验器
 */
public class ISBN13Validator implements ConstraintValidator<ISBN13, String> {

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext constraintValidatorContext) {
        if (isbn == null) {
            return false;
        }
        // 移除所有非数字字符（包括连字符）
        String cleanedIsbn = isbn.replaceAll("[^0-9]", "");
        if (cleanedIsbn.length() != 13) {
            return false;
        }
        // 校验位计算逻辑（与你的工具类一致）
        int total = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(cleanedIsbn.charAt(i));
            total += (i % 2 == 0) ? digit : digit * 3;
        }
        int checksum = 10 - (total % 10);
        checksum = (checksum == 10) ? 0 : checksum;
        int lastDigit = Character.getNumericValue(cleanedIsbn.charAt(12));
        return checksum == lastDigit;
    }
}
