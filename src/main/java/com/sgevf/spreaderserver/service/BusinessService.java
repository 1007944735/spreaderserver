package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.dto.ManageBusinessDto;
import com.sgevf.spreaderserver.entity.Business;

import java.util.List;

public interface BusinessService {
    Business queryBusinessByUserId(int userId);

    Business queryBusinessById(int id);

    int insertBusiness(Business business);

    int updateBusiness(Business business);

    List<ManageBusinessDto>  queryBusiness();
}
