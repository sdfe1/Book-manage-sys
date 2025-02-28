package com.zll.pojo.entity;

import com.zll.pojo.em.RoleEnum;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


/**
 * 用户实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 角色（USER ADMIN）
     */
    private RoleEnum role;

    /**
     * 可登录状态: 1启用 0禁用
     */
    private Integer isLogin;

    /**
     * 禁言状态：1启用 0禁用
     */
    private Integer isWord;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;
}
