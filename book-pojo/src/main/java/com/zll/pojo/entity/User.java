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
@Entity
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
     * 可登录状态: ENABLE =1启用 DISABLE =0禁用
     */
    private Integer isLogin;

    /**
     * 禁言状态：ENABLE =1启用 DISABLE =0禁用
     */
    private Integer isWord;


}
