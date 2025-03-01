package com.zll.server.controller.user;

import com.zll.common.result.PageResult;
import com.zll.common.result.Result;
import com.zll.pojo.dto.BookPageQueryDTO;
import com.zll.pojo.entity.Book;
import com.zll.pojo.vo.BookVO;
import com.zll.server.service.BookCategoryService;
import com.zll.server.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 查询图书功能控制器
 */
@RestController
@RequestMapping("/user/books")
@Tag(name = "USER-图书查询功能")
@RequiredArgsConstructor
public class UserBookController {

    private final BookService bookService;

    private final BookCategoryService bookCategoryService;

    /**
     * 获取所有图书列表
     * @param page 页码
     * @param pageSize 每页数量
     * @param sort 排序规则
     * @return
     */
    @Operation(summary = "获取所有图书列表")
    @GetMapping
    public Result<PageResult> getBooks(@RequestParam("page") int page,
                                       @RequestParam("pageSize") int pageSize,
                                       @RequestParam(value = "sort", defaultValue = "title,asc") String sort) {
        BookPageQueryDTO bookPageQueryDTO = new BookPageQueryDTO(page, pageSize, sort);
        PageResult pageResult = bookService.getBooks(bookPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 获取单本图书信息
     * @param id 图书id
     * @return 统一响应
     */
    @Operation(summary = "获取单本图书信息")
    @GetMapping("/{id}")
    public Result<BookVO> getBookById(@Valid @PathVariable Long id) {
        Book book = bookService.getBookById(id);
        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(book,bookVO);
        return Result.success(bookVO);
    }

    /**
     * 获取图书的分类
     * @param bookId 书本id
     * @return 统一响应
     */
    @Operation(summary = "获取图书的分类")
    @GetMapping("/{bookId}/categories")
    public Result<String> getCategoryByBookId( @PathVariable Long bookId) {
        String category = bookCategoryService.getCategoryByBookId(bookId);
        return Result.success(category);
    }

}
