package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.dao.WithdrawHistoryMapper;
import com.sgevf.spreaderserver.dto.TransQueryDto;
import com.sgevf.spreaderserver.dto.WithdrawHistoryDto;
import com.sgevf.spreaderserver.entity.WithdrawHistory;
import com.sgevf.spreaderserver.service.AliService;
import com.sgevf.spreaderserver.service.WithdrawHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawHistoryServiceImpl implements WithdrawHistoryService {
    @Autowired
    private WithdrawHistoryMapper withdrawHistoryMapper;

    @Autowired
    private AliService aliService;

    @Override
    public WithdrawHistoryDto queryWithdrawHistoryById(int id) {
        WithdrawHistory history = withdrawHistoryMapper.queryWithdrawHistoryById(id);
        WithdrawHistoryDto dto = new WithdrawHistoryDto();
        dto.setId(history.getId());
        dto.setTime(history.getTime());
        dto.setMoney(history.getMoney());
        dto.setFailReason(history.getFailReason());
        dto.setWay(history.getWay());
        dto.setStatus(history.getStatus());
        dto.setWithdrawOrder(history.getWithdrawOrder());
        TransQueryDto tqd=aliService.queryTrans(history.getWithdrawOrder());
        dto.setAlipayOrder(tqd.getOrderId());
        return dto;
    }

    @Override
    public int insertWithdrawHistory(WithdrawHistory history) {
        return withdrawHistoryMapper.insertWithdrawHistory(history);
    }

    @Override
    public int updateStatus(String order, String status, String failReason) {
        if ("-1".equals(status)) {
            return withdrawHistoryMapper.updateFailStatus(order, status, failReason);
        } else {
            return withdrawHistoryMapper.updateSuccessStatus(order, status);
        }
    }

    @Override
    public WithdrawHistoryDto queryWithdrawHistoryByOrder(String order) {
        WithdrawHistory history = withdrawHistoryMapper.queryWithdrawHistoryByOrder(order);
        WithdrawHistoryDto dto = new WithdrawHistoryDto();
        dto.setId(history.getId());
        dto.setTime(history.getTime());
        dto.setMoney(history.getMoney());
        dto.setFailReason(history.getFailReason());
        dto.setWay(history.getWay());
        dto.setStatus(history.getStatus());
        dto.setWithdrawOrder(history.getWithdrawOrder());
        return dto;
    }
}
