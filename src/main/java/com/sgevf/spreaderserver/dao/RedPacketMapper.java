package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.RedPacket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface RedPacketMapper {
    @Insert("insert into red_packet(amount,type,pub_longitude,pub_latitude,start_time,end_time,max_number,pub_address,puber_id,expand_id) " +
            "values(#{amount},#{type},#{pubLongitude},#{pubLatitude},#{startTime},#{endTime},#{maxNumber},#{pubAddress},#{puberId},#{expandId})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertRedPacket(RedPacket redPacket);
}
