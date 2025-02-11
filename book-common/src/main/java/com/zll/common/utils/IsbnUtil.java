package com.zll.common.utils;
/*
* Isbn13校验工具类
*
* */
public class IsbnUtil {
    public static boolean validateISBN13(String isbn) {
        // 移除所有非数字字符（包括连字符）
        isbn = isbn.replaceAll("-", "");
        if (isbn.length() != 13) {
            return false;
        }
        int total = 0;
        for (int i = 0; i < 12; i++) {
            int digit = isbn.charAt(i) - '0';
            if (i % 2 == 0) {
                total += digit;  // 奇数位 (1, 3, 5...) 权重为 1
            } else {
                total += digit * 3;  // 偶数位 (2, 4, 6...) 权重为 3
            }
        }
        // 计算校验位
        int checksum = 10 - (total % 10);
        if (checksum == 10) {
            checksum = 0;  // 校验位为 10 时，校验位应为 0
        }
        return checksum == (isbn.charAt(12) - '0');  // 检查最后一位是否匹配
    }
}
