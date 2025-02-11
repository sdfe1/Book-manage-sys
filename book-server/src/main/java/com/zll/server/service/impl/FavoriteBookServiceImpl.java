package com.zll.server.service.impl;

import com.zll.pojo.dto.BookDTO;
import com.zll.pojo.entity.FavoriteBook;
import com.zll.server.mapper.FavoriteBookMapper;
import com.zll.server.service.FavoriteBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavoriteBookServiceImpl implements FavoriteBookService {

    @Autowired
    private FavoriteBookMapper favoriteBookMapper;

    @Override
    public void addFavoriteBook(Long userId,Long bookId) {
        FavoriteBook favoriteBook = FavoriteBook.builder()
                .bookId(bookId)
                .userId(userId)
                .createTime(LocalDateTime.now())
                .build();
        favoriteBookMapper.insert(favoriteBook);
    }

    @Override
    public void removeFavoriteBook(Long userId, Long bookId) {

        favoriteBookMapper.delete(userId,bookId);
    }

    @Override
    public List<BookDTO> getFavoriteBooks(Long userId) {
        List<BookDTO> favoriteBooks = favoriteBookMapper.getFavoriteBooks(userId);
        return favoriteBooks;
    }
}
