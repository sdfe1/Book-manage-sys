package com.zll.pojo.entity;

import com.zll.pojo.em.Gender;
import com.zll.pojo.em.PrivacyLevel;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String location;

    private String bio;//个人简介

    private String interests;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'PUBLIC'")
    private PrivacyLevel privacyLevel = PrivacyLevel.PUBLIC;

    private LocalDateTime createTime;


    private LocalDateTime updateTime;
}
