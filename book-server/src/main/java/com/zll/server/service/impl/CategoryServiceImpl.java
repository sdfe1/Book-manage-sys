package com.zll.server.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.category.CategoryErrorException;
import com.zll.common.result.PageResult;
import com.zll.common.context.BaseContext;
import com.zll.pojo.dto.CategoryDTO;
import com.zll.pojo.dto.CategoryPageQueryDTO;
import com.zll.pojo.entity.Category;
import com.zll.server.mapper.CategoryMapper;
import com.zll.server.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        //判断新增的分类是否已经存在
        if(categoryMapper.selectByName(categoryDTO.getName()) != null) {
            throw new CategoryErrorException(CommonErrorCodeEnum.ALREADY_EXISTS,"分类已存在");
        }
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .createTime(LocalDateTime.now())
                .createUser(BaseContext.getCurrentId())
                .build();
        categoryMapper.insert(category);
    }

    @Override
    public void deleteCategory(int id) {
        //判断id的书是否存在
        if(categoryMapper.selectById(id) == null) {
            throw new CategoryErrorException(CommonErrorCodeEnum.NOT_FOUND,"分类不存在");
        }
        categoryMapper.delete(id);
    }


    @Override
    public Category getCategoryByID(int id) {
        Category category = categoryMapper.selectById(id);
        if (category == null) {
            throw new CategoryErrorException(CommonErrorCodeEnum.NOT_FOUND,"分类不存在");
        }
        return category;
    }


    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtil.copyProperties(categoryDTO,category, CopyOptions.create().ignoreNullValue());
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.update(category);


    }

    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());

        //下一条sql进行分页，自动加入limit关键字分页
        Page<Category> page =  categoryMapper.pageQuery(categoryPageQueryDTO);
        long total = page.getTotal();
        List<Category> records= page.getResult();
        return new PageResult(total,records);

    }


}
