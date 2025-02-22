package com.zll.server.controller.admin;

import com.zll.common.result.PageResult;
import com.zll.common.result.Result;
import com.zll.pojo.dto.BookDTO;
import com.zll.pojo.dto.BookPageQueryDTO;
import com.zll.pojo.entity.Book;
import com.zll.pojo.vo.BookVO;
import com.zll.server.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 书本相关的控制器
 */
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    /**
     * 新增图书
     * @param bookDTO
     * @return
     */
    @PostMapping
    public Result addBook(@Valid @RequestBody BookDTO bookDTO){
        bookService.addBook(bookDTO);
        return Result.success();
    }

    /**
     * 删除图书
     * @param id 要删除的图书id
     * @return
     */
    @DeleteMapping("/{id}")
    @Validated
    public Result deleteBook(@PathVariable @Min(value = 1, message = "ID必须为正数") Long id){
        bookService.deleteBook(id);
        return Result.success();
    }

    /**
     * 更新图书
     * @param bookDTO
     * @param id 图书Id
     * @return
     */
    @PutMapping("/{id}")
    public Result updateBook(@Valid @RequestBody BookDTO bookDTO,@PathVariable @Min(1) Long id) {
        log.info("bookDTO:{}",bookDTO);
        bookDTO.setId(id);
        bookService.updateBook(bookDTO);
        return Result.success();
    }


    /**
     * 获取所有图书列表
     * @param page 页码
     * @param pageSize 每页数量
     * @param sort 排序规则
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> getBooks(@RequestParam("page")@Min(1) int page,
                                       @RequestParam("pageSize")@Min(1) int pageSize,
                                       @RequestParam(value = "sort", defaultValue = "title,asc") String sort) {
        BookPageQueryDTO bookPageQueryDTO = new BookPageQueryDTO(page, pageSize, sort);
        PageResult pageResult = bookService.getBooks(bookPageQueryDTO);
        return Result.success(pageResult);
    }


    //获取单本图书信息
    @GetMapping("/{id}")
    public Result<BookVO> getBookById(@Valid @PathVariable Long id) {
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

}
