package com.crypto.portal.cryptoportal.entity;
//currency_wallets

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currency_wallets")
public class CurrencyWalletsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "currency_id",unique = true, nullable = false)
    private long currency_id;
    private String wallet_name;
    private String wallet_url;

    @OneToMany(targetEntity = CurrencyEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id")
    @Column(insertable = false, updatable = false)
    private List<CurrencyEntity> currencies;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "currency", nullable = false , insertable=false , updatable=false)
    private CurrencyEntity currency;
}

