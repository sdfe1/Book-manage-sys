package com.zll.server.controller.auth;

import cn.dev33.satoken.stp.StpUtil;
import com.zll.common.result.Result;
import com.zll.pojo.dto.UserLoginDTO;
import com.zll.pojo.dto.UserRegisterDTO;
import com.zll.pojo.entity.User;
import com.zll.pojo.vo.UserLoginVO;
import com.zll.server.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * 认证管理控制器
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "用户管理")
@Slf4j
public class AuthController {

    private final UserService userService;

    /**
     * 注册功能
     * @param userRegisterDTO 注册参数
     * @return 统一响应
     */
    @Operation(summary = "注册")
    @PostMapping(value = "/register")
    public Result register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        userService.register(userRegisterDTO);
        return Result.success();
    }

    /**
     * @param userLoginDTO 登录参数
     * @return 统一响应
     */
    @Operation(summary = "登录")
    @PostMapping(value = "/login")
    public Result<UserLoginVO> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        User user= userService.login(userLoginDTO);
        //1.登录,sotoken会自动存入cookie
        StpUtil.login(user.getId());
        //2.返回响应信息
        return Result.success(new UserLoginVO(user.getId(),user.getRole().getValue()));
    }
}
