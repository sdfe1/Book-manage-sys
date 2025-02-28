package com.zll.server.controller.user;

import com.zll.common.result.PageResult;
import com.zll.common.result.Result;
import com.zll.pojo.dto.BookCategoryQueryDTO;
import com.zll.pojo.dto.CategoryPageQueryDTO;
import com.zll.server.service.BookCategoryService;
import com.zll.server.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户查询分类控制器
 */
@RestController
@RequestMapping("/user/categories")
@Tag(name = "USER-分类功能")
@RequiredArgsConstructor
public class UserCategoryController {

    private final BookCategoryService bookCategoryService;

    private final CategoryService categoryService;


    /**
     * 根据分类id获取图书信息
     * @param categoryId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/{categoryId}/books")
    public Result<PageResult> getBookByCategoryId(@PathVariable Integer categoryId,
                                                  @RequestParam("page") int page,
                                                  @RequestParam("pageSize") int pageSize) {
        PageResult pageResult = bookCategoryService.getBookByCategoryId(new BookCategoryQueryDTO(page, pageSize,categoryId));
        return Result.success(pageResult);
    }

    /**
     * 获取所有分类
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping
    public Result<PageResult> getCategories(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize,
            @RequestParam(value = "name", defaultValue = "") String name) {
        CategoryPageQueryDTO categoryPageQueryDTO = new CategoryPageQueryDTO(page, pageSize, name);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

}
