package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.RedPacket;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface RedPacketMapper {
    @Insert("insert into red_packet(amount,type,pub_longitude,pub_latitude,start_time,end_time,max_number,pub_address,puber_id,expand_id,order_id) " +
            "values(#{amount},#{type},#{pubLongitude},#{pubLatitude},#{startTime},#{endTime},#{maxNumber},#{pubAddress},#{puberId},#{expandId},#{orderId})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertRedPacket(RedPacket redPacket);

//    @Select("select * from red_packet")
//    @Results({
//            @Result(column = "id", property = "id"),
//            @Result(column = "amount", property = "amount"),
//            @Result(column = "type", property = "type"),
//            @Result(column = "pub_time", property = "pubTime"),
//            @Result(column = "pub_longitude", property = "pubLongitude"),
//            @Result(column = "pub_latitude", property = "pubLatitude"),
//            @Result(column = "start_time", property = "startTime"),
//            @Result(column = "end_time", property = "endTime"),
//            @Result(column = "max_number", property = "maxNumber"),
//            @Result(column = "pub_address", property = "pubAddress"),
//            @Result(column = "puber_id", property = "puberId"),
//            @Result(column = "expand_id", property = "expandId")
//    })
//    List<RedPacket> queryRedPacket();

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
            @Result(column = "expand_id", property = "expandId"),
            @Result(column = "order_id", property = "orderId")
    })
    RedPacket queryRedPacketById(@Param("id") int id);
//
//
//    @Select("select * from red_packet order by max_number desc")
//    @Results({
//            @Result(column = "id", property = "id"),
//            @Result(column = "amount", property = "amount"),
//            @Result(column = "type", property = "type"),
//            @Result(column = "pub_time", property = "pubTime"),
//            @Result(column = "pub_longitude", property = "pubLongitude"),
//            @Result(column = "pub_latitude", property = "pubLatitude"),
//            @Result(column = "start_time", property = "startTime"),
//            @Result(column = "end_time", property = "endTime"),
//            @Result(column = "max_number", property = "maxNumber"),
//            @Result(column = "pub_address", property = "pubAddress"),
//            @Result(column = "puber_id", property = "puberId"),
//            @Result(column = "expand_id", property = "expandId")
//    })
//    List<RedPacket> queryRedPacketOrderByNumber();
//
//    @Select("select * from red_packet order by amount desc")
//    @Results({
//            @Result(column = "id", property = "id"),
//            @Result(column = "amount", property = "amount"),
//            @Result(column = "type", property = "type"),
//            @Result(column = "pub_time", property = "pubTime"),
//            @Result(column = "pub_longitude", property = "pubLongitude"),
//            @Result(column = "pub_latitude", property = "pubLatitude"),
//            @Result(column = "start_time", property = "startTime"),
//            @Result(column = "end_time", property = "endTime"),
//            @Result(column = "max_number", property = "maxNumber"),
//            @Result(column = "pub_address", property = "pubAddress"),
//            @Result(column = "puber_id", property = "puberId"),
//            @Result(column = "expand_id", property = "expandId")
//    })
//    List<RedPacket> queryRedPacketOrderByAmount();

    @SelectProvider(type = RedPacketFilterProvider.class, method = "queryRedPacketSearch")
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
            @Result(column = "expand_id", property = "expandId"),
            @Result(column = "order_id", property = "orderId")
    })
    List<RedPacket> queryRedPacketSearch(String orderType,String redPacketType, String[] numbers, String[] amounts);

    class RedPacketFilterProvider {
        public String queryRedPacketSearch(String orderType, String redPacketType, String[] numbers, String[] amounts) {
            return new SQL() {
                {
                    SELECT("id", "amount", "type", "pub_time", "pub_longitude", "pub_latitude", "start_time");
                    SELECT("end_time", "max_number", "pub_address", "puber_id", "expand_id");
                    FROM("red_packet");
                    if (redPacketType!=null&&!redPacketType.isEmpty()) {
                        WHERE("type='"+redPacketType+"'");
                    }
                    if (numbers != null) {
                        StringBuffer sb=new StringBuffer("(");
                        for(int i=0;i<numbers.length;i++){
                            if ("0".equals(numbers[i])) {
                                sb.append("(max_number>=0 AND max_number<10)");
                            } else if ("1".equals(numbers[i])) {
                                sb.append("(max_number>=10 AND max_number<50)");
                            } else if ("2".equals(numbers[i])) {
                                sb.append("(max_number>=50 AND max_number<100)");
                            } else if ("3".equals(numbers[i])) {
                                sb.append("(max_number>=100 AND max_number<500)");
                            } else if ("4".equals(numbers[i])) {
                                sb.append("max_number>=500");
                            }
                            if(i!=numbers.length-1){
                                sb.append(" OR ");
                            }
                        }
                        sb.append(")");
                        WHERE(sb.toString());
                    }
                    if (amounts != null) {
                        StringBuffer sb=new StringBuffer("(");
                        for(int i=0;i<amounts.length;i++){
                            if ("0".equals(amounts[i])) {
                                sb.append("amount>=0 AND amount<50");
                            } else if ("1".equals(amounts[i])) {
                                sb.append("amount>=50 AND amount<200");
                            } else if ("2".equals(amounts[i])) {
                                sb.append("amount>=200 AND amount<500");
                            } else if ("3".equals(amounts[i])) {
                                sb.append("amount>=500");
                            }
                            if(i!=amounts.length-1){
                                sb.append(" OR ");
                            }
                        }
                        sb.append(")");
                        WHERE(sb.toString());
                    }
                    if("1".equals(orderType)){
                        ORDER_BY("max_number desc");
                    }else if("2".equals(orderType)){
                        ORDER_BY("amount desc");
                    }
                }
            }.toString();
        }
    }
}
