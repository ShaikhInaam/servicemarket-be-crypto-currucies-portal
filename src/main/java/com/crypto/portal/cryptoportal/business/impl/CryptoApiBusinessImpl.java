package com.crypto.portal.cryptoportal.business.impl;


import com.crypto.portal.cryptoportal.business.base.CryptoApiBusiness;
import com.crypto.portal.cryptoportal.dto.*;
import com.crypto.portal.cryptoportal.entity.CurrencyEntity;
import com.crypto.portal.cryptoportal.entity.CurrencyStatsEntity;
import com.crypto.portal.cryptoportal.repository.CurrencyRepository;
import com.crypto.portal.cryptoportal.repository.CurrencyStatsRepository;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.*;
import com.crypto.portal.cryptoportal.service.base.CurrencyService;
import com.crypto.portal.cryptoportal.service.base.CurrencyStatsService;
import com.crypto.portal.cryptoportal.util.ConfigurationUtil;
import com.crypto.portal.cryptoportal.util.Constants;
import com.crypto.portal.cryptoportal.util.RestServiceUtility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class CryptoApiBusinessImpl implements CryptoApiBusiness {

    @Autowired
    RestServiceUtility utility;

    @Autowired
    ConfigurationUtil configurationUtil;
//
//    @Autowired
//    CurrencyRepository currencyRepository;
//
//    @Autowired
//    CurrencyStatsRepository currencyStatsRepository;

    @Autowired
    CurrencyStatsService currencyStatsService;

    @Autowired
    CurrencyService currencyService;

    @Override
    public BaseResponse getCryptoNames(BaseRequest request) {

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");


        List response = (List) utility.callGetJson(configurationUtil.getMessage(Constants.GET_CRYPTO_NAMES_AND_RATES_NOMICS_API_URL) + configurationUtil.getMessage(Constants.NOMICS_API_ACCESS_KEY), ArrayList.class, header);
        List<CryptoNamesApiJsonResponse> jsonResponseList = null;
        List<String> cryptoList = new ArrayList<>();
        if (response != null) {
            jsonResponseList = mapper.convertValue(response, new TypeReference<List<CryptoNamesApiJsonResponse>>() {
            });
            for (CryptoNamesApiJsonResponse cryptoNamesApiJsonResponse : jsonResponseList) {
                cryptoList.add(cryptoNamesApiJsonResponse.getCurrency());
            }

            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(cryptoList).build();

        } else {
            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
        }


    }

    @Override
    public BaseResponse getCryptoRates(BaseRequest request) {

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");

        List response = (List) utility.callGetJson(configurationUtil.getMessage(Constants.GET_CRYPTO_NAMES_AND_RATES_NOMICS_API_URL) + configurationUtil.getMessage(Constants.NOMICS_API_ACCESS_KEY), ArrayList.class, header);
        List<CryptoNamesApiJsonResponse> jsonResponseList = null;
        List<CryptoRatesDto> cryptoList = new ArrayList<>();
        if (response != null) {
            jsonResponseList = mapper.convertValue(response, new TypeReference<List<CryptoNamesApiJsonResponse>>() {
            });
            for (CryptoNamesApiJsonResponse cryptoRatesApiJsonResponse : jsonResponseList) {
                Double value = 0.0;
                if (cryptoRatesApiJsonResponse.getRate() != null) {
                    value = Double.parseDouble(cryptoRatesApiJsonResponse.getRate());
                    value = value * 161.5; //ToDo : extract current dollar rate every day with schedular
                }
                cryptoList.add(CryptoRatesDto.builder().currencyName(cryptoRatesApiJsonResponse.getCurrency()).currencyRateDollar(cryptoRatesApiJsonResponse.getRate()).currencyRatePkr(value.toString()).build());
            }

            CryptoRatesResponse cryptoRatesResponse = CryptoRatesResponse.builder().nameAndRate(cryptoList).date(LocalDate.now().toString()).build();

            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(cryptoRatesResponse).build();

        } else {
            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
        }
    }

    @Override
    public BaseResponse getCryptoInfo(BaseRequest request, String cryptoName) {

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");

        List response = (List) utility.callGetJson("https://api.nomics.com/v1/currencies?key=d0a7ba4aa83fa093b777e3085fa51a99&ids=" + cryptoName, ArrayList.class, header);
        List<CurrencyInfoDto> jsonResponseList = null;
        List<String> cryptoList = new ArrayList<>();

        if (response != null) {
            jsonResponseList = mapper.convertValue(response, new TypeReference<List<CurrencyInfoDto>>() {});


            System.out.println("response: " + jsonResponseList);

            for(CurrencyInfoDto currencyInfoResponse : jsonResponseList) {

               cryptoList.add(currencyInfoResponse.getDescription());
                cryptoList.add(currencyInfoResponse.getWebsite_url());
            }


     CurrencyInfoResponse currencyInfoResponse = CurrencyInfoResponse.builder().description(cryptoList.get(0)).url(cryptoList.get(1)).build();
            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(currencyInfoResponse).build();

        }
        else{
            return  BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
        }
    }

    @Override
    public BaseResponse getCryptoWeeklyRates(BaseRequest request, String cryptoName, String date) {

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");

        List response = (List) utility.callGetJson("https://api.nomics.com/v1/currencies/sparkline?key=d0a7ba4aa83fa093b777e3085fa51a99&ids=BTC&start=2019-11-25T00%3A00%3A00Z", CryptoWeeklyRatesResponse.class, header);
        //       List response = (List) utility.callGetJson("https://api.nomics.com/v1/currencies/sparkline?key=d0a7ba4aa83fa093b777e3085fa51a99&ids="+cryptoName+"&start="+date+"T00%3A00%3A00Z", ArrayList.class, header);
        // System.out.println("Url:"+"https://api.nomics.com/v1/currencies/sparkline?key=d0a7ba4aa83fa093b777e3085fa51a99&ids="+cryptoName+"&start="+date+"T00%3A00%3A00Z");
        System.out.println("initial response:" + response);
        List<CryptoWeeklyRatesResponse> jsonResponseList = null;
        List<CryptoWeeklyRatesDto> cryptoList = new ArrayList<>();
        if (response != null) {
            jsonResponseList = mapper.convertValue(response, new TypeReference<List<CryptoWeeklyRatesResponse>>() {
            });
            for (CryptoWeeklyRatesResponse cryptoWeeklyRatesDto : jsonResponseList) {
//                Double value = 0.0;
//                if (cryptoRatesApiJsonResponse.getRate() != null) {
//                    value = Double.parseDouble(cryptoRatesApiJsonResponse.getRate());
//                    value = value * 161.5; //ToDo : extract current dollar rate every day with schedular
//                }
                cryptoList.add(CryptoWeeklyRatesDto.builder().currency(cryptoWeeklyRatesDto.getCurrency()).timestamp(String.valueOf(cryptoWeeklyRatesDto.getTimestamps())).prices(String.valueOf(cryptoWeeklyRatesDto.getPrices())).build());
            }
            System.out.println("response: " + cryptoList);
            CryptoWeeklyRatesResponse cryptoWeeklyRatesResponse = CryptoWeeklyRatesResponse.builder().timestamps(cryptoList).prices(cryptoList).build();

            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(cryptoWeeklyRatesResponse).build();

        } else {
            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
        }

    }



    @Override
        public BaseResponse getExchangeCompanies(BaseRequest request) {
            ObjectMapper mapper = new ObjectMapper();

            HttpHeaders header = new HttpHeaders();
            header.add("ContentType", "application/json");
            header.add("X-CoinAPI-Key", configurationUtil.getMessage(Constants.COIN_API_ACCESS_KEY));

            List jsonResponse = (List) utility.callGetJson(configurationUtil.getMessage(Constants.GET_EXCHANGE_COMPANIES_AND_WEBSITE_COIN_API_URL), ExchangeCompaniesListJsonResponse.class, header);
            List<ExchangeCompaniesListJsonResponse> jsonResponseList = null;
            if(jsonResponse !=null){

                jsonResponseList = mapper.convertValue(jsonResponse, new TypeReference<List<ExchangeCompaniesListJsonResponse>>(){});

                List<ExchangeCompaniesResponse> response = new ArrayList<>();
                List<ExchangeCompaniesIconListJsonResponse> iconsListJsonResponse = getExchangeCompaniesIcon(request);
                for(ExchangeCompaniesListJsonResponse companiesJsonResponse : jsonResponseList){
                    for(ExchangeCompaniesIconListJsonResponse iconJsonResponse: iconsListJsonResponse){
                        if(companiesJsonResponse.getExchangeId().equals(iconJsonResponse.getExchangeId())){
                            response.add(ExchangeCompaniesResponse.builder()
                                    .exchangeId(companiesJsonResponse.getExchangeId())
                                    .name(companiesJsonResponse.getName())
                                    .website(companiesJsonResponse.getWebsite())
                                    .dataStart(companiesJsonResponse.getDataStart())
                                    .iconUrl(iconJsonResponse.getUrl())
                                    .build());
                        }
                    }
                }
                return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                        .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(response).build();
            }else{
                return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                        .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
            }
        }

        public List<ExchangeCompaniesIconListJsonResponse> getExchangeCompaniesIcon(BaseRequest request) {
            ObjectMapper mapper = new ObjectMapper();

            HttpHeaders header = new HttpHeaders();
            header.add("ContentType", "application/json");
            header.add("X-CoinAPI-Key", configurationUtil.getMessage(Constants.COIN_API_ACCESS_KEY));

            List response = (List) utility.callGetJson(configurationUtil.getMessage(Constants.GET_EXCHANGE_COMPANIES_ICONS_COIN_32X32_API_URL), ExchangeCompaniesIconListJsonResponse.class, header);
            List<ExchangeCompaniesIconListJsonResponse> jsonResponseList = null;
            if(response !=null){
                jsonResponseList = mapper.convertValue(response, new TypeReference<List<ExchangeCompaniesIconListJsonResponse>>(){});
                return jsonResponseList;
            }else{
                return null;
            }
        }



    @SneakyThrows
    @Override
    public BaseResponse getAllCryptoDetails(BaseRequest request) {
        SimpleDateFormat sdf   = new SimpleDateFormat("yyyy-MM-dd");
        List<Crypto> topCurrencies = new ArrayList<>();
        List<Crypto> newCurrencies = new ArrayList<>();
        List<Crypto> otherCurrencies = new ArrayList<>();
        List<CurrencyEntity> currencyEntityList  = currencyService.getAllCryptoDetails();

        for(CurrencyEntity currencyEntity : currencyEntityList){
            CurrencyStatsEntity currencyStatsEntity=  currencyStatsService.findByCurrencyId(currencyEntity.getId());
            Date dateAdded = sdf.parse(currencyEntity.getDate_added().toString());
            Crypto crypto  =  Crypto.builder().coinName(currencyEntity.getCoinName()).

                     symbol(currencyEntity.getSymbol())
                    .marketCap(currencyEntity.getMarketCap())
                    .id(currencyEntity.getId())
                    .price(currencyEntity.getPrice())
                    .circularSupply(currencyEntity.getCircSupply())
                    .blockChain(currencyEntity.getBlockChain())
                    .rank(currencyEntity.getRank())
                    .logoUrl(currencyEntity.getLogo_url())
                    .dateAdded(currencyEntity.getDate_added())
                    .description(currencyEntity.getDescription())
                    .currencyId(currencyStatsEntity.getCurrency_id())
                    .currencyStatsId(currencyStatsEntity.getId())
                    .allTimeHigh(currencyStatsEntity.getAll_time_high())
                    .allTimeLow(currencyStatsEntity.getAll_time_low())
                    .changesTwentyFourHrs(currencyStatsEntity.getChanges_24h())
                    .changesSevenDays(currencyStatsEntity.getChanges_7d())
                    .changesThirtyDays(currencyStatsEntity.getChanges_30d())
                    .changesYearly(currencyStatsEntity.getChanges_1y())
                    .build();

                    otherCurrencies.add(crypto);

                    if(currencyEntity.getRank() <=10){
                        topCurrencies.add(crypto);

                    }

                    long days_duration = TimeUnit.MILLISECONDS.toDays(
                    System.currentTimeMillis() - dateAdded.getTime());

                    if(days_duration<=10){
                         newCurrencies.add(crypto);

                    }


        }


        CryptoResponse cryptoResponse = CryptoResponse.builder().topCurrencies(topCurrencies).otherCurrencies(otherCurrencies).newCurrencies(newCurrencies).build();
        return BaseResponse.builder().response(cryptoResponse).responseCode(Constants.SUCCESS_RESPONSE_CODE).responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).build();

    }

}
