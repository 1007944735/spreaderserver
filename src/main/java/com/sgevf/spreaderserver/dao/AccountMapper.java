package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.Account;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AccountMapper {
    @Select("select * from account where id=#{id}")
    Account selectAccountById(@Param("id") int id);

    @Update("update account set balance=balance+#{money} where user_id=#{userId}")
    int updateBalance(@Param("money") double money, @Param("userId") int userId);
}
