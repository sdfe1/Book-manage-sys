package com.zll.server.controller.user;

import com.zll.common.context.BaseContext;
import com.zll.common.result.Result;
import com.zll.pojo.vo.UserVO;
import com.zll.server.service.FollowService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户关注功能控制器
 */
@RestController
@RequestMapping("/users/{userId}")
@Tag(name = "USER-关注用户功能")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    /**
     * 关注用户
     * @param userId
     * @return
     */
    @PostMapping("/follow")
    public Result followUser(@Valid @PathVariable Long userId) {
        Long currentUserId = BaseContext.getCurrentId();
        followService.followUser(currentUserId, userId);
        return Result.success();
    }

    /**
     * 取消关注
     * @param userId
     * @return
     */
    @DeleteMapping("/follow")
    public Result unfollowUser(@Valid @PathVariable Long userId) {
        Long currentUserId = BaseContext.getCurrentId();
        followService.unfollowUser(currentUserId, userId);
        return Result.success();
    }

    /**
     * 获取用户的粉丝列表
     * @param userId
     * @return
     */
    @GetMapping("/followers")
    public Result<List<UserVO>> getFollowers(@Valid @PathVariable Long userId) {
         List<UserVO> followers = followService.getFollowers(userId);
        return Result.success(followers);
    }


    /**
     * 获取用户关注的列表
     * @param userId
     * @return
     */
    @GetMapping("/following")
    public Result<List<UserVO>> getFollowing(@Valid @PathVariable Long userId) {
        List<UserVO> following = followService.getFollowing(userId);
        return Result.success(following);
    }
}
