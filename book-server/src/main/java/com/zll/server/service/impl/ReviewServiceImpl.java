package com.zll.server.service.impl;

import com.zll.pojo.entity.Review;
import com.zll.pojo.dto.ReviewDTO;
import com.zll.server.mapper.ReviewMapper;
import com.zll.server.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public void addReview(Long bookId, ReviewDTO reviewDTO) {
        Review review = Review.builder()
                .bookId(bookId)
                .userId(reviewDTO.getUserId())
                .content(reviewDTO.getContent())
                .rating(reviewDTO.getRating())
                .parentId(reviewDTO.getParentId())
                .createTime(LocalDateTime.now())
                .build();
        reviewMapper.insert(review);
    }

    @Override
    public List<Review> getReviews(Long bookId) {
        List<Review> reviews = reviewMapper.getReviews(bookId);
        return reviews;
    }

    @Override
    public void addReplies(Long reviewId, ReviewDTO reviewDTO) {
        Review review = Review.builder()
                .userId(reviewDTO.getUserId())
                .content(reviewDTO.getContent())
                .rating(reviewDTO.getRating())
                .parentId(reviewDTO.getParentId())
                .createTime(LocalDateTime.now())
                .bookId(reviewDTO.getBookId())
                .build();
        reviewMapper.insert(review);
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewMapper.deleteReview(reviewId);
    }


}
