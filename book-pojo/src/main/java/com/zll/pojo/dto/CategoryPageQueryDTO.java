package com.zll.pojo.dto;

import com.zll.common.result.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分类页面查询参数
 */
@Getter
public class CategoryPageQueryDTO extends PageRequest {

    private String name;

    public CategoryPageQueryDTO(int page, int pageSize, String name) {
        super(page, pageSize);
        this.name = name;
    }
}
