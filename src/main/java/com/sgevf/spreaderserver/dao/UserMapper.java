package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    @Results({
            @Result(column = "register_date", property = "registerDate")
    })
    User findUser(@Param("id") Integer id);

    @Select("select * from user where username=#{username}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "nickname", property = "nickname"),
            @Result(column = "head_portrait", property = "headPortrait"),
            @Result(column = "register_date", property = "registerDate"),
            @Result(column = "phone", property = "phone")
    })
    User findUserByUsername(@Param("username") String username);
}
