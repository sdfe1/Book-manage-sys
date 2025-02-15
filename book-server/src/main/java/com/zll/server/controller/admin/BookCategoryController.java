package com.zll.server.controller.admin;

import com.zll.common.result.PageResult;
import com.zll.common.result.Result;
import com.zll.pojo.dto.BookCategoryQueryDTO;
import com.zll.pojo.dto.CategoryDTO;
import com.zll.pojo.entity.BookCategory;
import com.zll.server.service.BookCategoryService;
import jakarta.validation.Valid;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookCategoryController {

    @Autowired
    private BookCategoryService bookCategoryService;
    //为图书创建新分类


    @PostMapping("/bookCategory")
    public Result addBookCategory(@Valid @RequestBody BookCategory bookCategory) {
        bookCategoryService.addBookCategory(bookCategory);

        return Result.success();
    }

    @DeleteMapping()
    //为图书删除分类
    public Result deleteBookCategory(@Valid @RequestBody BookCategory bookCategory) {
        bookCategoryService.deleteBookCategory(bookCategory);
        return Result.success();
    }

    //更新图书分类信息
    @PutMapping("/bookCategory")
    public Result updateBookCategory(@Valid @RequestBody BookCategory bookCategory) {
        bookCategoryService.updateBookCategory(bookCategory);
        return Result.success();
    }
    //根据图书Id获取图书分类信息
    @GetMapping("/bookCategory/{bookId}")
    public Result<Integer> getCategoryByBookId(@Valid @PathVariable Long bookId) {
        return Result.success(bookCategoryService.getCategoryByBookId(bookId));
    }
    //根据分类id获取图书信息
    @GetMapping("/bookCategory/category/{categoryId}")
    public Result<PageResult> getBookByCategoryId(@Valid @PathVariable Integer categoryId,   // 从 URL 中获取 categoryId
                                                  @RequestParam("page") int page,     // 从查询参数中获取分页参数 page
                                                  @RequestParam("pageSize") int pageSize) {
        PageResult pageResult = bookCategoryService.getBookByCategoryId(new BookCategoryQueryDTO(page, pageSize,categoryId));
        return Result.success(pageResult);
    }
}
