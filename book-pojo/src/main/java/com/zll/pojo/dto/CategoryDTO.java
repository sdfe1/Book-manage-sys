package com.zll.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * 分类DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private int id;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空")
    private String name;


    /**
     * 分类状态（0:禁用，1:正常）
     */
    @Range(min = 0, max = 1, message = "状态值不合法")
    @NotNull(message = "状态不能为空")
    private Integer status;

}
