package com.zll.pojo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPageQueryDTO implements Serializable {
    //页码
    private int page;

    //每页记录数
    private int pageSize;

    /**
     * 仅支持单个字段加排序<字段>,<方向>
     */
    private String sort;

    public String getSortField() {
        String[] parts = this.sort.split(",");
        String field = parts[0];
        List<String> allowedFields = Arrays.asList("title", "isbn", "author", "create_time","publisher","publish_date","price","stock");
        return allowedFields.contains(field) ? field : "title";
    }

    public String getSortOrder() {
        String[] parts = this.sort.split(",");
        String order = (parts.length > 1) ? parts[1] : "asc";
        return Arrays.asList("asc", "desc").contains(order.toLowerCase()) ? order : "asc";
    }


}
