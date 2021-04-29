package com.crypto.portal.cryptoportal.controller;

import com.crypto.portal.cryptoportal.business.base.CurrenciesDataDumpBusiness;
import com.crypto.portal.cryptoportal.business.base.CryptoApiBusiness;
import com.crypto.portal.cryptoportal.business.base.CurrencyWalletsBusiness;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import com.crypto.portal.cryptoportal.service.impl.TransactionBeLoggerService;
import com.crypto.portal.cryptoportal.service.impl.TransactionLoggerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/crypto")
public class CryptoApiController {

    @Autowired
    CryptoApiBusiness business;

    @Autowired
    CurrencyWalletsBusiness currencyWalletsBusiness;
    
    @Autowired
    CurrenciesDataDumpBusiness currenciesDataDumpBusiness;

    @Autowired
    TransactionLoggerService transactionLoggerService;

    @Autowired
    TransactionBeLoggerService transactionLoggerBeService;

    @PostMapping("/names")
    public ResponseEntity<BaseResponse> getCryptoNames(@Valid @RequestBody BaseRequest request){

        BaseResponse responseObject = business.getCryptoNames(request);

                transactionLoggerService.log(request.getTransactionId(), "/portal/job/job-shift",
                request, responseObject,"Post");

                transactionLoggerBeService.log(request.getTransactionId(), "/portal/job/job-shift",
                request, responseObject,"Post");

        return ResponseEntity.ok(responseObject);

    }

    @PostMapping("/rates")
    public ResponseEntity<BaseResponse> getCryptoRates(@Valid @RequestBody BaseRequest request){

        BaseResponse responseObject = business.getCryptoRates(request);
        transactionLoggerService.log(request.getTransactionId(), "/portal/job/job-shift",
                request, responseObject,"Post");

        return ResponseEntity.ok(responseObject);
    }

    @PostMapping("/exchanges")
    public ResponseEntity<BaseResponse> getExchangesCompanies(@Valid @RequestBody BaseRequest request){
        return ResponseEntity.ok(business.getExchangeCompanies(request));
    }

    @PostMapping(value = "/search/{key}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> getCurrencyInfoByValue(@Valid @RequestBody BaseRequest request,@PathVariable String key){

        return ResponseEntity.ok(business.getCryptoInfo(request,key));

    }

    @PostMapping(value = "/weeklyrate/{cryptoName}/{date}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> getCryptoWeeklyRate(@Valid @RequestBody BaseRequest request,@PathVariable String cryptoName,@PathVariable String date){

        return ResponseEntity.ok(business.getCryptoWeeklyRates(request,cryptoName,date));

    }


    @PostMapping("/getAll")
    public ResponseEntity<BaseResponse> getAllCryptoCurrencyInfo(@Valid @RequestBody BaseRequest request){

        return ResponseEntity.ok(business.getAllCryptoDetails(request));

    }

    @PostMapping("/dump-currencies")
    public ResponseEntity<BaseResponse> getCurrencies(@Valid @RequestBody BaseRequest request){

        return ResponseEntity.ok(currenciesDataDumpBusiness.getCryptoCurrencies(request));

    }

    @PostMapping("/dump-wallets")
    public ResponseEntity<BaseResponse> dumpWallets(@Valid @RequestBody BaseRequest request){

        return ResponseEntity.ok(currencyWalletsBusiness.dumpCurrencyWallets(request));

    }

}
