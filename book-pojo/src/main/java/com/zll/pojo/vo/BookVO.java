package com.zll.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookVO {
    private Long id;

    private String title;

    private String isbn;

    private String publisher;

    private String author;

    private String publishDate;

    private BigDecimal price;

    private int stock;

}
