package com.crypto.portal.cryptoportal.service.base;

import com.crypto.portal.cryptoportal.entity.CurrencyNewsEntity;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CurrencyNewsService {

    List<CurrencyNewsEntity> findNewsByDate(LocalDate news_date);
}
