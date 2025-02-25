package com.zll.server.controller.user;

import com.zll.common.result.PageResult;
import com.zll.common.result.Result;
import com.zll.pojo.dto.ReviewPageQueryDTO;
import com.zll.pojo.entity.Review;
import com.zll.pojo.dto.ReviewDTO;
import com.zll.server.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论功能控制器
 */
@RestController
@RequestMapping("/user/books/{bookId}/reviews")
@Tag(name = "USER-评论功能")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 添加评论
     * @param bookId
     * @param reviewDTO
     * @returnc
     */
    @PostMapping
    public Result addReview(@PathVariable Long bookId, @Valid @RequestBody ReviewDTO reviewDTO) {
        reviewService.addReview(bookId, reviewDTO);
        return Result.success();
    }

    /**
     * 获取图书的评论
     * @param bookId
     * @return
     */
    @GetMapping
    public Result<PageResult> getReviews(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize,
            @PathVariable Long bookId) {
         ReviewPageQueryDTO reviewPageQueryDTO = new ReviewPageQueryDTO(page, pageSize, bookId);
         PageResult pageResult = reviewService.getReviews(reviewPageQueryDTO);
         return Result.success(pageResult);
    }


    /**
     * 添加评论
     * @param bookId
     * @param reviewId
     * @param reviewDTO
     * @return
     */
    @PostMapping("/{reviewId}/replies")
    public Result addReply(@PathVariable Long bookId, @PathVariable Long reviewId,@Valid @RequestBody ReviewDTO reviewDTO) {
        reviewService.addReplies(reviewId, reviewDTO);
        return Result.success();
    }


    /**
     * 删除评论
     * @param bookId
     * @param reviewId
     * @return
     */
    @DeleteMapping("/{reviewId}")
    public Result deleteReview(@PathVariable Long bookId, @PathVariable Long reviewId) {
        reviewService.deleteReview(bookId, reviewId);
        return Result.success();
    }
}
