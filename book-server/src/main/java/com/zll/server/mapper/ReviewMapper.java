package com.zll.server.mapper;

import com.github.pagehelper.Page;
import com.zll.pojo.dto.ReviewPageQueryDTO;
import com.zll.pojo.entity.Review;
import com.zll.pojo.vo.BookVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 评论接口
 */
@Mapper
public interface ReviewMapper {
    @Insert("insert into review(book_id,user_id,parent_id,content,rating,create_time) values(#{bookId},#{userId},#{parentId},#{content},#{rating},#{createTime})")
    void insert(Review review);

    @Delete("delete from review where id = #{reviewId}")
    void deleteReview(Long reviewId);

    @Select("select * from review where id = #{reviewId}")
    Review selectById(Long reviewId);

    Page<Review> pageQuery(ReviewPageQueryDTO reviewPageQueryDTO);
}
