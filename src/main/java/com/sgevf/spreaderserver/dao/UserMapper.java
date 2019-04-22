package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "nickname", property = "nickname"),
            @Result(column = "head_portrait", property = "headPortrait"),
            @Result(column = "register_date", property = "registerDate"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "is_business",property = "isBusiness")
    })
    User findUserById(@Param("id") Integer id);

    @Select("select * from user where username=#{username}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "nickname", property = "nickname"),
            @Result(column = "head_portrait", property = "headPortrait"),
            @Result(column = "register_date", property = "registerDate"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "is_business",property = "isBusiness")
    })
    User findUserByUsername(@Param("username") String username);

    @Insert("insert into user(username,password,nickname) values(#{username},#{password},#{nickname})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertUser(User user);

    @Update("update user set nickname=#{nickname} where id=#{id}")
    int updateUserByNickname(@Param("nickname") String nickname, @Param("id") int id);

    @Update("update user set phone=#{phone} where id=#{id}")
    int updateUserByPhone(@Param("phone") String phone, @Param("id") int id);

    @Update("update user set password=#{password} where id=#{id}")
    int updatePassword(@Param("id") int id, @Param("password") String password);
}
