package com.zll.pojo.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 书本实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    private Long id;

    /**
     * 书名
     */
    private String title;

    /**
     * ISBN号
     */
    private String isbn;

    /**
     * 出版社
     */
    private String publisher;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版日期
     */
    private String publishDate;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存数量
     */
    private int stock;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long updateUser;

    private Long createUser;
    
}
