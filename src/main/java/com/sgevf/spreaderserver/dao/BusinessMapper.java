package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.Business;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BusinessMapper {
    @Select("select * from business where user_id=#{userId}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "b_name",property = "bName"),
            @Result(column = "b_time",property = "bTime"),
            @Result(column = "user_id",property = "userId"),
            @Result(column = "b_license",property = "bLicense"),
            @Result(column = "b_logo",property = "bLogo"),
            @Result(column = "b_idcard_front",property = "bIdcardFront"),
            @Result(column = "b_idcard_back",property = "bIdcardBack"),
            @Result(column = "b_address",property = "b_address"),
            @Result(column = "b_social_credit",property = "bSocialCredit"),
            @Result(column = "b_context",property = "bContext"),
            @Result(column = "b_phone",property = "bPhone")
    })
    Business queryBusinessByUserId(@Param("userId") int userId);

    @Select("select * from business where id=#{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "b_name",property = "bName"),
            @Result(column = "b_time",property = "bTime"),
            @Result(column = "user_id",property = "userId"),
            @Result(column = "b_license",property = "bLicense"),
            @Result(column = "b_logo",property = "bLogo"),
            @Result(column = "b_idcard_front",property = "bIdcardFront"),
            @Result(column = "b_idcard_back",property = "bIdcardBack"),
            @Result(column = "b_address",property = "b_address"),
            @Result(column = "b_social_credit",property = "bSocialCredit"),
            @Result(column = "b_context",property = "bContext"),
            @Result(column = "b_phone",property = "bPhone")
    })
    Business queryBusinessById(@Param("id") int id);
}
