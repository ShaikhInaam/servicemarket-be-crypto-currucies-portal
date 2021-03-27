package com.crypto.portal.cryptoportal.service.impl;

import com.crypto.portal.cryptoportal.dto.CurrenciesDataDumpDto;
import com.crypto.portal.cryptoportal.entity.CurrencyEntity;
import com.crypto.portal.cryptoportal.entity.CurrencyStatsEntity;
import com.crypto.portal.cryptoportal.repository.CurrencyRepository;
import com.crypto.portal.cryptoportal.repository.CurrencyStatsRepository;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import com.crypto.portal.cryptoportal.service.base.CurrenciesDataDumpService;
import com.crypto.portal.cryptoportal.util.ConfigurationUtil;
import com.crypto.portal.cryptoportal.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CurrenciesDataDumpServiceImpl implements CurrenciesDataDumpService {

    @Autowired
    ConfigurationUtil configurationUtil;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    CurrencyStatsRepository currencyStatsRepository;

    private static Logger log = LoggerFactory.getLogger(CurrenciesDataDumpServiceImpl.class);


    @Override
    public BaseResponse saveCurrenciesInfo(List<CurrenciesDataDumpDto> infoList) {

        log.info("Total Entities: " + infoList.size());

        int index = 0;
        for(CurrenciesDataDumpDto currencyResponse : infoList) {

            saveEntities(currencyResponse);
            log.info("Current Entity: " + index++);

            if(index == 2500)
                break;

        }

        return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
    }

    @Transactional
    public void saveEntities(CurrenciesDataDumpDto currencyResponse)
    {
        CurrencyEntity currencyEntity = CurrencyEntity.builder()
                .BlockChain("")
                .CoinName(currencyResponse.getCurrency())
                .symbol(currencyResponse.getSymbol())
                .MarketCap(currencyResponse.getMarket_cap()).price(currencyResponse.getPrice())
                .CircSupply(currencyResponse.getCirculating_supply())
                .rank(Integer.parseInt(currencyResponse.getRank()))
                .logo_url(currencyResponse.getLogo_url())
                .date_added(currencyResponse.getFirst_candle()).description(currencyResponse.getDescription()).build();

        currencyRepository.save(currencyEntity);

        CurrencyStatsEntity currencyStatsEntity = CurrencyStatsEntity.builder()
                .changes_24h(currencyResponse.getOneDay() == null ? "" : currencyResponse.getOneDay().getPrice_change())
                .changes_7d(currencyResponse.getWeekly()  == null ? "" : currencyResponse.getWeekly().getPrice_change())
                .changes_30d(currencyResponse.getMonthly() == null ? "" : currencyResponse.getMonthly().getPrice_change())
                .changes_1y(currencyResponse.getYearly() == null ? "" : currencyResponse.getYearly().getPrice_change())
                .all_time_high(currencyResponse.getHigh() == null ? "" : currencyResponse.getHigh())
                .all_time_low("0")
                .currency_id(currencyEntity.getId()).build();

        currencyStatsRepository.save(currencyStatsEntity);
    }

}
