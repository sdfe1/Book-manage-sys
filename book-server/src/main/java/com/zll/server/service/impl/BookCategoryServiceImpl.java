package com.zll.server.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.BaseException;

import com.zll.common.result.PageResult;
import com.zll.pojo.dto.BookCategoryQueryDTO;
import com.zll.pojo.entity.Category;
import com.zll.pojo.vo.BookVO;
import com.zll.server.mapper.BookCategoryMapper;
import com.zll.server.mapper.BookMapper;
import com.zll.server.mapper.CategoryMapper;
import com.zll.server.service.BookCategoryService;
import lombok.RequiredArgsConstructor;
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


    /**
     * 根据bookId查询分类Id
     * @param bookId 图书id
     * @return
     */
    @Override
    public String getCategoryByBookId(Long bookId) {
        if (bookMapper.getBookById(bookId) == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "书不存在");
        }
        Category category = bookCategoryMapper.selectCategoryByBookId(bookId);
        return category.getName();
    }

    /**
     * 根据分类Id查询图书
     * @param bookCategoryQueryDTO
     * @return
     */
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

    /**
     * 添加图书分类关联
     * @param bookId
     * @param categoryId
     */
    @Override
    public void addBookCategory(Long bookId, Integer categoryId) {
        if (bookMapper.getBookById(bookId) == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "书不存在");
        }
        if (categoryMapper.selectById(categoryId) == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "分类不存在");
        }
        int existCount = bookCategoryMapper.selectExistsByBookIdAndCategoryId(bookId, categoryId);
        if (existCount > 0) {
            throw new BaseException(CommonErrorCodeEnum.ALREADY_EXISTS, "该图书已关联此分类");
        }
        bookCategoryMapper.insert(bookId, categoryId);
    }

    /**
     * 删除图书分类关联
     * @param bookId
     * @param categoryId
     */
    @Override
    public void deleteBookCategory(Long bookId, Integer categoryId) {
        if (bookMapper.getBookById(bookId) == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "书不存在");
        }
        if (categoryMapper.selectById(categoryId) == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "分类不存在");
        }
        int existCount = bookCategoryMapper.selectExistsByBookIdAndCategoryId(bookId, categoryId);
        if (existCount == 0) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "该图书未关联此分类");
        }
        bookCategoryMapper.delete(bookId, categoryId);
    }
}
