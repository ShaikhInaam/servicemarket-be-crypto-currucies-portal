package com.crypto.portal.cryptoportal.business.base;

import com.crypto.portal.cryptoportal.dto.CurrenciesDataDumpDescDto;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;

import java.util.List;

public interface CurrenciesExchangeDataDumpBusiness {
    BaseResponse getCryptoCurrenciesExchanges(BaseRequest request);
    //List<CurrenciesDataDumpDescDto> getCryptoInfo(BaseRequest request);

}
