package com.zll.server.controller.admin;


import com.zll.common.result.PageResult;
import com.zll.common.result.Result;
import com.zll.pojo.dto.BookDTO;
import com.zll.pojo.dto.BookPageQueryDTO;
import com.zll.pojo.entity.Book;
import com.zll.pojo.vo.BookVO;
import com.zll.server.service.BookCategoryService;
import com.zll.server.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 图书管理控制器
 */
@RestController
@RequestMapping("/admin/books")
@Tag(name = "ADMIN-图书管理")
@RequiredArgsConstructor
@Slf4j
public class AdminBookController {

    private final BookService bookService;

    private final BookCategoryService bookCategoryService;


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
    public Result deleteBook(@PathVariable Long id){
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
    public Result updateBook(@Valid @RequestBody BookDTO bookDTO,@PathVariable Long id) {
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
    @GetMapping("/{id}")
    public Result<BookVO> getBookById(@Valid @PathVariable Long id) {
        Book book = bookService.getBookById(id);
        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(book,bookVO);
        return Result.success(bookVO);
    }

    //图书数量统计


    /**
     * 为图书设置分类
     * @param bookId 书本id
     * @param categoryId 分类Id
     * @return
     */
    @PostMapping("/{bookId}/categories/{categoryId}")
    public Result addBookCategory(@PathVariable Long bookId, @PathVariable Integer categoryId) {
        bookCategoryService.addBookCategory(bookId, categoryId);
        return Result.success();
    }

    /**
     * 删除图书分类
     * @param bookId 书本id
     * @param categoryId 分类Id
     * @return
     */
    @DeleteMapping("/{bookId}/categories/{categoryId}")
    public Result deleteBookCategory(@PathVariable Long bookId, @PathVariable Integer categoryId) {
        bookCategoryService.deleteBookCategory(bookId, categoryId);
        return Result.success();
    }



    /**
     * 获取图书的分类
     * @param bookId 书本id
     * @return 统一响应
     */
    @GetMapping("/{bookId}/categories")
    public Result<Integer> getCategoryByBookId( @PathVariable Long bookId) {
        return Result.success(bookCategoryService.getCategoryByBookId(bookId));
    }
}
