package com.zll.server.interceptor;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.zll.common.context.BaseContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("sotoken");
        if (token != null) {
            // 2. 使用 So-Token 工具进行校验
            SaSession session = StpUtil.getSessionByLoginId(token);
            if (session == null || !StpUtil.isLogin()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token无效或已过期，请重新登录");
                return false;
            }

            // 3. 获取当前用户的 ID 并设置到 ThreadLocal
            Long userId = StpUtil.getLoginIdAsLong(); // 获取当前登录用户ID
            BaseContext.setCurrentId(userId); // 设置到 ThreadLocal
            log.info("当前用户ID：{}", userId);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("缺少Token，请登录");
            return false;
        }

        return true; // 继续请求处理

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        BaseContext.removeCurrentId();
    }

}
