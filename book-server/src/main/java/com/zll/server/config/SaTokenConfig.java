package com.zll.server.config;

import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.result.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class SaTokenConfig {
    @Bean
    public SaServletFilter saServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .setAuth(obj -> {
                    // 先校验角色，再校验登录
                    SaRouter.match("/admin/**").check(r -> StpUtil.checkRole("ADMIN"));
                    SaRouter.match("/user/**", "/users/**").check(r -> StpUtil.checkRole("USER"));

                    // 最后处理全局登录校验
                    SaRouter.match("/**")
                            .notMatch("/doc.html", "/v3/api-docs/**", "/auth/login", "/auth/register", "/swagger-ui.html")
                            .check(StpUtil::checkLogin);
                })
                .setError(e -> {
                    // 登录校验失败的回调
                    return Result.error(CommonErrorCodeEnum.UNAUTHORIZED.getCode(), "没有权限访问");
                });

    }
}
