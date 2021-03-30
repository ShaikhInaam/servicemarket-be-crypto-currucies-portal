package com.crypto.portal.cryptoportal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyWalletsDto {

    private  String  name;
    private  String  url;

//    CurrencyWalletsDto(@JsonProperty("name") String name, @JsonProperty("url") String url){}
}
