package com.zll.pojo.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 分类实体类
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    private int id;

    private String name;

    /*
    * 分类状态（0:禁用，1:启用）
    * */
    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;
}
