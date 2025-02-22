package com.zll.common.result;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageRequest {

    private int page ;      // 默认页码


    private int pageSize; // 默认每页大小
}
