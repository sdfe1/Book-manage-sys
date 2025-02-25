package com.zll.server.service;

import com.zll.common.result.PageResult;
import com.zll.pojo.dto.BookCategoryQueryDTO;
import com.zll.pojo.entity.BookCategory;
import jakarta.validation.Valid;

public interface BookCategoryService {

    //void updateBookCategory(BookCategory bookCategory);

    Integer getCategoryByBookId(Long bookId);

    PageResult getBookByCategoryId(BookCategoryQueryDTO bookCategoryQueryDTO);

    void addBookCategory(Long bookId, Integer categoryId);

    void deleteBookCategory(Long bookId, Integer categoryId);

}
