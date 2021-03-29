package com.crypto.portal.cryptoportal.service.base;

import com.crypto.portal.cryptoportal.dto.CurrencyWalletsDto;
import com.crypto.portal.cryptoportal.response.BaseResponse;

import java.util.List;

public interface CurrencyWalletsDumpService {

    BaseResponse saveCurrenciesWallets(List<CurrencyWalletsDto> infoList);
}
