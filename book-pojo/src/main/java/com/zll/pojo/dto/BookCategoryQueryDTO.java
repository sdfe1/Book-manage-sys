package com.zll.pojo.dto;

import com.zll.common.result.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 图书分类分页请求参数
 */
@Getter
public class BookCategoryQueryDTO extends PageRequest {

    private Integer categoryId;

    public BookCategoryQueryDTO(int page, int pageSize, Integer categoryId) {
        super(page, pageSize);
        this.categoryId = categoryId;
    }
}
