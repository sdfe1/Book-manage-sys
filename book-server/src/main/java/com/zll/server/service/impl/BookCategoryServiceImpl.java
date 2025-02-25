package com.zll.server.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.base.BaseException;

import com.zll.common.result.PageResult;
import com.zll.pojo.dto.BookCategoryQueryDTO;
import com.zll.pojo.entity.BookCategory;
import com.zll.pojo.entity.Category;
import com.zll.pojo.vo.BookVO;
import com.zll.server.mapper.BookCategoryMapper;
import com.zll.server.mapper.BookMapper;
import com.zll.server.mapper.CategoryMapper;
import com.zll.server.service.BookCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {
    @Autowired
    private BookCategoryMapper bookCategoryMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    /*@Override
    public void updateBookCategory(BookCategory bookCategory) {
        if (bookMapper.getBookById(bookCategory.getBookId()) == null) {
            throw new BookErrorException(CommonErrorCodeEnum.NOT_FOUND, "书不存在");
        }
        if (categoryMapper.selectById(bookCategory.getCategoryId()) == null) {
            throw new CategoryErrorException(CommonErrorCodeEnum.NOT_FOUND, "分类不存在");
        }
        bookCategoryMapper.update(bookCategory);
    }*/

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

        //下一条sql进行分页，自动加入limit关键字分页
        Page<BookVO> page =  bookCategoryMapper.pageQuery(bookCategoryQueryDTO);
        long total = page.getTotal();
        List<BookVO> records= page.getResult();
        return new PageResult(total,records);
    }

    @Override
    public void addBookCategory(Long bookId, Integer categoryId) {
        //判断书本id是否存在
        if (bookMapper.getBookById(bookId) == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "书不存在");
        }
        if (categoryMapper.selectById(categoryId) == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "分类不存在");
        }
        //判断分类Id是否存在
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
