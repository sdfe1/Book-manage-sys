package com.zll.common.utils;

import cn.dev33.satoken.secure.BCrypt;

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

    public static void main(String[] args) {
        String password = "my_secure_password";

        // 注册时加密并存储密码
        String hashedPassword = hashPassword(password);
        System.out.println("加密后的密码: " + hashedPassword);

        // 登录时验证密码
        boolean isValid = verifyPassword(password, hashedPassword);
        System.out.println("密码是否正确: " + isValid);
    }
}
