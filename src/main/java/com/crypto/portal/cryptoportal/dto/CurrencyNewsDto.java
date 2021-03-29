package com.crypto.portal.cryptoportal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CurrencyNewsDto {

    private  String title;
    private String domain;
}
