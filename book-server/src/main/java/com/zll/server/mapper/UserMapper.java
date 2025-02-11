package com.zll.server.mapper;

import com.zll.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

   @Select("select * from user where username = #{username}")
   User findUserByName(String username);

   @Insert("insert into user (username,password,role,avatar,is_login,is_word,create_time)" +
           "values" +
           "(#{username},#{password},#{role},#{avatar},#{isLogin},#{isWord},#{createTime} )")
   void insert(User user);
}
