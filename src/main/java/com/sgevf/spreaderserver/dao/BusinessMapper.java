package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.Business;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BusinessMapper {
    @Select("select * from business where user_id=#{userId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "b_name", property = "bName"),
            @Result(column = "b_time", property = "bTime"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "b_license", property = "bLicense"),
            @Result(column = "b_logo", property = "bLogo"),
            @Result(column = "b_idcard_front", property = "bIdcardFront"),
            @Result(column = "b_idcard_back", property = "bIdcardBack"),
            @Result(column = "b_address", property = "bAddress"),
            @Result(column = "b_social_credit", property = "bSocialCredit"),
            @Result(column = "b_content", property = "bContent"),
            @Result(column = "b_phone", property = "bPhone")
    })
    Business queryBusinessByUserId(@Param("userId") int userId);

    @Select("select * from business where id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "b_name", property = "bName"),
            @Result(column = "b_time", property = "bTime"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "b_license", property = "bLicense"),
            @Result(column = "b_logo", property = "bLogo"),
            @Result(column = "b_idcard_front", property = "bIdcardFront"),
            @Result(column = "b_idcard_back", property = "bIdcardBack"),
            @Result(column = "b_address", property = "bAddress"),
            @Result(column = "b_social_credit", property = "bSocialCredit"),
            @Result(column = "b_content", property = "bContent"),
            @Result(column = "b_phone", property = "bPhone")
    })
    Business queryBusinessById(@Param("id") int id);

    @Insert("insert into business(b_name,user_id,b_license,b_logo,b_idcard_front,b_idcard_back,b_address,b_social_credit,b_phone,b_content) values(#{bName},#{userId},#{bLicense},#{bLogo},#{bIdcardFront},#{bIdcardBack},#{bAddress},#{bSocialCredit},#{bPhone},#{bContent})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertBusiness(Business business);

    @Update({"update business set b_name=#{bName},b_license=#{bLicense},b_logo=#{bLogo},b_idcard_front=#{bIdcardFront},b_idcard_back=#{bIdcardBack},b_address=#{bAddress},b_social_credit=#{bSocialCredit},b_content=#{bContent},b_phone=#{bPhone} where id=#{id}"})
    int updateBusiness(Business business);
}
