package com.zll.pojo.em;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.processing.Generated;

@Getter
@AllArgsConstructor
public enum PrivacyLevel {
    PUBLIC((byte) 1, "公开"),
    FRIENDS_ONLY((byte) 2, "好友可见"),
    PRIVATE((byte) 3, "私密");

    private final byte code;
    private final String desc;
    @JsonCreator
    public static PrivacyLevel fromString(String value) {
        if (value == null || value.isEmpty()) {
            return PUBLIC; // 或者 PUBLIC，根据你的业务需求
        }
        return PrivacyLevel.valueOf(value.toUpperCase());
    }
}
