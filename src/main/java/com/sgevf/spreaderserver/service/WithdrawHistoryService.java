package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.dto.WithdrawHistoryDto;

import java.util.List;

public interface WithdrawHistoryService {
    WithdrawHistoryDto queryWithdrawHistoryById(int id);
}
