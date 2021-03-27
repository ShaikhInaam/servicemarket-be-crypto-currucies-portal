package com.crypto.portal.cryptoportal.second_DB_entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="currency_rates_daywise")
public class CurrencyRatesDayWiseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="currency_id")
    private Integer currency_id;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "rete")
    private String rate;

}
