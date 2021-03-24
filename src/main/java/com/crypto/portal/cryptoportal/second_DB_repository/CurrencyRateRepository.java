package com.crypto.portal.cryptoportal.second_DB_repository;

import com.crypto.portal.cryptoportal.second_DB_entity.CurrencyRatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRatesEntity, Integer> {
}
