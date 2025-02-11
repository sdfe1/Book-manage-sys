package com.zll.server.mapper;

import com.zll.pojo.dto.CategoryDTO;
import com.zll.pojo.dto.CategoryPageQueryDTO;
import com.zll.pojo.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.github.pagehelper.Page;

@Mapper
public interface CategoryMapper {

    @Insert("insert into category(name,create_time,create_user,update_time,update_user)" +
            " values" +
            "(#{name},#{createTime},#{createUser},#{updateTime},#{updateUser})")
     void insert(Category category);

    @Select("select * from category where id = #{id}")
    Category selectById(int id);

    @Delete("delete from category where id = #{id}")
    void delete(int id);
    @Select("select * from category where name = #{name}")
    Category selectByName(String name);

    void update(Category category);


    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);
}
