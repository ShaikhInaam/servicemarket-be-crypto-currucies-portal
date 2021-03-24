package com.crypto.portal.cryptoportal.second_DB_repository;

import com.crypto.portal.cryptoportal.second_DB_entity.CurrencyRatesDayWiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRateDayWiseRepository extends JpaRepository<CurrencyRatesDayWiseEntity,Integer> {
}
