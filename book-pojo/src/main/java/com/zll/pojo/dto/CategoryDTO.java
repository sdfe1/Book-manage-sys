package com.zll.pojo.dto;

import com.zll.common.validation.CreateGroup;
import com.zll.common.validation.UpdateGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private int id;

    // 名称在新增和更新时都需校验非空
    @NotBlank(message = "分类名称不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    private String name;

    // 状态仅在更新时校验合法性（0/1）
    @Range(min = 0, max = 1, message = "状态值不合法", groups = UpdateGroup.class)
    @NotNull(message = "状态不能为空", groups = UpdateGroup.class)
    private Integer status;

    private Integer version;
}
