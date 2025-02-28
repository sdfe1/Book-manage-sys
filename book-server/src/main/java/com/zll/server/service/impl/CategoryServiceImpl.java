package com.zll.server.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cn.hutool.core.bean.BeanUtil;
import com.zll.common.constant.StatusConstant;
import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.BaseException;
import com.zll.common.result.PageResult;
import com.zll.common.context.BaseContext;
import com.zll.pojo.dto.CategoryDTO;
import com.zll.pojo.dto.CategoryPageQueryDTO;
import com.zll.pojo.entity.Category;
import com.zll.server.mapper.CategoryMapper;
import com.zll.server.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 分类服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryMapper categoryMapper;

    /**
     * 添加分类
     * @param categoryDTO
     */
    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        if (categoryMapper.selectByName(categoryDTO.getName()) != null) {
            throw new BaseException(CommonErrorCodeEnum.ALREADY_EXISTS, "分类已存在");
        }
        Category category = new Category();
        BeanUtil.copyProperties(categoryDTO,category);
        category.setCreateUser(BaseContext.getCurrentId());
        category.setCreateTime(LocalDateTime.now());
        category.setStatus(StatusConstant.ENABLE);
        categoryMapper.insert(category);
    }

    /**
     * 删除分类
     * @param id
     */
    @Override
    public void deleteCategory(int id) {
        if(categoryMapper.selectById(id) != null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND,"分类不存在");
        }
        categoryMapper.delete(id);
    }


    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    @Override
    public Category getCategoryByID(int id) {
        Category category = categoryMapper.selectById(id);
        if (category == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND,"分类不存在");
        }
        return category;
    }


    /**
     * 更新分类信息
     * @param categoryDTO
     */
    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        //1.查询是否存在该分类
        Category oldCategory = categoryMapper.selectById(categoryDTO.getId());
        if (oldCategory == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND,"分类不存在");
        }
        //2.categoryDTO转为category
        Category category = new Category();
        BeanUtil.copyProperties(categoryDTO,category);
        category.setUpdateUser(BaseContext.getCurrentId());
        category.setUpdateTime(LocalDateTime.now());
        //3.更新
        categoryMapper.update(category);

    }

    /**
     * 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
        Page<Category> page =  categoryMapper.pageQuery(categoryPageQueryDTO);
        long total = page.getTotal();
        List<Category> records= page.getResult();
        return new PageResult(total,records);

    }


}
