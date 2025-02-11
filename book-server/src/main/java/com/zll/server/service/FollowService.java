package com.zll.server.service;


import com.zll.pojo.vo.UserVO;

import java.util.List;

public interface FollowService {
    void followUser(Long currentUserId, Long userId);

    void unfollowUser(Long currentUserId, Long userId);

    List<UserVO> getFollowers(Long userId);

    List<UserVO> getFollowing(Long userId);

}
