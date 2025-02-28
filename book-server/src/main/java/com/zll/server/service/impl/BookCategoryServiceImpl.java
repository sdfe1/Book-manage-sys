package com.zll.server.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.BaseException;

import com.zll.common.result.PageResult;
import com.zll.pojo.dto.BookCategoryQueryDTO;
import com.zll.pojo.entity.BookCategory;
import com.zll.pojo.vo.BookVO;
import com.zll.server.mapper.BookCategoryMapper;
import com.zll.server.mapper.BookMapper;
import com.zll.server.mapper.CategoryMapper;
import com.zll.server.service.BookCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图书分类关联实现类
 */
@Service
@RequiredArgsConstructor
public class BookCategoryServiceImpl implements BookCategoryService {

    private final BookCategoryMapper bookCategoryMapper;

    private final BookMapper bookMapper;

    private final CategoryMapper categoryMapper;


    @Override
    public Integer getCategoryByBookId(Long bookId) {
        if (bookMapper.getBookById(bookId) == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "书不存在");
        }
        BookCategory bookCategory = bookCategoryMapper.selectByBookId(bookId);
        return bookCategory.getCategoryId();
    }

    @Override
    public PageResult getBookByCategoryId(BookCategoryQueryDTO bookCategoryQueryDTO) {
        if (categoryMapper.selectById(bookCategoryQueryDTO.getCategoryId()) == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "分类不存在");
        }
        //根据分类Id查到具体的Book表
        PageHelper.startPage(bookCategoryQueryDTO.getPage(), bookCategoryQueryDTO.getPageSize());
        Page<BookVO> page =  bookCategoryMapper.pageQuery(bookCategoryQueryDTO);
        long total = page.getTotal();
        List<BookVO> records= page.getResult();
        return new PageResult(total,records);
    }

    @Override
    public void addBookCategory(Long bookId, Integer categoryId) {
        if (bookMapper.getBookById(bookId) == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "书不存在");
        }
        if (categoryMapper.selectById(categoryId) == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "分类不存在");
        }
        bookCategoryMapper.insert(bookId, categoryId);
    }

    @Override
    public void deleteBookCategory(Long bookId, Integer categoryId) {
        if (bookMapper.getBookById(bookId) == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "书不存在");
        }
        if (categoryMapper.selectById(categoryId) == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "分类不存在");
        }
        bookCategoryMapper.delete(bookId, categoryId);
    }
}
