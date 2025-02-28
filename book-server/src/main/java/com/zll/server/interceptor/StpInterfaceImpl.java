package com.zll.server.interceptor;

import cn.dev33.satoken.stp.StpInterface;
import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.BaseException;
import com.zll.pojo.entity.User;
import com.zll.server.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class StpInterfaceImpl implements StpInterface {

    private final UserMapper userMapper;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return Collections.emptyList();
    }

    /**
     * 返回一个账号所拥有的角色标识集合 （USER, ADMIN）
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Long id = Long.parseLong((String) loginId);
        User user = userMapper.getUserById(id);
        if (user == null) {
            throw new BaseException(CommonErrorCodeEnum.NOT_FOUND, "用户不存在");
        }
        String name = user.getRole().name();
        return Collections.singletonList(name);
    }

}
