package com.zll.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图书收藏DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteBookDTO {

    private Long userId;

    private Long bookId;
}
