package com.crypto.portal.cryptoportal.business.impl;

import com.crypto.portal.cryptoportal.business.base.CurrenciesDataDumpBusiness;
import com.crypto.portal.cryptoportal.dto.CurrenciesDataDumpDto;
import com.crypto.portal.cryptoportal.dto.CurrenciesDataDumpDescDto;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import com.crypto.portal.cryptoportal.service.base.CurrenciesDataDumpService;
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
public class CurrenciesDataDumpBusinessImpl implements CurrenciesDataDumpBusiness {

    @Autowired
    RestServiceUtility utility;

    @Autowired
    ConfigurationUtil configurationUtil;

    @Autowired
    CurrenciesDataDumpService currenciesDataDumpService;

    private static Logger log = LoggerFactory.getLogger(CurrenciesDataDumpBusinessImpl.class);

    @Override
    public BaseResponse getCryptoCurrencies(BaseRequest request) {

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");

        List response = (List) utility.callGetJson("https://api.nomics.com/v1/currencies/ticker?key=d0a7ba4aa83fa093b777e3085fa51a99", ArrayList.class, header);
        List<CurrenciesDataDumpDto> jsonResponseList = null;

        log.info("Fetching Currencies Data");

        if (response != null) {
            jsonResponseList = mapper.convertValue(response, new TypeReference<List<CurrenciesDataDumpDto>>() {});

            log.info("Fetching Currencies Data info");

            List<CurrenciesDataDumpDescDto> currencyInfo = getCryptoInfo(request);


            log.info("Setting Description");

            for(CurrenciesDataDumpDto currencyResponse : jsonResponseList) {

                for(CurrenciesDataDumpDescDto infoResponse : currencyInfo) {
                    if(infoResponse.getId().equals(currencyResponse.getId()))
                    {
                        currencyResponse.setDescription(infoResponse.getDescription());
                    }
                }

            }


            return currenciesDataDumpService.saveCurrenciesInfo(jsonResponseList);

        }
        else{
            return  BaseResponse.builder().responseCode(Constants.FAILUARE_RESPNSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.FAILUARE_RESPNSE_CODE)).response(null).build();
        }
    }


    @Override
    public List<CurrenciesDataDumpDescDto> getCryptoInfo(BaseRequest request) {

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");

        List response = (List) utility.callGetJson("https://api.nomics.com/v1/currencies?key=d0a7ba4aa83fa093b777e3085fa51a99", ArrayList.class, header);
        List<CurrenciesDataDumpDescDto> jsonResponseList = null;

        if (response != null) {
            jsonResponseList = mapper.convertValue(response, new TypeReference<List<CurrenciesDataDumpDescDto>>() {});

            return jsonResponseList;

        }
        else{
            return  null;
        }
    }
}
