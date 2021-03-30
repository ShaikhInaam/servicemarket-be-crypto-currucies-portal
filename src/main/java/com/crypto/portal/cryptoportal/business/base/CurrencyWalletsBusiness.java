package com.crypto.portal.cryptoportal.business.base;

import com.crypto.portal.cryptoportal.dto.CurrencyWalletsDto;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;

import java.util.List;

public interface CurrencyWalletsBusiness {

    BaseResponse dumpCurrencyWallets(BaseRequest request);

}
