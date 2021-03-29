package com.crypto.portal.cryptoportal.business.base;

import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;

public interface CurrencyNewsBusiness {

    BaseResponse findNewsByDate(BaseRequest request);
}
