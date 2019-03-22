package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    User findUser(@Param("id") Integer id);
    @Insert("insert into user(name,age) values(#{name},#{age})")
    Integer insertUser(@Param("name") String name, @Param("age") String age);
}
