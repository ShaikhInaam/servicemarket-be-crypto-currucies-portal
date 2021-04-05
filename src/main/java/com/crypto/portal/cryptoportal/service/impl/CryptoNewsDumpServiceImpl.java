package com.crypto.portal.cryptoportal.service.impl;

import com.crypto.portal.cryptoportal.dto.CryptoNewsDto;
import com.crypto.portal.cryptoportal.entity.CurrencyNewsEntity;
import com.crypto.portal.cryptoportal.repository.CurrencyNewsRepository;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import com.crypto.portal.cryptoportal.service.base.CryptoNewsDumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoNewsDumpServiceImpl implements CryptoNewsDumpService {

    @Autowired
    CurrencyNewsRepository currencyNewsRepository;



    @Override
    public void saveNews(CurrencyNewsEntity currencyNewsEntity) {
        currencyNewsRepository.save(currencyNewsEntity);
    }
}
