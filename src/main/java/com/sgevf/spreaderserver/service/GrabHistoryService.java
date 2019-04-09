package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.dto.AccountRedPacketDetailsDto;

public interface GrabHistoryService {
    AccountRedPacketDetailsDto queryRedPacketHistoryById(int robberId);
}
