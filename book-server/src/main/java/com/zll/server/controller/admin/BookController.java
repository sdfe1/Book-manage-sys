package com.zll.server.controller.admin;

import com.zll.common.result.PageResult;
import com.zll.common.result.Result;
import com.zll.pojo.dto.BookDTO;
import com.zll.pojo.dto.BookPageQueryDTO;
import com.zll.pojo.entity.Book;
import com.zll.pojo.vo.BookVO;
import com.zll.server.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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

    @GetMapping("/page")
    public Result<PageResult> getBooks(@RequestParam("page") int page,
                                       @RequestParam("pageSize") int pageSize,
                                       @RequestParam(value = "sort", defaultValue = "title,asc") String sort) {
        BookPageQueryDTO bookPageQueryDTO = new BookPageQueryDTO(page, pageSize, sort);
        PageResult pageResult = bookService.getBooks(bookPageQueryDTO);
        return Result.success(pageResult);
    }
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

    //`GET /api/books/search?title=Java&author
    //
    //isbn,publisher,publish_date,price,stock
    /*@GetMapping("/search")
    public Result<PageResult> searchBooks(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "isbn", required = false) String isbn,
            @RequestParam(value = "publisher", required = false) String publisher,
            @RequestParam(value = "publish_date", required = false) String publishDate,
            @RequestParam(value = "price", required = false) BigDecimal price,
            @RequestParam(value = "stock", required = false) Integer stock
            ) {

    }*/
}
