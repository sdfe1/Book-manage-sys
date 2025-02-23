package com.zll.server.controller.admin;

import com.zll.common.result.Result;
import com.zll.pojo.dto.UserProfileDTO;
import com.zll.pojo.entity.UserProfile;
import com.zll.server.service.UserProfileService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户个人资料控制器
 */
@RestController
@RequestMapping("/users/{userId}/profile")
@Validated
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    /**
     * 获取用户个人资料
     * @param userId
     * @return
     */
    @GetMapping
    public Result<UserProfileDTO> getUserProfile(@PathVariable @Min(1) Long userId) {
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

    /**
     * 更新个人资料
     * @param userId
     * @param userProfileDTO
     * @return
     */
    @PostMapping
    public Result updateUserProfile(@PathVariable Long userId, @RequestBody @Valid UserProfileDTO userProfileDTO) {
        userProfileService.updateUserProfile(userId, userProfileDTO);
        return Result.success();
    }



}
