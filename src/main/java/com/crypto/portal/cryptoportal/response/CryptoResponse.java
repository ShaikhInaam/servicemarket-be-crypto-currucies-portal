package com.crypto.portal.cryptoportal.response;

import com.crypto.portal.cryptoportal.dto.Crypto;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CryptoResponse {
        List<Crypto> topCurrencies = new ArrayList<>();
        List<Crypto> newCurrencies = new ArrayList<>();
        List<Crypto> otherCurrencies = new ArrayList<>();

}
