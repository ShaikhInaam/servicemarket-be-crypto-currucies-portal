package com.crypto.portal.cryptoportal.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currency_news")
public class CurrencyNewsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @Column(name = "date")
    //@Temporal(TemporalType.DATE)
    private LocalDate date_news;

    private String domain;

}
