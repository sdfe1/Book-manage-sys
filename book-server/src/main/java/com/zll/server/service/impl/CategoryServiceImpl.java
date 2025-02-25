package com.zll.server.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.zll.common.constant.StatusConstant;
import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.base.BaseException;
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
import org.springframework.dao.DuplicateKeyException;
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
        try {
            Category category = Category.builder()
                    .name(categoryDTO.getName())
                    .status(StatusConstant.ENABLE)
                    .createTime(LocalDateTime.now())
                    .createUser(BaseContext.getCurrentId())
                    .build();
            categoryMapper.insert(category);
        }catch (DuplicateKeyException e) {
            throw new BaseException(CommonErrorCodeEnum.ALREADY_EXISTS, "分类已存在");
        }
    }

    /**
     * 删除分类
     * @param id
     */
    @Override
    public void deleteCategory(int id) {
        // 防御性校验
        if ( id <= 0) {
            throw new BaseException(CommonErrorCodeEnum.INVALID_REQUEST, "ID必须为正整数");
        }
        int rows = categoryMapper.delete(id);
        //判断id的书是否存在
        if(rows == 0) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND,"分类不存在");
        }
    }


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
        // 2. 将旧数据的version赋值给DTO
        categoryDTO.setVersion(oldCategory.getVersion());

        //2.categoryDTO转为category
        Category category = new Category();
        BeanUtil.copyProperties(categoryDTO,category, CopyOptions.create().ignoreNullValue());
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());
        //3.更新
        int rows = categoryMapper.update(category);
        if (rows == 0) {
            throw new BaseException(CommonErrorCodeEnum.CONCURRENT_CONFLICT,"数据已被修改，请刷新后重试");
        }
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
