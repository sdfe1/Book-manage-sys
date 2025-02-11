package com.zll.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteBook {

    private Long id;

    private Long bookId;

    private Long userId;

    private LocalDateTime createTime;
}
