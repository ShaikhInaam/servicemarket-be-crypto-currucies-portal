package com.crypto.portal.cryptoportal.service.impl;

import com.crypto.portal.cryptoportal.entity.CurrencyEntity;
import com.crypto.portal.cryptoportal.entity.CurrencyStatsEntity;
import com.crypto.portal.cryptoportal.repository.CurrencyRepository;
import com.crypto.portal.cryptoportal.repository.CurrencyStatsRepository;
import com.crypto.portal.cryptoportal.service.base.CurrencyStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyStatsServiceImpl  implements CurrencyStatsService {

    @Autowired
    CurrencyStatsRepository currencyStatsRepository;

    @Override
    public CurrencyStatsEntity findByCurrencyId(Integer id) {
        return currencyStatsRepository.findByCurrency_id(id);
    }
}

