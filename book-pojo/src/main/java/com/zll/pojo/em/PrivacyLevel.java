package com.zll.pojo.em;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.processing.Generated;

/**
 * 隐私类别枚举
 */
@Getter
@AllArgsConstructor
public enum PrivacyLevel {
    PUBLIC((byte) 1, "公开"),
    PRIVATE((byte) 2, "私密");

    private final byte code;
    private final String desc;
    @JsonCreator
    public static PrivacyLevel fromString(String value) {
        if (value == null || value.isEmpty()) {
            return PUBLIC;
        }
        return PrivacyLevel.valueOf(value.toUpperCase());
    }
}
