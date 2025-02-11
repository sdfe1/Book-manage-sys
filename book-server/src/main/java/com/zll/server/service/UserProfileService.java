package com.zll.server.service;

import com.zll.common.result.Result;
import com.zll.pojo.dto.UserProfileDTO;
import com.zll.pojo.entity.UserProfile;

public interface UserProfileService {

    UserProfile getUserProfile(Long userId);


    void updateUserProfile(Long userId, UserProfileDTO userProfileDTO);
}
