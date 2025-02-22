package com.zll.server.controller.admin;

import com.zll.common.result.Result;
import com.zll.pojo.dto.UserProfileDTO;
import com.zll.pojo.entity.UserProfile;
import com.zll.server.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/users/{userId}/profile")
    public Result<UserProfileDTO> getUserProfile(@PathVariable Long userId) {
        UserProfile userProfile = userProfileService.getUserProfile(userId);
        UserProfileDTO userProfileDTO = UserProfileDTO.builder()
                .userId(userProfile.getUserId())
                .gender(userProfile.getGender())
                .location(userProfile.getLocation())
                .bio(userProfile.getBio())
                .interests(userProfile.getInterests())
                .privacyLevel(userProfile.getPrivacyLevel())
                .build();
        return Result.success(userProfileDTO);
    }

    @PostMapping("/users/{userId}/profile")
    public Result updateUserProfile(@PathVariable Long userId, @RequestBody UserProfileDTO userProfileDTO) {
        userProfileService.updateUserProfile(userId, userProfileDTO);
        return Result.success();
    }

}
