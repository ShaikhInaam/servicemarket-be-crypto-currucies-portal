package com.crypto.portal.cryptoportal.service.impl;

import com.crypto.portal.cryptoportal.entity.CurrencyEntity;
import com.crypto.portal.cryptoportal.repository.CurrencyRepository;
import com.crypto.portal.cryptoportal.service.base.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {


    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public List<CurrencyEntity> getAllCryptoDetails() {
        return currencyRepository.findAll();
    }


}
