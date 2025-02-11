package com.zll.server.controller.admin;

import com.zll.common.result.Result;
import com.zll.pojo.dto.BookDTO;
import com.zll.pojo.entity.Book;
import com.zll.pojo.vo.BookVO;
import com.zll.server.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 书籍相关的控制器
 */
@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    //添加图书
    @PostMapping
    public Result addBook(@RequestBody BookDTO bookDTO){
        bookService.addBook(bookDTO);
        return Result.success();
    }

    //删除图书
    @DeleteMapping("/{id}")
    public Result deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return Result.success();
    }


    //获取所有图书列表

    //获取单本图书信息
    @GetMapping("/{id}")
    public Result<BookVO> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return Result.error(401,"Book not found for id: " );  // 返回合适的失败响应
        }
        BookVO bookVO = BookVO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .publisher(book.getPublisher())
                .author(book.getAuthor())
                .publishDate(book.getPublishDate())
                .build();
        return Result.success(bookVO);
    }

    //更新图书
    @PutMapping("/{id}")
    public Result updateBook(@RequestBody BookDTO bookDTO,@PathVariable Long id) {
        log.info("bookDTO:{}",bookDTO);
        bookDTO.setId(id);
        bookService.updateBook(bookDTO);
        return Result.success();
    }


}
