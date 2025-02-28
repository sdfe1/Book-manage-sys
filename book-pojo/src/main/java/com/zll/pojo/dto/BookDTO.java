package com.zll.pojo.dto;

import com.zll.common.validation.ISBN13;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * 图书DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;

    @NotBlank(message = "书名不能为空")
    private String title;

    @NotBlank(message = "isbn不能为空")
    @ISBN13(message = "isbn格式错误")
    private String isbn;

    private String publisher;

    @NotBlank(message = "作者不能为空")
    private String author;

    @Pattern(
            regexp = "^\\d{4}-(0[1-9]|1[0-2])$",
            message = "出版日期格式必须为YYYY-MM（如1990-08）"
    )
    private String  publishDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "价格必须大于0")
    @Digits(integer = 10, fraction = 2, message = "价格最多保留两位小数")
    private BigDecimal price;

    @Min(value = 0, message = "库存不能为负数")
    private Integer stock;
}
