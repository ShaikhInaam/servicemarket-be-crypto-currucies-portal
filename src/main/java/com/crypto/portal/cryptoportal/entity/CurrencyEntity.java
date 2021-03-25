package com.crypto.portal.cryptoportal.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currency")
public class CurrencyEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String BlockChain;
    @Column(unique = true)
    private String Symbol;
    private String MarketCap;
    private String Price;
    private String CircSupply;
    @Column(unique = true)
    private String CoinName;
    private String rank;
    private String logo_url;
    private String date_added;
    private String Description;

    @ManyToOne
    @JoinColumn(name = "currency", nullable = false , insertable=false , updatable=false)
    private CurrencyWalletsEntity currency_wallet;

    @OneToMany(targetEntity = CurrencyExchangesEntity.class, fetch = FetchType.LAZY)
    private List<CurrencyExchangesEntity> currencyExchanList;

    @OneToMany(targetEntity = CurrencyStatsEntity.class, fetch = FetchType.LAZY)
    private List<CurrencyStatsEntity> currencyStatsList;
}
