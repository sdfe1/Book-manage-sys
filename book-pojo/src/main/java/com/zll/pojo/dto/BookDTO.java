package com.zll.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;

    private String title;

    private String isbn;

    private String publisher;

    private String author;

    private String  publishDate;

    private BigDecimal price;

    private int stock;
}
