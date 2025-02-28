package com.zll.pojo.em;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 性别枚举
 */
@Getter
@AllArgsConstructor
public enum Gender {

    MALE((byte) 1, "男"),
    FEMALE((byte) 2, "女");

    private final byte code;
    private final String desc;

}
