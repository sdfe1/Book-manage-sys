package com.zll.common.utils;

import java.util.regex.Pattern;

public class PhoneUtils {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");

    public static boolean isValid(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }
}
