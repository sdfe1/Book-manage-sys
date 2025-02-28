package com.zll.server.mapper;

import com.github.pagehelper.Page;
import com.zll.pojo.dto.BookPageQueryDTO;
import com.zll.pojo.entity.Book;
import com.zll.pojo.vo.BookVO;
import org.apache.ibatis.annotations.*;

/**
 * 书籍持久化接口
 */
@Mapper
public interface BookMapper {

    @Insert("insert into book (title,isbn,publisher,author,publish_date,price,stock,create_time,update_time,create_user,update_user)" +
            " values " +
            "(#{title},#{isbn},#{publisher},#{author},#{publishDate},#{price},#{stock},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void insert(Book book) ;

    @Delete("delete from book where id = #{id}")
    void deleteBook(Long id);

    @Select("SELECT id, title, isbn, publisher, author, publish_date FROM book WHERE id = #{id}")
    Book getBookById(Long id);

    void updateBook(Book book);

    Page<BookVO> pageQuery(BookPageQueryDTO bookPageQueryDTO);

    @Select("select isbn from book where isbn = #{isbn}")
    String ISBNExists(String isbn);

}
