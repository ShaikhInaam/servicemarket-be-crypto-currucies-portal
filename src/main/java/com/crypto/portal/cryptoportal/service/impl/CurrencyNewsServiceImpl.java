package com.crypto.portal.cryptoportal.service.impl;

import com.crypto.portal.cryptoportal.entity.CurrencyNewsEntity;
import com.crypto.portal.cryptoportal.repository.CurrencyNewsRepository;
import com.crypto.portal.cryptoportal.service.base.CurrencyNewsService;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class CurrencyNewsServiceImpl implements CurrencyNewsService {

    @Autowired
    CurrencyNewsRepository currencyNewsRepository;


    @Override
    public List<CurrencyNewsEntity> findNewsByDate(LocalDate news_date) {

        List<CurrencyNewsEntity> currencyNewsEntities = currencyNewsRepository.findNewsByDate(news_date);

        if(currencyNewsEntities != null){
            return currencyNewsEntities;
        }
        return null;

    }
}
