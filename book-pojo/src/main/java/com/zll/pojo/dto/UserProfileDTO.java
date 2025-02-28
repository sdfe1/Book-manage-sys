package com.zll.pojo.dto;

import com.zll.pojo.em.Gender;
import com.zll.pojo.em.PrivacyLevel;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 用户个人资料DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDTO {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "性别不能为空")
    private Gender gender;

    private String location;

    /**
     * 个人简介
     */
    @Size(max = 100, message = "个人简介不能超过100个字符")
    private String bio;

    /**
     * 兴趣
     */
    private String interests;

    /**
     * 隐私设置
     */
    @NotNull(message = "隐私级别不能为空")
    private PrivacyLevel privacyLevel = PrivacyLevel.PUBLIC;


}
