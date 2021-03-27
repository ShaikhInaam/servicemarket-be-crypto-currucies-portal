package com.crypto.portal.cryptoportal.service.base;

import com.crypto.portal.cryptoportal.dto.CurrenciesDataDumpDto;
import com.crypto.portal.cryptoportal.response.BaseResponse;

import java.util.List;

public interface CurrenciesDataDumpService {

    BaseResponse saveCurrenciesInfo(List<CurrenciesDataDumpDto> infoList);

}
