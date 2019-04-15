package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.dto.WithdrawHistoryDto;
import com.sgevf.spreaderserver.entity.WithdrawHistory;

import java.util.List;

public interface WithdrawHistoryService {
    WithdrawHistoryDto queryWithdrawHistoryById(int id);

    int insertWithdrawHistory(WithdrawHistory history);

    int updateStatus(String order,String status,String failReason);

    WithdrawHistoryDto queryWithdrawHistoryByOrder(String order);
}
