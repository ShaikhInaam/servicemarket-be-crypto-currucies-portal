package com.crypto.portal.cryptoportal.business.impl;

import com.crypto.portal.cryptoportal.business.base.CurrencyNewsBusiness;
import com.crypto.portal.cryptoportal.dto.CurrencyNewsDto;
import com.crypto.portal.cryptoportal.entity.CurrencyNewsEntity;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import com.crypto.portal.cryptoportal.response.CurrencyNewsResponse;
import com.crypto.portal.cryptoportal.service.base.CurrencyNewsService;
import com.crypto.portal.cryptoportal.util.ConfigurationUtil;
import com.crypto.portal.cryptoportal.util.Constants;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class CurrencyNewsBusinessImpl implements CurrencyNewsBusiness {

    @Autowired
    CurrencyNewsService currencyNewsService;

    @Autowired
    ConfigurationUtil configurationUtil;

    @Override
    public BaseResponse findNewsByDate(BaseRequest request) {

//        java.util.Date currencyNewsDate;
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        LocalDate currencyNewsDate =  LocalDate.now();
        //Date currencyNewsDate = Date.from(Instant.from(localDate));
        //java.sql.Date.valueOf( currencyNewsDate );
        //Date currencyNewsDate = Date.valueOf(LocalDate.now());
        //CurrentDateFunction currencyNewsDate = new CurrentDateFunction();
       // Date currencyNewsDate = Date(new LocalDate.now());

        List<CurrencyNewsEntity> entities = currencyNewsService.findNewsByDate(currencyNewsDate);

        if(entities !=null){
            List<CurrencyNewsResponse> currencyNewsResponses = new ArrayList<>();
            for(CurrencyNewsEntity newsEntity : entities ){
                currencyNewsResponses.add(CurrencyNewsResponse.builder().title(newsEntity.getTitle())
                                           .domain(newsEntity.getDomain()).build());
            }
            return BaseResponse.builder().response(currencyNewsResponses).responseCode(Constants.SUCCESS_RESPONSE_CODE)
                                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).build();
        }else{
            return BaseResponse.builder().response(null).responseCode(Constants.SUCCESS_RESPONSE_CODE)
                                         .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).build();
        }

    }
}
