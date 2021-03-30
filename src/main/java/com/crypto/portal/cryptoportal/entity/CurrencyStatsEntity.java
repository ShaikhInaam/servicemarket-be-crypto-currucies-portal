package com.crypto.portal.cryptoportal.entity;

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
@Table(name = "currency_stats")
public class CurrencyStatsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false, name = "currency_id")
    private Integer currency_id;

    private String changes_24h;
    private String changes_7d;
    private String changes_30d;
    private String changes_1y;
    private String all_time_high;
    private String all_time_low;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "currency_id", referencedColumnName = "id", nullable = false , insertable=false , updatable=false)
    private CurrencyEntity currency;
}
