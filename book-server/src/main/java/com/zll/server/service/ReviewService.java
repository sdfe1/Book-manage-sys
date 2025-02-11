package com.zll.server.service;

import com.zll.pojo.entity.Review;
import com.zll.pojo.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    void addReview(Long bookId, ReviewDTO reviewDTO);

    List<Review> getReviews(Long bookId);

    void addReplies(Long reviewId, ReviewDTO reviewDTO);

    void deleteReview(Long reviewId);

}
