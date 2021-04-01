package com.crypto.portal.cryptoportal.business.impl;

import com.crypto.portal.cryptoportal.business.base.CurrenciesExchangeDataDumpBusiness;
import com.crypto.portal.cryptoportal.dto.CurrenciesDataDumpDescDto;
import com.crypto.portal.cryptoportal.dto.CurrenciesDataDumpDto;
import com.crypto.portal.cryptoportal.dto.CurrenciesExchangeDataDumpDto;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import com.crypto.portal.cryptoportal.service.base.CurrenciesDataDumpService;
import com.crypto.portal.cryptoportal.service.base.CurrenciesExchangeDataDumpService;
import com.crypto.portal.cryptoportal.util.ConfigurationUtil;
import com.crypto.portal.cryptoportal.util.Constants;
import com.crypto.portal.cryptoportal.util.RestServiceUtility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurrenciesExchangeDataDumpBusinessImpl implements CurrenciesExchangeDataDumpBusiness {

    @Autowired
    RestServiceUtility utility;

    @Autowired
    ConfigurationUtil configurationUtil;

    @Autowired
    CurrenciesExchangeDataDumpService currenciesExchangeDataDumpService;

    private static Logger log = LoggerFactory.getLogger(CurrenciesDataDumpBusinessImpl.class);

    @Override
    public BaseResponse getCryptoCurrenciesExchanges(BaseRequest request) {

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("X-CoinAPI-Key", "3A6C0FDA-BD54-4D72-B96C-5A0152809198");
        List response = (List) utility.callGetJson("https://rest.coinapi.io/v1/exchanges", ArrayList.class, header);
        List<CurrenciesExchangeDataDumpDto> jsonResponseList = null;
        log.info("Fetching Currencies Data");
        if (response != null) {
            jsonResponseList = mapper.convertValue(response, new TypeReference<List<CurrenciesExchangeDataDumpDto>>(){});
            log.info("Fetching Currencies Data info"+jsonResponseList);
            return currenciesExchangeDataDumpService.saveCurrenciesExchangeInfo(jsonResponseList);
        }
        else{
            return  BaseResponse.builder().responseCode(Constants.FAILUARE_RESPNSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.FAILUARE_RESPNSE_CODE)).response(null).build();
        }
    }

}
