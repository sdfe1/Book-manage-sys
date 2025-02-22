package com.zll.server.service;

import com.zll.common.result.PageResult;
import com.zll.pojo.dto.ReviewPageQueryDTO;
import com.zll.pojo.entity.Review;
import com.zll.pojo.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    void addReview(Long bookId, ReviewDTO reviewDTO);

     PageResult getReviews(ReviewPageQueryDTO reviewPageQueryDTO
     );

    void addReplies(Long reviewId, ReviewDTO reviewDTO);

    void deleteReview(Long bookId, Long reviewId);

}
