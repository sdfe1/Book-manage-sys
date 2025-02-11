package com.zll.server.controller.admin;

import com.zll.common.result.PageResult;
import com.zll.common.result.Result;
import com.zll.pojo.dto.CategoryDTO;
import com.zll.pojo.dto.CategoryPageQueryDTO;
import com.zll.pojo.entity.Book;
import com.zll.pojo.entity.Category;
import com.zll.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类相关接口
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * @param categoryDTO
     * @return
     */
    @PostMapping
    public Result addCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }


    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<String> getCategory(@PathVariable int id) {
        Category category = categoryService.getCategoryByID(id);
        return Result.success(category.getName());
    };

    @PutMapping("/{id}")
    public Result updateCategory(@PathVariable int id, @RequestBody CategoryDTO categoryDTO) {
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

    //TODO 问题
    @GetMapping("/page")
    public Result<PageResult> page(@RequestParam CategoryPageQueryDTO categoryPageQueryDTO) {
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

}
