package com.crypto.portal.cryptoportal.controller;

import com.crypto.portal.cryptoportal.business.base.CurrenciesDataDumpBusiness;
import com.crypto.portal.cryptoportal.business.base.CurrenciesExchangeDataDumpBusiness;
import com.crypto.portal.cryptoportal.dto.CurrenciesExchangeDataDumpDto;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/crypto/exchanges/data")   ////crypto/exchanges/datadump-currencies-exchanges
public class CurrenciesExchangeDataDumpController {

    @Autowired
    CurrenciesExchangeDataDumpBusiness currenciesExchangeDataDumpBusiness;

    @PostMapping("/dump-currencies-exchanges")
    public ResponseEntity<BaseResponse> getCurrencies(@Valid @RequestBody BaseRequest request){
        return ResponseEntity.ok(currenciesExchangeDataDumpBusiness.getCryptoCurrenciesExchanges(request));
    }
}
