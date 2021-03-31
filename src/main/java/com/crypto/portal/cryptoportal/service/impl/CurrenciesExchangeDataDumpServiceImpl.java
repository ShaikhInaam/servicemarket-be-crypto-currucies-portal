package com.crypto.portal.cryptoportal.service.impl;

import com.crypto.portal.cryptoportal.dto.CurrenciesDataDumpDto;
import com.crypto.portal.cryptoportal.dto.CurrenciesExchangeDataDumpDto;
import com.crypto.portal.cryptoportal.entity.CurrencyExchangesEntity;
import com.crypto.portal.cryptoportal.entity.CurrencyStatsEntity;
import com.crypto.portal.cryptoportal.repository.CurrenciesExchangeDataDumpRepository;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import com.crypto.portal.cryptoportal.service.base.CurrenciesExchangeDataDumpService;
import com.crypto.portal.cryptoportal.util.ConfigurationUtil;
import com.crypto.portal.cryptoportal.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

public class CurrenciesExchangeDataDumpServiceImpl implements CurrenciesExchangeDataDumpService {

    @Autowired
    ConfigurationUtil configurationUtil;

    @Autowired
    CurrenciesExchangeDataDumpRepository currencyExchangeRepository;

    //@Autowired
    //CurrencyStatsRepository currencyStatsRepository;

    private static Logger log = LoggerFactory.getLogger(CurrenciesDataDumpServiceImpl.class);

    @Override
    public BaseResponse saveCurrenciesExchangeInfo(List<CurrenciesExchangeDataDumpDto> infoList) {

        log.info("Total Entities: " + infoList.size());
        int index = 0;
        log.info("Updating Servicesss RS  ");

        for (CurrenciesExchangeDataDumpDto currencyExchangeResponse : infoList) {
            saveEntities(currencyExchangeResponse);
            log.info("Current Entity: " + index++);
            if (index == 2500)
                break;

        }
        return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
    }

    @Transactional
    public void saveEntities(CurrenciesExchangeDataDumpDto currencyExchangeResponse){
        CurrencyExchangesEntity currencyExchangesEntity = CurrencyExchangesEntity.builder()
                .exchange_name(currencyExchangeResponse.getExchange_name())
                .exchange_url(currencyExchangeResponse.getExchange_url())
                .build();

        currencyExchangeRepository.save(currencyExchangesEntity);
    }
}