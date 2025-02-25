package com.zll.server.service.impl;

import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.base.BaseException;
import com.zll.pojo.entity.Follow;
import com.zll.pojo.vo.UserVO;
import com.zll.server.mapper.FollowMapper;
import com.zll.server.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户关注功能实现类
 */
@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowMapper followMapper;

    /**
     * 关注用户
     * @param currentUserId
     * @param userId
     */
    @Override
    public void followUser(Long currentUserId, Long userId) {
        if (currentUserId.equals(userId)) {
            throw new BaseException(CommonErrorCodeEnum.CONCURRENT_CONFLICT,"不能关注自己");
        }
        Follow follow = Follow.builder()
                .followerId(currentUserId)
                .followedId(userId)
                .build();
        followMapper.insert(follow);
    }

    /**
     * 取消关注
     * @param currentUserId
     * @param userId
     */
    @Override
    public void unfollowUser(Long currentUserId, Long userId) {
        Follow follow = Follow.builder()
                .followerId(currentUserId)
                .followedId(userId)
                .build();
        followMapper.delete(follow);
    }

    /**
     * 获取用户的粉丝列表
     * @param userId
     * @return
     */
    @Override
    public List<UserVO> getFollowers(Long userId) {
        List<UserVO> followers = followMapper.getFollowers(userId);
        return followers;
    }


    /**
     * 获取用户关注的列表
     * @param userId
     * @return
     */
    @Override
    public List<UserVO> getFollowing(Long userId) {
        List<UserVO> following = followMapper.getFollowing(userId);
        return following;
    }

}
