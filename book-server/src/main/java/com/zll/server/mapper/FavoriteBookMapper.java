package com.zll.server.mapper;

import com.github.pagehelper.Page;
import com.zll.pojo.dto.BookDTO;
import com.zll.pojo.dto.FavoritePageQueryDTO;
import com.zll.pojo.entity.FavoriteBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 图书收藏接口
 */
@Mapper
public interface FavoriteBookMapper {

    @Insert("insert into favorite(book_id,user_id,create_time) values(#{bookId},#{userId},#{createTime})")
    void insert(FavoriteBook favoriteBook);

    @Delete(value = "delete from favorite where user_id = #{userId} and book_id = #{bookId}")
    void delete(Long userId, Long bookId);

    Page<FavoriteBook> pageQuery(FavoritePageQueryDTO favoritePageQueryDTO);

    @Select("select * from favorite where user_id = #{userId} and book_id = #{bookId}")
    FavoriteBook selectByBookIdAndUserId(Long bookId, Long userId);

}
