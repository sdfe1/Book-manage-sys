package com.zll.server.controller.admin;

import com.zll.common.result.Result;
import com.zll.pojo.entity.Review;
import com.zll.pojo.dto.ReviewDTO;
import com.zll.server.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    /*- - - `POST /api/books/{bookId}/reviews`: 添加评论
        - `GET /api/books/{bookId}/reviews`: 获取图书的评论列表
        - `POST /api/reviews/{reviewId}/replies`: 回复评论
        - `DELETE /api/reviews/{reviewId}`: 删除评论
- **图书标签与分类管理**
    * */


    @PostMapping("/books/{bookId}/reviews")
    public Result addReview(@PathVariable Long bookId, @RequestBody ReviewDTO reviewDTO) {
        reviewService.addReview(bookId, reviewDTO);
        return Result.success();
    }

    @GetMapping("/books/{bookId}/reviews")
    public Result<List<Review>> getReviews(@PathVariable Long bookId) {
        return Result.success(reviewService.getReviews(bookId));
    }

    @PostMapping("/reviews/{reviewId}/replies")
    public Result addReply(@PathVariable Long reviewId, @RequestBody ReviewDTO reviewDTO) {
        reviewService.addReplies(reviewId, reviewDTO);
        return Result.success();
    }

    @DeleteMapping("/reviews/{reviewId}")
    public Result deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return Result.success();
    }
}
