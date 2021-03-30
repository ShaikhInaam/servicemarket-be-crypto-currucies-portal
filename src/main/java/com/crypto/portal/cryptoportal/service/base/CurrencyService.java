package com.crypto.portal.cryptoportal.service.base;

import com.crypto.portal.cryptoportal.entity.CurrencyEntity;


import java.util.List;

public interface CurrencyService {
    List<CurrencyEntity> getAllCryptoDetails();
}
