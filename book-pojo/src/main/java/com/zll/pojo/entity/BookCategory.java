package com.zll.pojo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图书分类关联实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCategory {

    private Integer categoryId;

    private Long bookId;

}
