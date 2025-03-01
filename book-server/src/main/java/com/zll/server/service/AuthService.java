package com.zll.server.service;

import com.zll.pojo.dto.UserLoginDTO;
import com.zll.pojo.dto.UserRegisterDTO;
import com.zll.pojo.entity.User;


public interface AuthService {


    User login(UserLoginDTO userLoginDTO);


    void register(UserRegisterDTO userRegisterDTO);


}
