package com.zll.server.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.FavoriteErrorException;
import com.zll.common.exception.book.BookErrorException;
import com.zll.common.result.PageResult;
import com.zll.pojo.dto.BookDTO;
import com.zll.pojo.dto.FavoritePageQueryDTO;
import com.zll.pojo.entity.FavoriteBook;
import com.zll.pojo.entity.Review;
import com.zll.server.mapper.BookMapper;
import com.zll.server.mapper.FavoriteBookMapper;
import com.zll.server.service.FavoriteBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 收藏图书服务实现类
 */
@Service
@RequiredArgsConstructor
public class FavoriteBookServiceImpl implements FavoriteBookService {

    private final FavoriteBookMapper favoriteBookMapper;

    private final BookMapper bookMapper;

    /**
     * 收藏图书
     * @param userId
     * @param bookId
     */
    @Override
    public void addFavoriteBook(Long userId,Long bookId) {
        //图书id是否存在
        if (bookMapper.getBookById(bookId) == null) {
            throw new BookErrorException(CommonErrorCodeEnum.NOT_FOUND, "图书不存在");
        }
        if (favoriteBookMapper.selectByBookIdAndUserId(bookId,userId) != null) {
            throw new FavoriteErrorException(CommonErrorCodeEnum.ALREADY_EXISTS, "已经收藏过");
        }
        FavoriteBook favoriteBook = FavoriteBook.builder()
                .bookId(bookId)
                .userId(userId)
                .createTime(LocalDateTime.now())
                .build();
        favoriteBookMapper.insert(favoriteBook);
    }

    /**
     * 取消收藏图书
     * @param userId
     * @param bookId
     */
    @Override
    public void removeFavoriteBook(Long userId, Long bookId) {
        //图书id是否存在
        if (bookMapper.getBookById(bookId) == null) {
            throw new BookErrorException(CommonErrorCodeEnum.NOT_FOUND, "图书不存在");
        }
        favoriteBookMapper.delete(userId,bookId);
    }

    /**
     * 获取收藏图书列表
     * @param favoritePageQueryDTO
     * @return
     */
    @Override
    public PageResult getFavoriteBooks(FavoritePageQueryDTO favoritePageQueryDTO) {
        PageHelper.startPage(favoritePageQueryDTO.getPage(),favoritePageQueryDTO.getPageSize());
        Page<FavoriteBook> page =  favoriteBookMapper.pageQuery(favoritePageQueryDTO);
        return new PageResult(
                page.getTotal(),
                page.getResult()
        );

    }
}
