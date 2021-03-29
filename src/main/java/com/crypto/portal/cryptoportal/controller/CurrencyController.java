package com.crypto.portal.cryptoportal.controller;

import com.crypto.portal.cryptoportal.business.base.CurrencyNewsBusiness;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyNewsBusiness currencyNewsBusiness;

    @GetMapping("/news")
    public ResponseEntity<BaseResponse> currencyNews(@Valid @RequestBody BaseRequest request){

        return  ResponseEntity.ok(currencyNewsBusiness.findNewsByDate(request));
    }
}
