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
import java.util.Objects;

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

        log.info("Deleting old data");

        deleteEntities();

        log.info("Inserting total Entities: " + infoList.size());

        int index = 0;
        for(CurrenciesDataDumpDto currencyResponse : infoList) {

            saveEntities(currencyResponse);

            log.info("Current Entity: " + index++);

            if(index == 1000)
            {
                break;
            }

        }

        return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
    }

    @Transactional
    public void deleteEntities()
    {
        currencyRepository.deleteAll();
    }

    @Transactional
    public void saveEntities(CurrenciesDataDumpDto currencyResponse)
    {
        CurrencyEntity currencyEntity = CurrencyEntity.builder().BlockChain("").CoinName(currencyResponse.getCurrency())
                .symbol(currencyResponse.getSymbol()).MarketCap(currencyResponse.getMarket_cap()).price(currencyResponse.getPrice())
                .CircSupply(currencyResponse.getCirculating_supply()).rank(Integer.parseInt(currencyResponse.getRank())).logo_url(currencyResponse.getLogo_url())
                .date_added(currencyResponse.getFirst_candle()).description(currencyResponse.getDescription()).build();

        currencyRepository.save(currencyEntity);

        CurrencyStatsEntity currencyStatsEntity = CurrencyStatsEntity.builder().changes_24h(
                Objects.nonNull(currencyResponse.getOneDay()) ? "" : currencyResponse.getOneDay().getPrice_change())
                .changes_7d(Objects.nonNull(currencyResponse.getWeekly()) ? "" : currencyResponse.getWeekly().getPrice_change())
                .changes_30d(Objects.nonNull(currencyResponse.getMonthly()) ? "" : currencyResponse.getMonthly().getPrice_change())
                .changes_1y(Objects.nonNull(currencyResponse.getYearly()) ? "" : currencyResponse.getYearly().getPrice_change())
                .all_time_high(Objects.nonNull(currencyResponse.getHigh()) ? "" : currencyResponse.getHigh())
                .all_time_low("0").currency_id(currencyEntity.getId()).build();

        currencyStatsRepository.save(currencyStatsEntity);
    }

}
