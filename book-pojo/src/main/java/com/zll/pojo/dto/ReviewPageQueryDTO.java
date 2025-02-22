package com.zll.pojo.dto;

import com.zll.common.result.PageRequest;



public class ReviewPageQueryDTO extends PageRequest {

    private Long bookId;
    public ReviewPageQueryDTO(int page, int pageSize, Long bookId) {
        super(page, pageSize);  // 调用父类构造函数
        this.bookId = bookId;
    }
}
