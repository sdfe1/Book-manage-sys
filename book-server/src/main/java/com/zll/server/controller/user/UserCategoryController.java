package com.zll.server.controller.user;

import com.zll.common.result.PageResult;
import com.zll.common.result.Result;
import com.zll.pojo.dto.BookCategoryQueryDTO;
import com.zll.server.service.BookCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/categories")
@Tag(name = "USER-分类功能")
@RequiredArgsConstructor
public class UserCategoryController {

    private final BookCategoryService bookCategoryService;

    //根据分类id获取图书信息
    @GetMapping("/{categoryId}/books")
    public Result<PageResult> getBookByCategoryId(@PathVariable Integer categoryId,   // 从 URL 中获取 categoryId
                                                  @RequestParam("page") int page,     // 从查询参数中获取分页参数 page
                                                  @RequestParam("pageSize") int pageSize) {
        PageResult pageResult = bookCategoryService.getBookByCategoryId(new BookCategoryQueryDTO(page, pageSize,categoryId));
        return Result.success(pageResult);
    }
}
