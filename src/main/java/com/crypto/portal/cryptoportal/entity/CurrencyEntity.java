package com.crypto.portal.cryptoportal.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
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
    @Column(name = "BlockChain")
    private String BlockChain;
    @Column(name = "Symbol")
    private String Symbol;
    @Column(name = "MarketCap")
    private String MarketCap;
    @Column(name = "Price")
    private String Price;
    @Column(name = "CoinName")
    private String CircSupply;
    @Column(name = "CoinName")
    private String CoinName;
    @Column(name = "rank")
    private String rank;
    @Column(name = "logo_url")
    private String logo_url;
    @Column(name = "date_added")
    private Date date_added;
    @Column(name = "Description")
    private String Description;

    @ManyToOne
    @JoinColumn(name = "currency", nullable = false , insertable=false , updatable=false)
    private CurrencyWalletsEntity currency_wallet;

    @OneToMany(targetEntity = CurrencyExchangesEntity.class, fetch = FetchType.LAZY)
    private List<CurrencyExchangesEntity> currencyExchanList;

    @OneToMany(targetEntity = CurrencyStatsEntity.class, fetch = FetchType.LAZY)
    private List<CurrencyStatsEntity> currencyStatsList;
}
