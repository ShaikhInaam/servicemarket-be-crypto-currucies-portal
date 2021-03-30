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
    private Integer id;

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

    @OneToOne(mappedBy = "currency", fetch = FetchType.LAZY)
    private CurrencyStatsEntity currencyStatsEntity;
}
