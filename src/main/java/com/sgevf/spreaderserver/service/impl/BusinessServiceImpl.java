package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.dao.BusinessMapper;
import com.sgevf.spreaderserver.entity.Business;
import com.sgevf.spreaderserver.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public Business queryBusinessByUserId(int userId) {
        return businessMapper.queryBusinessByUserId(userId);
    }

    @Override
    public Business queryBusinessById(int id) {
        return businessMapper.queryBusinessById(id);
    }
}
