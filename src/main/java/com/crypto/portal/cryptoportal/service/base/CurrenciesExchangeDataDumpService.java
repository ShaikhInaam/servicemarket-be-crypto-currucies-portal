package com.crypto.portal.cryptoportal.service.base;


import com.crypto.portal.cryptoportal.dto.CurrenciesExchangeDataDumpDto;
import com.crypto.portal.cryptoportal.response.BaseResponse;

import java.util.List;

public interface CurrenciesExchangeDataDumpService {
   BaseResponse saveCurrenciesExchangeInfo(List<CurrenciesExchangeDataDumpDto> infoList);
}
