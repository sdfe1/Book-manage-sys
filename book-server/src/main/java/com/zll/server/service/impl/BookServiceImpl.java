package com.zll.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.zll.common.context.BaseContext;
import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.book.BookErrorException;
import com.zll.common.utils.IsbnUtil;
import com.zll.pojo.dto.BookDTO;
import com.zll.pojo.entity.Book;
import com.zll.server.mapper.BookMapper;
import com.zll.server.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public void addBook(BookDTO bookDTO) {
        //书名和作者不为空
        if(bookDTO.getAuthor() == null || bookDTO.getTitle() == null) {
            throw new BookErrorException(CommonErrorCodeEnum.INVALID_REQUEST,"作者或书名不能为空");
        }
        //isbn输入格式正确
        if (!IsbnUtil.validateISBN13(bookDTO.getIsbn())) {
            throw new BookErrorException(CommonErrorCodeEnum.INVALID_REQUEST,"isbn格式不正确");
        }
        //完善book
        Book book = Book.builder()
                .title(bookDTO.getTitle())
                .isbn(bookDTO.getIsbn())
                .publisher(bookDTO.getPublisher())
                .author(bookDTO.getAuthor())
                .publishDate(bookDTO.getPublishDate())
                .price(bookDTO.getPrice())
                .stock(bookDTO.getStock())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .createUser(BaseContext.getCurrentId())
                .updateUser(BaseContext.getCurrentId())
                .build();
        bookMapper.insert(book);

    }

    @Override
    public void deleteBook(Long id) {
        //判断id的书是否存在
        if(bookMapper.getBookById(id) == null) {
            throw new BookErrorException(CommonErrorCodeEnum.NOT_FOUND,"书不存在");
        }
        bookMapper.deleteBook(id);
    }

    @Override
    public Book getBookById(Long id) {
        if (bookMapper.getBookById(id) == null) {
            throw new BookErrorException(CommonErrorCodeEnum.NOT_FOUND,"书不存在");
        }
        return bookMapper.getBookById(id);
    }

    @Override
    public void updateBook(BookDTO bookDTO) {
        Book book = new Book();
        BeanUtil.copyProperties(bookDTO,book, CopyOptions.create().ignoreNullValue());
        book.setUpdateTime(LocalDateTime.now());
        book.setUpdateUser(BaseContext.getCurrentId());
        bookMapper.updateBook(book);
    }


}
