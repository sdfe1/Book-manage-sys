package com.zll.pojo.entity;

import com.zll.pojo.em.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 用户实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private Long id;

    private String username;

    private String password;

    private String avatar;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, columnDefinition = "ENUM('USER', 'ADMIN') DEFAULT 'USER'")
    private RoleEnum role;

    private LocalDateTime createTime;

    /**
     * 可登录状态(0 ,1)
     */
    private Integer isLogin;

    /**
     * 禁言状态
     */
    private Integer isWord;


}
