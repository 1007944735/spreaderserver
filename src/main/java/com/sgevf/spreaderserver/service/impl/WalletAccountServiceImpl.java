package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.dao.WalletAccountMapper;
import com.sgevf.spreaderserver.dto.AccountDetailListDto;
import com.sgevf.spreaderserver.service.WalletAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WalletAccountServiceImpl implements WalletAccountService {
    @Autowired
    private WalletAccountMapper walletAccountMapper;
    @Override
    public List<AccountDetailListDto> queryWalletAccountList(int id) {
        return walletAccountMapper.queryWalletAccountList(id);
    }
}
