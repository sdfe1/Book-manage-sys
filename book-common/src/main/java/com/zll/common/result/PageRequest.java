package com.zll.common.result;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 页面查询参数
 */
@Data
@AllArgsConstructor
public class PageRequest {
    /**
     * 页码
     */
    private int page ;

    /**
     * 每页大小
     */
    private int pageSize;
}
