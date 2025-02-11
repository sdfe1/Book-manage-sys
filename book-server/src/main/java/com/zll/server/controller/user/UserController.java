package com.zll.server.controller.user;

import cn.dev33.satoken.stp.StpUtil;
import com.zll.common.result.Result;
import com.zll.pojo.dto.UserLoginDTO;
import com.zll.pojo.dto.UserRegisterDTO;
import com.zll.pojo.entity.User;
import com.zll.pojo.vo.UserLoginVO;
import com.zll.server.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Tag(name = "用户管理")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "注册")
    @PostMapping(value = "/register")
    @ResponseBody
    public Result register(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("注册");
        userService.register(userRegisterDTO);
        return Result.success();
    }


    @Operation(summary = "登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        User user= userService.login(userLoginDTO);
        StpUtil.login(user.getId());
        //获取sotoken
        String sotoken = StpUtil.getTokenValue();
        log.info("sotoken: {}", sotoken);
        return Result.success(new UserLoginVO(user.getId(),user.getRole().getValue()));
    }





}
