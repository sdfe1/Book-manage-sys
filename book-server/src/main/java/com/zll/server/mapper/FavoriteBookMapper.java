package com.zll.server.mapper;

import com.zll.pojo.dto.BookDTO;
import com.zll.pojo.entity.FavoriteBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FavoriteBookMapper {

    @Insert("insert into favorite(book_id,user_id,create_time) values(#{bookId},#{userId},#{createTime})")
    void insert(FavoriteBook favoriteBook);

    @Delete(value = "delete from favorite where user_id = #{userId} and book_id = #{bookId}")
    void delete(Long userId, Long bookId);

    //
    // TODO
    @Select("select b.id,b.title,b.isbn,b.publisher,b.author,b.publish_date,b.price,b.stock from favorite f join book b on f.book_id = b.id where f.user_id = #{userId} ;")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "isbn", column = "isbn"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "author", column = "author"),
            @Result(property = "publishDate", column = "publish_date"),
            @Result(property = "price", column = "price"),
            @Result(property = "stock", column = "stock")
    })
    List<BookDTO> getFavoriteBooks(Long userId);

}
