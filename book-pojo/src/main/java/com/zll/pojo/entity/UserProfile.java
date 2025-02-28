package com.zll.pojo.entity;

import com.zll.pojo.em.Gender;
import com.zll.pojo.em.PrivacyLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户个人资料实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    private Long userId;

    /**
     * 性别
     */
    private Gender gender;

    /**
     * 地址
     */
    private String location;

    /**
     * 个人简介
     */
    private String bio;

    /**
     * 兴趣
     */
    private String interests;

    /**
     * 隐私设置（PUBLIC,PRIVATE,）
     */
    private PrivacyLevel privacyLevel = PrivacyLevel.PUBLIC;

    private LocalDateTime createTime;


    private LocalDateTime updateTime;
}
