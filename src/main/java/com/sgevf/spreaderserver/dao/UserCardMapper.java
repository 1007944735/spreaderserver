package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.UserCard;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserCardMapper {
    @Insert("insert into user_card(card_id,user_id,get_time,end_time) values(#{cardId},#{userId},#{getTime},#{endTime},#{redPacketId})")
    int insertUserCards(@Param("cardId") int cardId, @Param("userId") int userId, @Param("getTime") String getTime, @Param("endTime") String endTime, @Param("redPacketId") int redPacketId);

    @Select("select * from user_card where user_id=#{userId}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "card_id", property = "cardId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "is_use", property = "isUse"),
            @Result(column = "get_time", property = "getTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "red_packet_id", property = "redPacketId")
    })
    List<UserCard> queryUserCardByUserId(@Param("userId") int userId);
}
