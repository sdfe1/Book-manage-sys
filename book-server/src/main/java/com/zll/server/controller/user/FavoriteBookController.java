package com.zll.server.controller.user;

import com.zll.common.result.PageResult;
import com.zll.common.result.Result;
import com.zll.pojo.dto.BookDTO;
import com.zll.pojo.dto.FavoritePageQueryDTO;
import com.zll.server.service.FavoriteBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 收藏书籍功能控制
 *
 */
@RestController
@RequestMapping("/users/{userId}/favorites")
@Tag(name = "USER-收藏书籍功能")
@Validated
@RequiredArgsConstructor
public class FavoriteBookController {

    private final FavoriteBookService favoriteBookService;

    /**
     * 添加图书到收藏夹
     * @param userId
     * @param bookId
     * @return
     */
    @Operation(summary = "添加图书到收藏夹")
    @PostMapping("/{bookId}")
    public Result addBookToFavorites(@PathVariable  Long userId, @PathVariable  Long bookId) {

        favoriteBookService.addFavoriteBook(userId, bookId);
        return Result.success();
    }

    /**
     * 从收藏夹中删除图书
     * @param userId
     * @param bookId
     * @return
     */
    @Operation(summary = "从收藏夹中删除图书")
    @DeleteMapping("/{bookId}")
    public Result removeBookFromFavorites(@PathVariable Long userId, @PathVariable  Long bookId) {
        // 处理删除收藏逻辑
        favoriteBookService.removeFavoriteBook(userId, bookId);
        return Result.success();
    }

    /**
     * 获取收藏夹中的图书
     * @param userId
     * @return
     */
    @Operation(summary = "获取收藏夹的图书列表")
    @GetMapping
    public Result<PageResult> getFavorites(@RequestParam int page, @RequestParam int pageSize, @PathVariable  Long userId) {
        FavoritePageQueryDTO favoritePageQueryDTO = new FavoritePageQueryDTO(page, pageSize, userId);
        PageResult pageResult = favoriteBookService.getFavoriteBooks(favoritePageQueryDTO);
        return Result.success(pageResult);
    }

}
