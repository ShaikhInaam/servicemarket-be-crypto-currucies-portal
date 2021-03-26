package com.crypto.portal.cryptoportal.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
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

    @Column(name = "block_chain")
    private String BlockChain;

    private String symbol;

    @Column(name = "market_cap")
    private String MarketCap;

    private String price;

    @Column(name = "circ_supply")
    private String CircSupply;

    @Column(unique = true, name = "coin_name")
    private String CoinName;

    private int rank;
    private String logo_url;
    private Date date_added;
    private String description;

    @ManyToOne
    @JoinColumn(name = "currency", nullable = false , insertable=false , updatable=false)
    private CurrencyWalletsEntity currency_wallet;

    @OneToMany(targetEntity = CurrencyExchangesEntity.class, fetch = FetchType.LAZY)
    private List<CurrencyExchangesEntity> currencyExchanList;

//    @OneToOne(targetEntity = CurrencyStatsEntity.class, fetch = FetchType.LAZY)
    @OneToOne(mappedBy = "currency", fetch = FetchType.LAZY)
    private CurrencyStatsEntity currencyStatsEntity;
}
