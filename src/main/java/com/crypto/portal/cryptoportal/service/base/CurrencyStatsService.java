package com.crypto.portal.cryptoportal.service.base;

import com.crypto.portal.cryptoportal.entity.CurrencyEntity;
import com.crypto.portal.cryptoportal.entity.CurrencyStatsEntity;

public interface CurrencyStatsService {

    CurrencyStatsEntity findByCurrencyId(Integer id);
}
