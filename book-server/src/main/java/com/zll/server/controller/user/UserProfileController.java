package com.zll.server.controller.user;

import cn.dev33.satoken.stp.StpUtil;
import com.zll.common.context.BaseContext;
import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.BaseException;
import com.zll.common.result.Result;
import com.zll.pojo.dto.UserProfileDTO;
import com.zll.pojo.em.PrivacyLevel;
import com.zll.pojo.entity.UserProfile;
import com.zll.server.service.UserProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户个人资料控制器
 */
@RestController
@RequestMapping("/users/{userId}/profile")
@Tag(name = "USER-个人资料功能")
@Validated
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    /**
     * 获取用户个人资料
     * @param userId 获取的用户ID
     * @return
     */
    @GetMapping
    public Result<UserProfileDTO> getUserProfile(@PathVariable  Long userId) {
        // 获取当前登录用户ID
        Long currentUserId = BaseContext.getCurrentId();
        // 查询目标用户资料
        UserProfile userProfile = userProfileService.getUserProfile(userId);
        // 权限校验
        if (userProfile.getPrivacyLevel() == PrivacyLevel.PRIVATE
                && !currentUserId.equals(userId)) {
            throw new BaseException(CommonErrorCodeEnum.UNAUTHORIZED,"该用户资料已设置为私有");
        }
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        BeanUtils.copyProperties(userProfile, userProfileDTO);
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
