package com.zll.server.mapper;

import com.zll.pojo.dto.UserProfileDTO;
import com.zll.pojo.entity.UserProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserProfileMapper {

    @Select("select * from user_profile where user_id = #{userId}")
    UserProfile getUserProfile(Long userId);


    void updateUserProfile( UserProfile userProfile);

}
