package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.dao.WithdrawHistoryMapper;
import com.sgevf.spreaderserver.dto.WithdrawHistoryDto;
import com.sgevf.spreaderserver.entity.WithdrawHistory;
import com.sgevf.spreaderserver.service.WithdrawHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class WithdrawHistoryServiceImpl implements WithdrawHistoryService {
    @Autowired
    private WithdrawHistoryMapper withdrawHistoryMapper;

    @Override
    public WithdrawHistoryDto queryWithdrawHistoryById(int id) {
        WithdrawHistory history=withdrawHistoryMapper.queryWithdrawHistoryById(id);
        WithdrawHistoryDto dto=new WithdrawHistoryDto();
        dto.setId(history.getId());
        dto.setTime(history.getTime());
        dto.setMoney(history.getMoney());
        dto.setFailReason(history.getFailReason());
        dto.setWay(history.getWay());
        dto.setStatus(history.getStatus());
        return dto;
    }

    @Override
    public int insertWithdrawHistory(WithdrawHistory history) {
        return withdrawHistoryMapper.insertWithdrawHistory(history);
    }

    @Override
    public int updateSuccessStatus(Integer id) {
        return withdrawHistoryMapper.updateSuccessStatus(id);
    }
}
