package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.dao.OrdersMapper;
import com.sgevf.spreaderserver.entity.Orders;
import com.sgevf.spreaderserver.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public int insertOrder(Orders orders) {
        return ordersMapper.insertOrder(orders);
    }

    @Override
    public int updateOrderStatus(int id) {
        return ordersMapper.updateOrderStatus(id);
    }
}
