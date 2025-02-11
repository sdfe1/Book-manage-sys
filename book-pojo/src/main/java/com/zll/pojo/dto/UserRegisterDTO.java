package com.zll.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;




}
