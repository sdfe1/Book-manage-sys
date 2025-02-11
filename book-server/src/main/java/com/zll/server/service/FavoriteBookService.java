package com.zll.server.service;

import com.zll.pojo.dto.BookDTO;
import com.zll.pojo.dto.FavoriteBookDTO;

import java.util.List;

public interface FavoriteBookService {

    void addFavoriteBook(Long userId, Long bookId);

    void removeFavoriteBook(Long userId, Long bookId);

    List<BookDTO> getFavoriteBooks(Long userId);

}
