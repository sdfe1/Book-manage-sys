package com.zll.common.utils;

import cn.dev33.satoken.secure.BCrypt;

/**
 * 用户密码工具类
 */
public class PasswordUtil {
    // 生成加盐并加密的密码
    public static String hashPassword(String password) {
        // 生成盐并加密
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // 验证密码
    public static boolean verifyPassword(String password, String hashedPassword) {
        // 比较输入的密码与存储的密码是否一致
        return BCrypt.checkpw(password, hashedPassword);
    }

}
