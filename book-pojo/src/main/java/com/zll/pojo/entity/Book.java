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



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    private Long id;

    private String title;

    private String isbn;

    //出版社
    private String publisher;

    private String author;

    //出本日期
    private String publishDate;

    private BigDecimal price;

    // 库存
    private int stock;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long updateUser;

    private Long createUser;

    private Integer version;
}
