package com.zll.pojo.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;


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
