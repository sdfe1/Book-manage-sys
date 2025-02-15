package com.zll.server.service;

import com.zll.common.result.PageResult;
import com.zll.common.result.Result;
import com.zll.pojo.dto.BookDTO;
import com.zll.pojo.dto.BookPageQueryDTO;
import com.zll.pojo.entity.Book;
import com.zll.pojo.vo.BookVO;

public interface BookService {
    void addBook(BookDTO bookDTO);

    void deleteBook(Long id);

    Book getBookById(Long id);

    void updateBook(BookDTO bookDTO);

    PageResult getBooks(BookPageQueryDTO bookPageQueryDTO);

}
