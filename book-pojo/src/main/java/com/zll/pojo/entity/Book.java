package com.zll.pojo.entity;



import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    private Long id;

    private String title;

    private String isbn;

    private String publisher;

    private String author;

    private String publishDate;

    private BigDecimal price;

    private int stock;

    private LocalDateTime createTime;


    private LocalDateTime updateTime;

    private Long updateUser;

    private Long createUser;
}
