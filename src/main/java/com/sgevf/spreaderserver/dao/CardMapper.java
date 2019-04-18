package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.Card;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CardMapper {
    @Select("select * from card where business_id=#{businessId} and status='0'")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "discount_rule", property = "discountRule"),
            @Result(column = "use_rule", property = "useRule"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "effective_time", property = "effectiveTime"),
            @Result(column = "business_id", property = "businessId"),
            @Result(column = "status", property = "status")
    })
    List<Card> queryBusinessCard(@Param("businessId") int businessId);

    @Update("update card set status='-1' where id=#{id}")
    int deleteCard(@Param("id") int id);

    @Insert("insert into card(discount_rule,use_rule,start_time,effective_time,business_id) values(#{discountRule},#{useRule},#{startTime},#{effectiveTime},#{businessId})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertCard(Card card);
}
