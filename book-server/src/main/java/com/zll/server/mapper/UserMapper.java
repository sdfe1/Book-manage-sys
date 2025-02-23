package com.zll.server.mapper;

import com.zll.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

   @Select("select * from user where username = #{username}")
   User findUserByName(String username);



   @Insert("insert into user (username,password,role,avatar,is_login,is_word,create_time)" +
           "values" +
           "(#{username},#{password},#{role},#{avatar},#{isLogin},#{isWord},#{createTime} )")
   @Options(useGeneratedKeys = true, keyProperty = "id")  //回填机制，便于注册时获取userId
   void insert(User user);

   @Select("select * from user where id = #{userId}")
   User getUserById(Long userId);

}
