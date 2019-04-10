package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.entity.Orders;

public interface OrdersService {
    int insertOrder(Orders orders);

    int updateOrderStatus(int id);
}
