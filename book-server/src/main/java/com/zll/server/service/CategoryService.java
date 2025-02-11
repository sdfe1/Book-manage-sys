package com.zll.server.service;

import com.zll.common.result.PageResult;
import com.zll.pojo.dto.CategoryDTO;
import com.zll.pojo.dto.CategoryPageQueryDTO;
import com.zll.pojo.entity.Category;

public interface CategoryService {

    void addCategory(CategoryDTO categoryDTO);

    void deleteCategory(int id);

    Category getCategoryByID(int id);



    void updateCategory(CategoryDTO categoryDTO);



    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);
}
