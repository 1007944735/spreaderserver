package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.dto.AccountDetailListDto;

import java.util.List;

public interface WalletAccountService {
    List<AccountDetailListDto> queryWalletAccountList(int id);
}
