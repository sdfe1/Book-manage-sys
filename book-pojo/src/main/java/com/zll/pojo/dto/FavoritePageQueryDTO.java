package com.zll.pojo.dto;

import com.zll.common.result.PageRequest;
import lombok.Getter;

/**
 * 收藏页面请求参数
 */
@Getter
public class FavoritePageQueryDTO extends PageRequest {

    private Long userId;

    public FavoritePageQueryDTO(int page, int pageSize, Long userId) {
        super(page, pageSize);
        this.userId = userId;
    }
}
