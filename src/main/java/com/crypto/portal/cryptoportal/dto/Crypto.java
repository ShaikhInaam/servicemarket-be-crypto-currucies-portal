package com.crypto.portal.cryptoportal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import liquibase.pro.packaged.S;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Crypto {

    private Integer id;
    private String coinName;
    private String symbol;
    private String marketCap;
    private String price;
    private String circularSupply;
    private String blockChain;
    private Integer rank;
    private String logoUrl;
    private Date dateAdded;
    private String description;

    private Integer currencyId;
    private String  changesTwentyFourHrs;
    private String  changesSevenDays;
    private String  changesThirtyDays;
    private String  changesYearly;
    private String  allTimeHigh;
    private String  allTimeLow;
    private Integer currencyStatsId;
}
