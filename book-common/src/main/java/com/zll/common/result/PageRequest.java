package com.zll.common.result;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 分页请求参数类
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
