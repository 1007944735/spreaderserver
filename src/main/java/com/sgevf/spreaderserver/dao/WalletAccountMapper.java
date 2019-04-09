package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.dto.AccountDetailListDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WalletAccountMapper {
    @Select("select * from (select \"0\" as type,w.id,w.money,w.time,\"\" as red_packet_id from withdraw_history w where taker_id=#{id} union select \"1\" as type,r.id,r.rob_money,r.rob_time,r.red_packet_id from red_packet_history r where robber_id=#{id}) c order by time desc")
    @Results({
            @Result(column = "type",property = "type"),
            @Result(column = "id",property = "id"),
            @Result(column = "money",property = "money"),
            @Result(column = "time",property = "time"),
            @Result(column = "red_packet_id",property = "redPacketId")
    })
    List<AccountDetailListDto> queryWalletAccountList(@Param("id") int id);
}
