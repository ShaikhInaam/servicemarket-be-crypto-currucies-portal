package com.crypto.portal.cryptoportal.service.impl;

import com.crypto.portal.cryptoportal.dto.CurrenciesDataDumpDto;
import com.crypto.portal.cryptoportal.dto.CurrencyWalletsDto;
import com.crypto.portal.cryptoportal.entity.CurrencyWalletsEntity;
import com.crypto.portal.cryptoportal.repository.CurrencyWalletsRepository;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import com.crypto.portal.cryptoportal.service.base.CurrencyWalletsDumpService;
import com.crypto.portal.cryptoportal.util.ConfigurationUtil;
import com.crypto.portal.cryptoportal.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CurrencyWalletsDumpServiceImpl implements CurrencyWalletsDumpService {

    @Autowired
    ConfigurationUtil configurationUtil;

    @Autowired
    CurrencyWalletsRepository currencyWalletsRepository;


    private static Logger log = LoggerFactory.getLogger(CurrencyWalletsDumpServiceImpl.class);

    @Override
    public BaseResponse saveCurrenciesWallets(List<CurrencyWalletsDto> infoList) {

        log.info("Deleting old data");

        deleteEntities();

        log.info("Inserting total Entities: " + infoList.size());

        int index = 0;
        for(CurrencyWalletsDto currencyResponse : infoList) {

            saveEntities(currencyResponse);
            log.info("Current Entity: " + index++);

        }

        return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();

    }

    @Transactional
    public void deleteEntities()
    {
        currencyWalletsRepository.deleteAll();
    }

    @Transactional
    public void saveEntities(CurrencyWalletsDto currencyWalletsDto)
    {
        CurrencyWalletsEntity currencyWalletsEntity = CurrencyWalletsEntity.builder()
                .wallet_name(currencyWalletsDto.getName())
                .wallet_url(currencyWalletsDto.getUrl()).build();

        currencyWalletsRepository.save(currencyWalletsEntity);
    }

}
