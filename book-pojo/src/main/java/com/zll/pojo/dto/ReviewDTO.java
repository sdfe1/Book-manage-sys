package com.zll.pojo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {
    private Long id;

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotBlank(message = "评论内容不能为空")
    @Size(max = 500, message = "评论内容最长500字")
    private String content;

    @Min(value = 1, message = "评分至少1分")
    @Max(value = 5, message = "评分最多5分")
    private byte rating;

}
