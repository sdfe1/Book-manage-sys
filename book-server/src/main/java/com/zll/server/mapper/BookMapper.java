package com.zll.server.mapper;

import com.zll.pojo.dto.BookDTO;
import com.zll.pojo.entity.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

@Mapper
public interface BookMapper {

    @Insert("insert into book (title,isbn,publisher,author,publish_date,price,stock,create_time,update_time,create_user,update_user)" +
            " values " +
            "(#{title},#{isbn},#{publisher},#{author},#{publishDate},#{price},#{stock},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void insert(Book book) ;

    @Delete("delete from book where id = #{id}")
    void deleteBook(Long id);

    @Select("select * from book where id = #{id}")
    Book getBookById(Long id);


    void updateBook(Book book);

}
