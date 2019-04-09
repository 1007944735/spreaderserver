package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.RedPacketHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface RedPacketHistoryMapper {
    @Select("select * from red_packet_history where id=#{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "red_packet_id",property = "redPacketId"),
            @Result(column = "robber_id",property = "robberId"),
            @Result(column = "rob_money",property = "robMoney"),
            @Result(column = "rob_time",property = "robTime")
    })
    RedPacketHistory queryHistoryById(@Param("id") int id);

    @Select("select rob_money from red_packet_history where robber_id=#{robberId} and red_packet_id=#{redPacketId}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "red_packet_id",property = "redPacketId"),
            @Result(column = "robber_id",property = "robberId"),
            @Result(column = "rob_money",property = "robMoney"),
            @Result(column = "rob_time",property = "robTime")
    })
    RedPacketHistory queryHistoryByRobberIdAndRedPacketId(@Param("robberId") int robberId, @Param("redPacketId") int redPacketId);

    @Insert("insert into red_packet_history(red_packet_id,robber_id,rob_money) values(#{redPacketId},#{robberId},#{robMoney})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertHistory(RedPacketHistory history);
}
