package com.zll.server.service;

import com.zll.common.result.Result;
import com.zll.pojo.dto.UserLoginDTO;
import com.zll.pojo.dto.UserRegisterDTO;
import com.zll.pojo.entity.User;

public interface UserService {


    User login(UserLoginDTO userLoginDTO);


    void register(UserRegisterDTO userRegisterDTO);


}
