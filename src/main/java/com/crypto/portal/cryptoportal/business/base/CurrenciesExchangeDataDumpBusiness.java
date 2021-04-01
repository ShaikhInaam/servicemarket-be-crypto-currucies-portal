package com.crypto.portal.cryptoportal.business.base;

import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;


public interface CurrenciesExchangeDataDumpBusiness {
    BaseResponse getCryptoCurrenciesExchanges(BaseRequest request);
}
