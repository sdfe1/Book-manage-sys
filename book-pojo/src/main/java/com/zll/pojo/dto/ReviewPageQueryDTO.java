package com.zll.pojo.dto;

import com.zll.common.result.PageRequest;
import lombok.Getter;


/**
 * 评论页面查询参数
 */
@Getter
public class ReviewPageQueryDTO extends PageRequest {

    private Long bookId;
    public ReviewPageQueryDTO(int page, int pageSize, Long bookId) {
        super(page, pageSize);
        this.bookId = bookId;
    }
}
