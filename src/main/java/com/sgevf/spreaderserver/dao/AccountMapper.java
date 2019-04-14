package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.Account;
import org.apache.ibatis.annotations.*;

import javax.annotation.security.PermitAll;

@Mapper
public interface AccountMapper {
    @Select("select * from account where id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "balance", property = "balance"),
            @Result(column = "alipay_account", property = "alipayAccount"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "alipay_head", property = "alipayHead"),
            @Result(column = "alipay_name", property = "alipayName")
    })
    Account selectAccountById(@Param("id") int id);

    @Insert("insert into account(user_id) values(#{user_id})")
    int insertAccount(@Param("user_id") int userId);

    @Update("update account set balance=balance+#{money} where user_id=#{userId}")
    int increaseBalance(@Param("money") double money, @Param("userId") int userId);

    @Update("update account set balance=balance-#{money} where user_id=#{userId}")
    int reduceBalance(@Param("money") double money, @Param("userId") int userId);

    @Select("select * from account where user_id=#{user_id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "balance", property = "balance"),
            @Result(column = "alipay_account", property = "alipayAccount"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "alipay_head", property = "alipayHead"),
            @Result(column = "alipay_name", property = "alipayName")
    })
    Account selectAccountByUserId(@Param("user_id") int userId);

    @Update("update account set alipay_account=#{alipayAccount},alipay_head=#{alipayHead},alipay_name=#{alipayName} where user_id=#{userId}")
    int updateAlipayAccount(@Param("alipayAccount") String alipayAccount, @Param("alipayHead") String alipayHead, @Param("alipayName") String alipayName, @Param("userId") int userId);
}
