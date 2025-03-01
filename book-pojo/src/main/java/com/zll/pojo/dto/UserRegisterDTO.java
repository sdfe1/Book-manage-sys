package com.zll.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户注册请求参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    /**
     * 用户名：2-16位的组合字符串
     */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 16, message = "用户名长度需为2-16位")
    private String username;

    /**
     * 密码：6-16位的组合字符串
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 16, message = "密码长度需为6-16位")
    private String password;
}
