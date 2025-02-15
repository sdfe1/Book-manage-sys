package com.zll.pojo.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCategory {

    @NotNull
    private Integer categoryId;

    @NotNull
    private Long bookId;

}
