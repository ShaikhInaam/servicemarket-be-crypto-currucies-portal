package com.crypto.portal.cryptoportal.business.impl;


import com.crypto.portal.cryptoportal.business.base.CryptoNewsDataDumpBusiness;
import com.crypto.portal.cryptoportal.dto.CryptoNewsDto;
import com.crypto.portal.cryptoportal.dto.CurrenciesDataDumpDto;
import com.crypto.portal.cryptoportal.entity.CurrencyEntity;
import com.crypto.portal.cryptoportal.entity.CurrencyNewsEntity;
import com.crypto.portal.cryptoportal.repository.CurrencyNewsRepository;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import com.crypto.portal.cryptoportal.service.base.CryptoNewsDumpService;
import com.crypto.portal.cryptoportal.service.base.CurrenciesDataDumpService;
import com.crypto.portal.cryptoportal.util.ConfigurationUtil;
import com.crypto.portal.cryptoportal.util.Constants;
import com.crypto.portal.cryptoportal.util.RestServiceUtility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class CryptoNewsDataDumpBusinessImpl implements CryptoNewsDataDumpBusiness {


    @Autowired
    RestServiceUtility utility;

    @Autowired
    ConfigurationUtil configurationUtil;

    @Autowired
    CryptoNewsDumpService currenciesDataDumpService;

    @Scheduled(cron = "0 7 * * *")
    public void saveCryptoNews() {
        System.out.println("Starting");
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");

       LinkedHashMap response = (LinkedHashMap) utility.callGetJson("https://cryptopanic.com/api/v1/posts/?auth_token=1b9fdbdbb0962f1b7d9f7d4a6028272b72d0be7f", ArrayList.class, header);
        List<CryptoNewsDto> jsonResponseList = null;
        jsonResponseList = mapper.convertValue(response.get("results"), new TypeReference<List<CryptoNewsDto>>() {});
        if(jsonResponseList.isEmpty()){
//            return BaseResponse.builder().responseCode(Constants.FAILUARE_RESPNSE_CODE)
//                    .responseMessage(configurationUtil.getMessage(Constants.FAILUARE_RESPNSE_CODE)).
//                            response(null).build();
        }
        List<CryptoNewsDto>  cryptoNewsList= new ArrayList<>();
            for( CryptoNewsDto  cryptoNewsDto : jsonResponseList){
                cryptoNewsList.add(CryptoNewsDto.builder().
                        title(cryptoNewsDto.getTitle())
                        .url(cryptoNewsDto.getUrl()).
                        created_at(cryptoNewsDto.getCreated_at()).build());

            }
            if(cryptoNewsList.isEmpty()){
//                return BaseResponse.builder().responseCode(Constants.FAILUARE_RESPNSE_CODE)
//                        .responseMessage(configurationUtil.getMessage(Constants.FAILUARE_RESPNSE_CODE)).
//                                response(null).build();
            }
            saveEntities(cryptoNewsList);
        System.out.println("Ending");
//            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
//                .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).
//                response(cryptoNewsList).build();
    }

    @Transactional
    public void saveEntities(List<CryptoNewsDto> currencyResponse)
    {
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date1;
        try {
            for (int i = 0; i < currencyResponse.size(); i++) {
                date1=formatter1.parse(currencyResponse.get(i).getCreated_at());
                CurrencyNewsEntity currencyEntity = CurrencyNewsEntity.builder()
                        .title(currencyResponse.get(i).getTitle())
                        .date(date1)
                        .domain(currencyResponse.get(i).getUrl()).build();

                     currenciesDataDumpService.saveNews(currencyEntity);
            }

            } catch (Exception e) {
                e.printStackTrace();
        }
    }

}
