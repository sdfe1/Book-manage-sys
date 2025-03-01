package com.zll.server.mapper;

import com.github.pagehelper.Page;
import com.zll.pojo.dto.BookCategoryQueryDTO;
import com.zll.pojo.entity.BookCategory;
import com.zll.pojo.entity.Category;
import com.zll.pojo.vo.BookVO;
import org.apache.ibatis.annotations.*;

/**
 * 图书分类关联接口
 */
@Mapper
public interface BookCategoryMapper {

    @Insert("insert into book_category (category_id,book_id) values (#{categoryId},#{bookId})")
    void insert(Long bookId, Integer categoryId);

    @Delete("delete from book_category where category_id = #{categoryId} and book_id = #{bookId}")
    void delete(Long bookId, Integer categoryId);

    @Update("update book_category set category_id = #{categoryId},book_id = #{bookId} where category_id = #{categoryId} and book_id = #{bookId}")
    void update(BookCategory bookCategory);

    Category selectCategoryByBookId(Long bookId);

    Integer selectExistsByBookIdAndCategoryId(Long bookId, Integer categoryId);
    Page<BookVO> pageQuery(BookCategoryQueryDTO bookCategoryQueryDTO);

}
