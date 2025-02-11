package com.zll.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {
    private Long id;

    private Long userId;

    private Long bookId;

    private String content;

    private byte rating;

    private Long parentId;
}
