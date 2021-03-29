package com.crypto.portal.cryptoportal.controller;

import com.crypto.portal.cryptoportal.business.base.CurrencyWalletsBusiness;
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
@RequestMapping("/data-dump")
public class CurrencyWalletsController {

    @Autowired
    CurrencyWalletsBusiness currencyWalletsBusiness;

    @PostMapping("/dump-wallets")
    public ResponseEntity<BaseResponse> dumpWallets(@Valid @RequestBody BaseRequest request){

        return ResponseEntity.ok(currencyWalletsBusiness.dumpCurrencyWallets(request));

    }

}
