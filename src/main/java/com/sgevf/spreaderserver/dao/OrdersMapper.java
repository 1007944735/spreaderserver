package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.Orders;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrdersMapper {

    @Insert("insert into orders(order_no,status,money) values(#{orderNo},'0',#{money})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertOrder(Orders orders);

    @Update("update orders set status=#{status} where order_no=#{orderNo}")
    int updateOrderStatus(@Param("orderNo") String orderNo,@Param("status") String status);

    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "order_no", property = "orderNo"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "status", property = "status"),
            @Result(column = "money", property = "money"),
    })
    Orders queryOrderById(@Param("id") Integer id);
}
