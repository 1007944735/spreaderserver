package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.entity.RedPacketHistory;

public interface GrabHistoryService {
    RedPacketHistory queryByRobId(int robid,int redPacketId);
}
