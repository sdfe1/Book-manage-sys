package com.zll.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
