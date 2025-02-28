package com.zll.server.mapper;

import com.zll.pojo.entity.Follow;
import com.zll.pojo.vo.UserVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 关注接口
 */
@Mapper
public interface FollowMapper {

    @Insert("insert into follow(follower_id, followed_id) values(#{followerId}, #{followedId})")
    void insert(Follow follow);

    @Delete("delete from follow where follower_id = #{followerId} and followed_id = #{followedId}")
    void delete(Follow follow);

    @Select("select u.id,u.username,u.avatar from user u, follow f where f.followed_id = #{userId} and f.follower_id = u.id")
    List<UserVO> getFollowers(Long userId);

    @Select("select u.id,u.username,u.avatar from user u, follow f where f.follower_id = #{userId} and f.followed_id = u.id")
    List<UserVO> getFollowing(Long userId);

}
