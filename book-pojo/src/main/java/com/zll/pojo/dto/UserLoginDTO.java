package com.zll.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    /**
     * 用户账号名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
