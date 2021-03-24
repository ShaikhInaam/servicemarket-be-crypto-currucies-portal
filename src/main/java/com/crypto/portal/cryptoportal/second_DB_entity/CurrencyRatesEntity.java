package com.crypto.portal.cryptoportal.second_DB_entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="currency_rates")
public class CurrencyRatesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "currency_id")
    private Integer currency_id;

    @Column(name = "date_time")
    private Timestamp date_time;

    @Column(name = "rate")
    private String rate;

}
