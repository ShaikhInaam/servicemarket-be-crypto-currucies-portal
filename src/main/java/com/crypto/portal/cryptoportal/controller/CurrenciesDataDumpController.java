package com.crypto.portal.cryptoportal.controller;


import com.crypto.portal.cryptoportal.business.base.CurrenciesDataDumpBusiness;
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
@RequestMapping("/data")
public class CurrenciesDataDumpController {

    @Autowired
    CurrenciesDataDumpBusiness currenciesDataDumpBusiness;

    @PostMapping("/dump-currencies")
    public ResponseEntity<BaseResponse> getCurrencies(@Valid @RequestBody BaseRequest request){

        return ResponseEntity.ok(currenciesDataDumpBusiness.getCryptoCurrencies(request));

    }
}
