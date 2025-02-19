package com.zll.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zll.common.context.BaseContext;
import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.book.BookErrorException;
import com.zll.common.result.PageResult;
import com.zll.pojo.dto.BookDTO;
import com.zll.pojo.dto.BookPageQueryDTO;
import com.zll.pojo.entity.Book;
import com.zll.pojo.vo.BookVO;
import com.zll.server.mapper.BookMapper;
import com.zll.server.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        if (bookMapper.ISBNExists(bookDTO.getIsbn()) == null) {
            throw new BookErrorException(CommonErrorCodeEnum.ALREADY_EXISTS,"ISBN已存在");
        }
        //2.BookDTO转Book
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
        //3.插入数据
        bookMapper.insert(book);
    }

    /**
     * 删除图书
     * @param id 图书id
     *
     */
    @Override
    public void deleteBook(Long id) {
        int rows = bookMapper.deleteBook(id);
        if (rows == 0) {
            throw new BookErrorException(CommonErrorCodeEnum.NOT_FOUND,"图书不存在");
        }
    }

    @Override
    public Book getBookById(Long id) {
        Book book= bookMapper.getBookById(id);
        if (book == null) {
            throw new BookErrorException(CommonErrorCodeEnum.NOT_FOUND,"书不存在");
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
        Book oldBook = bookMapper.getBookById(bookDTO.getId());
        if (oldBook == null) {
            throw new BookErrorException(CommonErrorCodeEnum.NOT_FOUND,"图书不存在");
        }
        //2. BookDTO转Book
        Book book = new Book();
        BeanUtil.copyProperties(bookDTO,book, CopyOptions.create().ignoreNullValue());
        book.setUpdateTime(LocalDateTime.now());
        book.setUpdateUser(BaseContext.getCurrentId());
        // 3. 更新数据
        int rows = bookMapper.updateBook(book);
        if (rows == 0) {
            throw new BookErrorException(CommonErrorCodeEnum.CONCURRENT_CONFLICT,"数据已被修改，请刷新后重试");
        }
    }

    /**
     * 获取所有图书列表
     * @param bookPageQueryDTO
     * @return
     */
    @Override
    public PageResult getBooks(BookPageQueryDTO bookPageQueryDTO) {
        PageHelper.startPage(bookPageQueryDTO.getPage(),bookPageQueryDTO.getPageSize());

        //下一条sql进行分页，自动加入limit关键字分页
        Page<BookVO> page =  bookMapper.pageQuery(bookPageQueryDTO);
        return new PageResult(
                page.getTotal(),
                page.getResult()
        );
    }


}
