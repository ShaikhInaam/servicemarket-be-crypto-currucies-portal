package com.crypto.portal.cryptoportal.repository;

import com.crypto.portal.cryptoportal.entity.CurrencyStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyStatsRepository extends JpaRepository<CurrencyStatsEntity, String> {
}
