package com.zll.server.service.impl;

import com.zll.common.result.Result;
import com.zll.pojo.dto.UserProfileDTO;
import com.zll.pojo.entity.UserProfile;
import com.zll.server.mapper.UserProfileMapper;
import com.zll.server.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Override
    public UserProfile getUserProfile(Long userId) {
        if (userProfileMapper.getUserProfile(userId) == null) {
            throw new RuntimeException("获取用户信息失败");
        }
        return userProfileMapper.getUserProfile(userId);
    }

    @Override
    public void updateUserProfile(Long userId, UserProfileDTO userProfileDTO) {
        if (userProfileMapper.getUserProfile(userId) == null) {
            throw new RuntimeException("获取用户信息失败");
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
