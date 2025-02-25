package com.zll.server.controller.user;

import com.zll.common.result.PageResult;
import com.zll.common.result.Result;
import com.zll.pojo.dto.BookPageQueryDTO;
import com.zll.pojo.entity.Book;
import com.zll.pojo.vo.BookVO;
import com.zll.server.service.BookCategoryService;
import com.zll.server.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/books")
@Tag(name = "USER-图书查询功能")
@RequiredArgsConstructor
public class UserBookController {

    private final BookService bookService;

    private final BookCategoryService bookCategoryService;
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

    //根据图书Id获取图书分类信息
    @GetMapping("/{bookId}/categories")
    public Result<Integer> getCategoryByBookId( @PathVariable Long bookId) {
        return Result.success(bookCategoryService.getCategoryByBookId(bookId));
    }

}
