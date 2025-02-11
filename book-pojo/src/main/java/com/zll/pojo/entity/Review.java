package com.zll.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    private Long id;
    private Long bookId;
    private Long userId;
    private Long parentId;
    private String content;
    private byte rating;
    private LocalDateTime createTime;
}
