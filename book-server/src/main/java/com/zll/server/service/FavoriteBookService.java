package com.zll.server.service;

import com.zll.common.result.PageResult;
import com.zll.pojo.dto.BookDTO;
import com.zll.pojo.dto.FavoriteBookDTO;
import com.zll.pojo.dto.FavoritePageQueryDTO;

import java.util.List;

public interface FavoriteBookService {

    void addFavoriteBook(Long userId, Long bookId);

    void removeFavoriteBook(Long userId, Long bookId);

    PageResult getFavoriteBooks(FavoritePageQueryDTO favoritePageQueryDTO);

}
