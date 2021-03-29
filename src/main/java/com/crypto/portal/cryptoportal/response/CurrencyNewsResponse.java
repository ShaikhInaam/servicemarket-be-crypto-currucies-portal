package com.crypto.portal.cryptoportal.response;

import com.crypto.portal.cryptoportal.dto.CurrencyNewsDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CurrencyNewsResponse {



    private String title;
    private String  domain;
}
