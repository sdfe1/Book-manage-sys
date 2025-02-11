package com.zll.server.controller.admin;

import com.zll.common.context.BaseContext;
import com.zll.common.result.Result;
import com.zll.pojo.vo.UserVO;
import com.zll.server.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class FollowController {

/*    - `POST /api/users/{userId}/follow`: 关注用户
- `GET /api/users/{userId}/followers`: 获取用户的关注者列表
- `GET /api/users/{userId}/following`: 获取用户关注的用户列表*/


    @Autowired
    private FollowService followService;

    @PostMapping("/users/{userId}/follow")
    public Result followUser(@PathVariable Long userId) {
        Long currentUserId = BaseContext.getCurrentId();
        followService.followUser(currentUserId, userId);
        return Result.success();
    }

    @DeleteMapping("/users/{userId}/follow")
    public Result unfollowUser(@PathVariable Long userId) {
        Long currentUserId = BaseContext.getCurrentId();
        followService.unfollowUser(currentUserId, userId);
        return Result.success();
    }

    //获取用户的粉丝列表
    @GetMapping("/users/{userId}/followers")
    public Result<List<UserVO>> getFollowers(@PathVariable Long userId) {
         List<UserVO> followers = followService.getFollowers(userId);
        return Result.success(followers);
    }


    //获取用户关注的博主列表
    @GetMapping("/users/{userId}/following")
    public Result<List<UserVO>> getFollowing(@PathVariable Long userId) {
        List<UserVO> following = followService.getFollowing(userId);
        return Result.success(following);
    }
}
