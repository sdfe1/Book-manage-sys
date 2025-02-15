package com.zll.server.mapper;

import com.github.pagehelper.Page;
import com.zll.pojo.dto.BookCategoryQueryDTO;
import com.zll.pojo.entity.BookCategory;
import com.zll.pojo.vo.BookVO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BookCategoryMapper {

    @Insert("insert into book_category (category_id,book_id) values (#{categoryId},#{bookId})")
    void insert(BookCategory bookCategory);

    @Delete("delete from book_category where category_id = #{categoryId} and book_id = #{bookId}")
    void delete(BookCategory bookCategory);

    @Update("update book_category set category_id = #{categoryId},book_id = #{bookId} where category_id = #{categoryId} and book_id = #{bookId}")
    void update(BookCategory bookCategory);

    @Select("select * from book_category where book_id = #{bookId}")
    BookCategory selectByBookId(Long bookId);

    Page<BookVO> pageQuery(BookCategoryQueryDTO bookCategoryQueryDTO);

}
