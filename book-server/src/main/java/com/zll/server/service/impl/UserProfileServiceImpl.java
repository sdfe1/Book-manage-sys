package com.zll.server.service.impl;

import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.base.BaseException;
import com.zll.pojo.dto.UserProfileDTO;
import com.zll.pojo.entity.UserProfile;
import com.zll.server.mapper.UserMapper;
import com.zll.server.mapper.UserProfileMapper;
import com.zll.server.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户个人资料实现类
 */
@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileMapper userProfileMapper;

    private final UserMapper userMapper;

    /**
     * 获取用户个人资料
     * @param userId
     * @return
     */
    @Override
    public UserProfile getUserProfile(Long userId) {
        if (userMapper.getUserById(userId) == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "用户不存在");
        }
        UserProfile userProfile = userProfileMapper.getUserProfile(userId);
        if (userProfile == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "用户个人资料不存在");
        }
        return userProfile;
    }

    /**
     * 更新个人资料
     * @param userId
     * @param userProfileDTO
     */
    @Override
    public void updateUserProfile(Long userId, UserProfileDTO userProfileDTO) {
        if (userProfileMapper.getUserProfile(userId) == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND,"获取用户信息失败");
        }
        UserProfile userProfile = UserProfile.builder()
                .userId(userId)
                .gender(userProfileDTO.getGender())
                .location(userProfileDTO.getLocation())
                .bio(userProfileDTO.getBio())
                .interests(userProfileDTO.getInterests())
                .privacyLevel(userProfileDTO.getPrivacyLevel())
                .build();
        userProfileMapper.updateUserProfile(userProfile);
    }
}
