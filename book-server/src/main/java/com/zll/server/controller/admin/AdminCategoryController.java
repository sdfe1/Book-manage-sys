package com.zll.server.controller.admin;

import com.zll.common.result.PageResult;
import com.zll.common.result.Result;
import com.zll.pojo.dto.BookCategoryQueryDTO;
import com.zll.pojo.dto.CategoryDTO;
import com.zll.pojo.dto.CategoryPageQueryDTO;
import com.zll.pojo.entity.Category;
import com.zll.server.service.BookCategoryService;
import com.zll.server.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 分类管理控制器
 */
@RestController
@RequestMapping("/admin/categories")
@Tag(name = "ADMIN-分类管理")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    private final BookCategoryService bookCategoryService;

    /**
     * 新增分类
     * @param categoryDTO
     * @return
     */
    @Operation(summary = "新增分类")
    @PostMapping
    public Result addCategory(@Validated @RequestBody CategoryDTO categoryDTO) {
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }


    /**
     * 删除分类
     * @param id 待删除分类的id
     * @return
     */
    @Operation(summary = "删除分类")
    @DeleteMapping("/{id}")
    @Validated
    public Result deleteCategory(@PathVariable  int id) {
        categoryService.deleteCategory(id);
        return Result.success();
    }

    /**
     * 根据id获取分类
     * @param id 查询的分类Id
     * @return
     */
    @Operation(summary = "根据id获取分类")
    @GetMapping("/{id}")
    public Result<String> getCategory(@PathVariable int id) {
        Category category = categoryService.getCategoryByID(id);
        return Result.success(category.getName());
    };

    /**
     * 更新分类
     * @param id 分类id
     * @param categoryDTO
     * @return
     */
    @Operation(summary = "更新分类")
    @PutMapping("/{id}")
    public Result updateCategory(@PathVariable int id,  @RequestBody @Valid CategoryDTO categoryDTO) {
        categoryDTO.setId(id);
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }


    /**
     * 获取所有分类
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @Operation(summary = "获取所有分类")
    @GetMapping
    public Result<PageResult> getCategories(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize,
            @RequestParam(value = "name", defaultValue = "") String name) {
        CategoryPageQueryDTO categoryPageQueryDTO = new CategoryPageQueryDTO(page, pageSize, name);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 根据分类Id获取图书
     * @param categoryId
     * @param page
     * @param pageSize
     * @return
     */
    @Operation(summary = "根据分类Id获取图书")
    @GetMapping("/{categoryId}/books")
    public Result<PageResult> getBookByCategoryId( @PathVariable Integer categoryId,
                                                   @RequestParam("page") int page,
                                                   @RequestParam("pageSize") int pageSize) {
        PageResult pageResult = bookCategoryService.getBookByCategoryId(new BookCategoryQueryDTO(page, pageSize,categoryId));
        return Result.success(pageResult);
    }
}
