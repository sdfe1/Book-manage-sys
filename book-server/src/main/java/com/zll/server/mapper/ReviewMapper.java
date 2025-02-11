package com.zll.server.mapper;

import com.zll.pojo.entity.Review;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReviewMapper {
    @Insert("insert into review(book_id,user_id,parent_id,content,rating,create_time) values(#{bookId},#{userId},#{parentId},#{content},#{rating},#{createTime})")
    void insert(Review review);

    @Select("select id,user_id,book_id,parent_id,content,rating,create_time from review where book_id = #{bookId}")
    List<Review> getReviews(Long bookId);

    @Delete("delete from review where id = #{reviewId}")
    void deleteReview(Long reviewId);

}
