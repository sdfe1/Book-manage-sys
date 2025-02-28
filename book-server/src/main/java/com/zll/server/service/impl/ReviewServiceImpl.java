package com.zll.server.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.BaseException;
import com.zll.common.result.PageResult;
import com.zll.pojo.dto.ReviewPageQueryDTO;
import com.zll.pojo.entity.Review;
import com.zll.pojo.dto.ReviewDTO;
import com.zll.server.mapper.ReviewMapper;
import com.zll.server.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 评论功能服务实现类
 */
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;

    /**
     * 添加评论
     * @param bookId 书本id
     * @param reviewDTO 评论内容
     */
    @Override
    public void addReview(Long bookId, ReviewDTO reviewDTO) {
        Review review = Review.builder()
                .bookId(bookId)
                .userId(reviewDTO.getUserId())
                .content(reviewDTO.getContent())
                .rating(reviewDTO.getRating())
                .parentId(null)//添加评论默认父级为Null
                .createTime(LocalDateTime.now())
                .build();
        reviewMapper.insert(review);
    }


    @Override
    public PageResult getReviews(ReviewPageQueryDTO reviewPageQueryDTO) {

        PageHelper.startPage(reviewPageQueryDTO.getPage(),reviewPageQueryDTO.getPageSize());

        //下一条sql进行分页，自动加入limit关键字分页
        Page<Review> page =  reviewMapper.pageQuery(reviewPageQueryDTO);
        return new PageResult(
                page.getTotal(),
                page.getResult()
        );
    }

    /**
     * 回复评论
     * @param reviewId 父级评论id
     * @param reviewDTO 回复内容
     */
    @Override
    public void addReplies(Long reviewId, ReviewDTO reviewDTO) {
        Review parentReview = reviewMapper.selectById(reviewId);
        if (parentReview == null) {
           throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "评论不存在");
        }
        Review review = Review.builder()
                .userId(reviewDTO.getUserId())
                .content(reviewDTO.getContent())
                .rating(reviewDTO.getRating())
                .parentId(reviewId)
                .bookId(parentReview.getBookId())
                .createTime(LocalDateTime.now())
                .build();
        reviewMapper.insert(review);
    }


    /**
     * 删除评论
     * @param bookId
     * @param reviewId
     */
    @Override
    public void deleteReview(Long bookId, Long reviewId) {
        Review review = reviewMapper.selectById(reviewId);
        if (review == null || !review.getBookId().equals(bookId)) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND,"评论不存在或无权删除");
        }
        reviewMapper.deleteReview(reviewId);
    }


}
