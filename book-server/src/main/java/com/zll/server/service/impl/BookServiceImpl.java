package com.zll.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zll.common.context.BaseContext;
import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.BaseException;
import com.zll.common.result.PageResult;
import com.zll.pojo.dto.BookDTO;
import com.zll.pojo.dto.BookPageQueryDTO;
import com.zll.pojo.entity.Book;
import com.zll.pojo.vo.BookVO;
import com.zll.server.mapper.BookMapper;
import com.zll.server.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * 图书服务实现类
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;

    /**
     * 添加图书
     * @param bookDTO
     */
    @Override
    public void addBook(BookDTO bookDTO) {
        //1.判断ISBN是否唯一
        if (bookMapper.ISBNExists(bookDTO.getIsbn()) != null) {
            throw new BaseException(CommonErrorCodeEnum.ALREADY_EXISTS,"ISBN已存在");
        }
        //2.BookDTO转Book
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        book.setCreateUser(BaseContext.getCurrentId());
        book.setCreateTime(LocalDateTime.now());
        //3.插入数据
        bookMapper.insert(book);
        log.info("图书添加成功，ID: {}", book.getId());
    }

    /**
     * 删除图书
     * @param id 图书id
     *
     */
    @Override
    public void deleteBook(Long id) {
        if (bookMapper.getBookById(id) == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND,"图书不存在");
        }
        bookMapper.deleteBook(id);
        log.info("图书删除成功，ID: {}", id);
    }

    /**
     * 根据id获取图书
     * @param id
     * @return
     */
    @Override
    public Book getBookById(Long id) {
        Book book= bookMapper.getBookById(id);
        if (book == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND,"图书不存在");
        }
        return book;
    }

    /**
     * 更新图书
     * @param bookDTO
     *
     */
    @Override
    public void updateBook(BookDTO bookDTO) {
        // 1. 查询当前书本信息
        Book existingBook = bookMapper.getBookById(bookDTO.getId());
        if (existingBook == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND,"图书不存在");
        }
        //2. BookDTO转Book
        Book book = new Book();
        BeanUtil.copyProperties(bookDTO,book);
        book.setUpdateUser(BaseContext.getCurrentId());
        book.setUpdateTime(LocalDateTime.now());
        // 3. 更新数据
        bookMapper.updateBook(book);
        log.info("图书更新成功，ID: {}", existingBook.getId());

    }

    /**
     * 获取所有图书列表
     * @param bookPageQueryDTO
     * @return
     */
    @Override
    public PageResult getBooks(BookPageQueryDTO bookPageQueryDTO) {
        PageHelper.startPage(bookPageQueryDTO.getPage(),bookPageQueryDTO.getPageSize());
        Page<BookVO> page =  bookMapper.pageQuery(bookPageQueryDTO);
        return new PageResult(
                page.getTotal(),
                page.getResult()
        );
    }


}
