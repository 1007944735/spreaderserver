package com.sgevf.spreaderserver.dao;

import com.sgevf.spreaderserver.dto.AccountDetailListDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class WalletAccountMapperTest {
    @Autowired
    private WalletAccountMapper mapper;
    @Test
    public void queryWalletAccountList() {
        List<AccountDetailListDto> list=mapper.queryWalletAccountList(21);
        assertEquals(5,list.size());
    }
}