package com.zll.pojo.dto;

import com.zll.common.result.PageRequest;

/**
 * 收藏页面查询参数
 */
public class FavoritePageQueryDTO extends PageRequest {

    private Long userId;

    public FavoritePageQueryDTO(int page, int pageSize, Long userId) {
        super(page, pageSize);
        this.userId = userId;
    }
}
