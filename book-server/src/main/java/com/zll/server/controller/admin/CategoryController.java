package com.zll.server.controller.admin;

import com.zll.common.result.PageResult;
import com.zll.common.result.Result;
import com.zll.common.validation.CreateGroup;
import com.zll.common.validation.UpdateGroup;
import com.zll.pojo.dto.CategoryDTO;
import com.zll.pojo.dto.CategoryPageQueryDTO;
import com.zll.pojo.entity.Book;
import com.zll.pojo.entity.Category;
import com.zll.server.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类相关接口
 */
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 新增分类
     * @param categoryDTO
     * @return
     */
    @PostMapping
    public Result addCategory(@Validated(CreateGroup.class) @RequestBody CategoryDTO categoryDTO) {
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }


    /**
     * 删除分类
     * @param id 待删除分类的id
     * @return
     */
    @DeleteMapping("/{id}")
    @Validated
    public Result deleteCategory(@PathVariable @Min(value = 1, message = "ID必须为正数") int id) {
        categoryService.deleteCategory(id);
        return Result.success();
    }

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
    @PutMapping("/{id}")
    @Validated(UpdateGroup.class)
    public Result updateCategory(@PathVariable @Min(value = 1, message = "ID必须为正数") int id,  @RequestBody @Valid CategoryDTO categoryDTO) {
        categoryDTO.setId(id);
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }


    //- `GET /api/categories`: 获取所有分类(前提：分页)






    //`GET /api/books?categoryId=1`: 根据分类ID获取图书
    @GetMapping
    public Result<List<Book>> getBooksByCategoryId(@RequestParam int categoryId) {
        
        return Result.success();
    }


    @GetMapping("/page")
    public Result<PageResult> page(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize,
            @RequestParam(value = "name", defaultValue = "") String name) {

        CategoryPageQueryDTO categoryPageQueryDTO = new CategoryPageQueryDTO(page, pageSize, name);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }
}
