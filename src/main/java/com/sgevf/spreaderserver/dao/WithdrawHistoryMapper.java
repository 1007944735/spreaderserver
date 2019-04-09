package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.WithdrawHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WithdrawHistoryMapper {
    @Select("select * from withdraw_history where id=#{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "taker_id",property = "takerId"),
            @Result(column = "money",property = "money"),
            @Result(column = "time",property = "time"),
            @Result(column = "way",property = "way"),
            @Result(column = "status",property = "status"),
            @Result(column = "fail_reason",property = "failReason"),
    })
    WithdrawHistory queryWithdrawHistoryById(@Param("id") int id);
}
