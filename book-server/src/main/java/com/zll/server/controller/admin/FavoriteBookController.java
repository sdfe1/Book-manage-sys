package com.zll.server.controller.admin;

import com.zll.common.result.Result;
import com.zll.pojo.dto.BookDTO;
import com.zll.server.service.FavoriteBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/favorites")
public class FavoriteBookController {
    @Autowired
    private FavoriteBookService favoriteBookService;
    //添加图书到收藏夹

    @PostMapping
    public Result addBookToFavorites(
            @PathVariable("userId") Long userId,
            @RequestBody BookDTO bookDTO) {

        // 处理添加收藏逻辑
        favoriteBookService.addFavoriteBook(userId, bookDTO.getId());

        return Result.success();
    }

    //从收藏夹中删除图书
    @DeleteMapping("/{bookId}")
    public Result removeBookFromFavorites(
            @PathVariable("userId") Long userId,
            @PathVariable("bookId") Long bookId) {

        // 处理删除收藏逻辑
        favoriteBookService.removeFavoriteBook(userId, bookId);

        return Result.success();
    }
    //获取收藏夹中的图书
    @GetMapping
    public Result<List<BookDTO>> getFavorites(
            @PathVariable("userId") Long userId) {

        List<BookDTO> favoriteBooks = favoriteBookService.getFavoriteBooks(userId);

        return Result.success(favoriteBooks);
    }

}
