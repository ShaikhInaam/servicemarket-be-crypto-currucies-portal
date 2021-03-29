package com.crypto.portal.cryptoportal.business.impl;

import com.crypto.portal.cryptoportal.business.base.CurrencyWalletsBusiness;
import com.crypto.portal.cryptoportal.dto.CurrencyWalletsDto;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import com.crypto.portal.cryptoportal.service.impl.CurrencyWalletsDumpServiceImpl;
import com.crypto.portal.cryptoportal.util.ConfigurationUtil;
import com.crypto.portal.cryptoportal.util.Constants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CurrencyWalletsBusinessImpl implements CurrencyWalletsBusiness {


    @Autowired
    ConfigurationUtil configurationUtil;

    @Autowired
    CurrencyWalletsDumpServiceImpl currencyWalletsDumpService;

    @Override
    public BaseResponse dumpCurrencyWallets(BaseRequest request) {

        try {
            CurrencyWalletsDto[] currencyWalletsDto = new ObjectMapper().readValue(new ClassPathResource("currencies_wallets.json").getFile(), CurrencyWalletsDto[].class);

            List<CurrencyWalletsDto> currencyWalletsDtos = new ArrayList<>(Arrays.asList(currencyWalletsDto));

            return currencyWalletsDumpService.saveCurrenciesWallets(currencyWalletsDtos);

        } catch (IOException e) {
            e.printStackTrace();
            return  BaseResponse.builder().responseCode(Constants.FAILUARE_RESPNSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.FAILUARE_RESPNSE_CODE)).response(null).build();
        }


    }

}
