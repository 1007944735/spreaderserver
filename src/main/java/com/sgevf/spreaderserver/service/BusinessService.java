package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.entity.Business;

public interface BusinessService {
    Business queryBusinessByUserId(int userId);

    Business queryBusinessById(int id);

    int insertBusiness(Business business);

    int updateBusiness(Business business);
}
