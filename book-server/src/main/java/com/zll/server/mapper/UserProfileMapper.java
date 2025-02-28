package com.zll.server.mapper;

import com.zll.pojo.entity.UserProfile;
import org.apache.ibatis.annotations.*;

/**
 * 个人资料接口
 */
@Mapper
public interface UserProfileMapper {

    @Select("select * from user_profile where user_id = #{userId}")
    UserProfile getUserProfile(Long userId);


    void updateUserProfile( UserProfile userProfile);

    @Insert("insert into user_profile (user_id, gender, location, bio, interests, privacy_level, create_time, update_time) " +
            "values (#{userId}, #{gender}, #{location}, #{bio}, #{interests}, #{privacyLevel}, #{createTime}, #{updateTime})")
    void addUserProfile(UserProfile userProfile);

}
