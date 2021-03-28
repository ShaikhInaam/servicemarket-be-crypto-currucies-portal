package com.crypto.portal.cryptoportal.repository;

import com.crypto.portal.cryptoportal.entity.CurrencyStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyStatsRepository extends JpaRepository<CurrencyStatsEntity, String> {

    CurrencyStatsEntity findByCurrency_id(Integer currencyId);
}
