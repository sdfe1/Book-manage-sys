package com.zll.server.service.impl;

import com.zll.pojo.entity.Follow;
import com.zll.pojo.vo.UserVO;
import com.zll.server.mapper.FollowMapper;
import com.zll.server.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;

    @Override
    public void followUser(Long currentUserId, Long userId) {
        Follow follow = Follow.builder()
                .followerId(currentUserId)
                .followedId(userId)
                .build();
        followMapper.insert(follow);
    }

    @Override
    public void unfollowUser(Long currentUserId, Long userId) {
        Follow follow = Follow.builder()
                .followerId(currentUserId)
                .followedId(userId)
                .build();
        followMapper.delete(follow);
    }

    @Override
    public List<UserVO> getFollowers(Long userId) {
        List<UserVO> followers = followMapper.getFollowers(userId);
        return followers;
    }

    @Override
    public List<UserVO> getFollowing(Long userId) {
        List<UserVO> following = followMapper.getFollowing(userId);
        return following;
    }

}
