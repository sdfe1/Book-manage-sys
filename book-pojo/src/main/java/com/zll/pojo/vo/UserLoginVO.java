package com.zll.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户登录返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVO {
    private Long id;
    private String role;
}
