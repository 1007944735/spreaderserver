package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.entity.Orders;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrdersMapper {

    @Insert("insert into orders(order_no,status,money) values(#{orderNo},'0',#{money})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertOrder(Orders orders);

    @Update("update orders set status='1' where id=#{id}")
    int updateOrderStatus(@Param("id") int id);
}
