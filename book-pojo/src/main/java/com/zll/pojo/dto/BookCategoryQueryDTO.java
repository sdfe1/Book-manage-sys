package com.zll.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCategoryQueryDTO {
    private int page;

    //每页记录数
    private int pageSize;

    private Integer categoryId;
}
