package com.zll.server.service.impl;


import com.zll.common.constant.StatusConstant;
import com.zll.common.context.BaseContext;
import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.auth.AccountLockedException;
import com.zll.common.exception.auth.AccountNameException;
import com.zll.common.exception.auth.PasswordErrorException;
import com.zll.common.utils.PasswordUtil;
import com.zll.pojo.dto.UserLoginDTO;
import com.zll.pojo.dto.UserRegisterDTO;
import com.zll.pojo.em.PrivacyLevel;
import com.zll.pojo.em.RoleEnum;
import com.zll.pojo.entity.User;
import com.zll.pojo.entity.UserProfile;
import com.zll.server.mapper.UserMapper;
import com.zll.server.mapper.UserProfileMapper;
import com.zll.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;

import static com.zll.common.utils.PasswordUtil.verifyPassword;


/**
 * 用户认证服务实现
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserProfileMapper userProfileMapper;

    /**
     * 登录
     * @param userLoginDTO 用户登录请求参数
     * @return 登录成功后的用户实体
     */
    @Override
    public User login(UserLoginDTO userLoginDTO) {
      User user = userMapper.findUserByName(userLoginDTO.getUsername());
      if (user == null){
          throw new AccountNameException(CommonErrorCodeEnum.NOT_FOUND,"用户名不存在");
      }
      if(!PasswordUtil.verifyPassword(userLoginDTO.getPassword(), user.getPassword())){
          throw new PasswordErrorException(CommonErrorCodeEnum.INVALID_REQUEST,"密码错误");
      }
      if (user.getIsLogin()==StatusConstant.DISABLE) {
          throw new AccountLockedException(CommonErrorCodeEnum.ACCOUNT_DISABLED,"用户账号被禁用");
      }
        log.info("用户登录：{}", userLoginDTO.getUsername());
        return user;
    }

    /**
     * 注册
     * @param userRegisterDTO 用户注册请求参数
     *
     */
    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        //1.判断是否已经存在该账户名
        if (userMapper.findUserByName(userRegisterDTO.getUsername()) != null) {
            throw new AccountNameException(CommonErrorCodeEnum.ALREADY_EXISTS,"用户名已存在");
        }
        // 2. 构建用户实体
        User saveUser = User.builder()
                .username(userRegisterDTO.getUsername())
                .password(PasswordUtil.hashPassword(userRegisterDTO.getPassword()))
                .role(RoleEnum.USER)
                .avatar("http")
                .createTime(LocalDateTime.now())
                .isLogin(StatusConstant.ENABLE)
                .isWord(StatusConstant.ENABLE)
                .build();
        try {
            // 3. 插入用户(自动回填id)
            userMapper.insert(saveUser);
            log.info("注册用户：{}", userRegisterDTO.getUsername());

            //4.新增空用户资料
            UserProfile initProfile = UserProfile.builder()
                    .userId(saveUser.getId())//获取Id值
                    .privacyLevel(PrivacyLevel.PUBLIC)  // 设置默认值
                    .createTime(LocalDateTime.now())
                    .build();
            userProfileMapper.addUserProfile(initProfile);
        } catch (DuplicateKeyException ex) {
            // 5. 精确捕获用户名唯一约束异常
            throw new AccountNameException(CommonErrorCodeEnum.ALREADY_EXISTS, "用户名已被占用");
        }
    }




}
