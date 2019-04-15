package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.WithdrawHistory;
import com.sgevf.spreaderserver.service.WithdrawHistoryService;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WithdrawHistoryMapper {
    @Select("select * from withdraw_history where id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "taker_id", property = "takerId"),
            @Result(column = "money", property = "money"),
            @Result(column = "time", property = "time"),
            @Result(column = "way", property = "way"),
            @Result(column = "status", property = "status"),
            @Result(column = "fail_reason", property = "failReason"),
            @Result(column = "withdraw_order", property = "withdrawOrder"),
    })
    WithdrawHistory queryWithdrawHistoryById(@Param("id") int id);

    @Insert("insert into withdraw_history(taker_id,money,way,withdraw_order) values(#{takerId},#{money},'1',#{withdrawOrder})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertWithdrawHistory(WithdrawHistory withdrawHistory);

    @Update("update withdraw_history set status=#{status} where withdraw_order=#{order}")
    int updateSuccessStatus(@Param("order") String order, @Param("status") String status);

    @Update("update withdraw_history set status=#{status},fail_reason=#{failReason} where withdraw_order=#{order}")
    int updateFailStatus(@Param("order") String order, @Param("status") String status, @Param("failReason") String failReason);


    @Select("select * from withdraw_history where withdraw_order=#{order}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "taker_id", property = "takerId"),
            @Result(column = "money", property = "money"),
            @Result(column = "time", property = "time"),
            @Result(column = "way", property = "way"),
            @Result(column = "status", property = "status"),
            @Result(column = "fail_reason", property = "failReason"),
            @Result(column = "withdraw_order", property = "withdrawOrder"),
    })
    WithdrawHistory queryWithdrawHistoryByOrder(@Param("order") String order);
}
