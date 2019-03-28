package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.RedPacket;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface RedPacketMapper {
    @Insert("insert into red_packet(amount,type,pub_longitude,pub_latitude,start_time,end_time,max_number,pub_address,puber_id,expand_id) " +
            "values(#{amount},#{type},#{pubLongitude},#{pubLatitude},#{startTime},#{endTime},#{maxNumber},#{pubAddress},#{puberId},#{expandId})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertRedPacket(RedPacket redPacket);

    @Select("select * from red_packet")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "type", property = "type"),
            @Result(column = "pub_time", property = "pubTime"),
            @Result(column = "pub_longitude", property = "pubLongitude"),
            @Result(column = "pub_latitude", property = "pubLatitude"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "max_number", property = "maxNumber"),
            @Result(column = "pub_address", property = "pubAddress"),
            @Result(column = "puber_id", property = "puberId"),
            @Result(column = "expand_id", property = "expandId")
    })
    List<RedPacket> queryRedPacket();

    @Select("select * from red_packet where id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "type", property = "type"),
            @Result(column = "pub_time", property = "pubTime"),
            @Result(column = "pub_longitude", property = "pubLongitude"),
            @Result(column = "pub_latitude", property = "pubLatitude"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "max_number", property = "maxNumber"),
            @Result(column = "pub_address", property = "pubAddress"),
            @Result(column = "puber_id", property = "puberId"),
            @Result(column = "expand_id", property = "expandId")
    })
    RedPacket queryRedPacketById(@Param("id") int id);


    @Select("select * from red_packet order by max_number desc")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "type", property = "type"),
            @Result(column = "pub_time", property = "pubTime"),
            @Result(column = "pub_longitude", property = "pubLongitude"),
            @Result(column = "pub_latitude", property = "pubLatitude"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "max_number", property = "maxNumber"),
            @Result(column = "pub_address", property = "pubAddress"),
            @Result(column = "puber_id", property = "puberId"),
            @Result(column = "expand_id", property = "expandId")
    })
    List<RedPacket> queryRedPacketOrderByNumber();

    @Select("select * from red_packet order by amount desc")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "type", property = "type"),
            @Result(column = "pub_time", property = "pubTime"),
            @Result(column = "pub_longitude", property = "pubLongitude"),
            @Result(column = "pub_latitude", property = "pubLatitude"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "max_number", property = "maxNumber"),
            @Result(column = "pub_address", property = "pubAddress"),
            @Result(column = "puber_id", property = "puberId"),
            @Result(column = "expand_id", property = "expandId")
    })
    List<RedPacket> queryRedPacketOrderByAmount();

    @SelectProvider(type = RedPacketFilterProvider.class, method = "queryRedPacketFilter")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "type", property = "type"),
            @Result(column = "pub_time", property = "pubTime"),
            @Result(column = "pub_longitude", property = "pubLongitude"),
            @Result(column = "pub_latitude", property = "pubLatitude"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "max_number", property = "maxNumber"),
            @Result(column = "pub_address", property = "pubAddress"),
            @Result(column = "puber_id", property = "puberId"),
            @Result(column = "expand_id", property = "expandId")
    })
    List<RedPacket> queryRedPacketFilter(@Param("type") String type, String[] numbers, String[] amounts);

    class RedPacketFilterProvider {
        public String queryRedPacketFilter(@Param("type") String type, String[] numbers, String[] amounts) {
            return new SQL() {
                {
                    SELECT("id", "amount", "type", "pub_time", "pub_longitude", "pub_latitude", "start_time");
                    SELECT("end_time", "max_number", "pub_address", "puber_id", "expand_id");
                    FROM("red_packet");
                    WHERE("type=#{type}");
                    if (numbers != null) {
                        for (String number : numbers) {
                            if ("0".equals(number)) {
                                WHERE("max_number>=0", "max_number<10");
                            } else if ("1".equals(number)) {
                                WHERE("max_number>=10", "max_number<500");
                            } else if ("2".equals(number)) {
                                WHERE("max_number>=50", "max_number<100");
                            } else if ("3".equals(number)) {
                                WHERE("max_number>=100", "max_number<500");
                            } else if ("4".equals(number)) {
                                WHERE("max_number>=500");
                            }
                        }
                    }
                    if (amounts != null) {
                        for (String amount : amounts) {
                            if ("0".equals(amount)) {
                                WHERE("amount>=0", "amount<50");
                            } else if ("1".equals(amount)) {
                                WHERE("amount>=50", "amount<200");
                            } else if ("2".equals(amount)) {
                                WHERE("amount>=200", "amount<500");
                            } else if ("3".equals(amount)) {
                                WHERE("amount>=500");
                            }
                        }
                    }
                }
            }.toString();
        }
    }
}
