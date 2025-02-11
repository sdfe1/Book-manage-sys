package com.zll.pojo.dto;

import com.zll.pojo.em.Gender;
import com.zll.pojo.em.PrivacyLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDTO {
    private Long userId;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String location;

    private String bio;

    private String interests;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'PUBLIC'")
    private PrivacyLevel privacyLevel = PrivacyLevel.PUBLIC;


}
