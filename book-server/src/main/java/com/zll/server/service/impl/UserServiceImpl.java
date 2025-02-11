package com.zll.server.service.impl;


import com.zll.common.constant.StatusConstant;
import com.zll.common.enumeration.CommonErrorCodeEnum;
import com.zll.common.exception.auth.AccountLockedException;
import com.zll.common.exception.auth.AccountNameException;
import com.zll.common.exception.auth.PasswordErrorException;
import com.zll.pojo.dto.UserLoginDTO;
import com.zll.pojo.dto.UserRegisterDTO;
import com.zll.pojo.em.RoleEnum;
import com.zll.pojo.entity.User;
import com.zll.server.mapper.UserMapper;
import com.zll.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(UserLoginDTO userLoginDTO) {
      User user = userMapper.findUserByName(userLoginDTO.getUsername());
      if (user == null){
          throw new AccountNameException(CommonErrorCodeEnum.NOT_FOUND,"用户名不存在");
      }
      if(!user.getPassword().equals(userLoginDTO.getPassword())){
          throw new PasswordErrorException(CommonErrorCodeEnum.INVALID_REQUEST,"密码错误");
      }
      if (user.getIsLogin()==StatusConstant.DISABLE) {
          throw new AccountLockedException(CommonErrorCodeEnum.ACCOUNT_DISABLED,"用户账号被禁用");
      }
        return user;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        //1.判断是否已经存在该账户名
        if (userMapper.findUserByName(userRegisterDTO.getUserName()) != null) {
            throw new AccountNameException(CommonErrorCodeEnum.ALREADY_EXISTS,"用户名已存在");
        }
        //2.保存用户信息
        User saveUser = User.builder()
                .username(userRegisterDTO.getUserName())
                .password(userRegisterDTO.getPassword())
                .role(RoleEnum.USER)
                .avatar("http")
                .createTime(LocalDateTime.now())
                .isLogin(StatusConstant.ENABLE)
                .isWord(StatusConstant.ENABLE).build();
        //3.添加到数据库
        userMapper.insert(saveUser);
    }


}
