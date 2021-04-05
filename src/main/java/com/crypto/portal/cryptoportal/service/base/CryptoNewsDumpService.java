package com.crypto.portal.cryptoportal.service.base;

import com.crypto.portal.cryptoportal.dto.CryptoNewsDto;
import com.crypto.portal.cryptoportal.dto.CurrencyWalletsDto;
import com.crypto.portal.cryptoportal.entity.CurrencyNewsEntity;
import com.crypto.portal.cryptoportal.response.BaseResponse;

import java.util.Currency;
import java.util.List;

public interface CryptoNewsDumpService {

    public   void saveNews(CurrencyNewsEntity currencyNewsEntity);
}
