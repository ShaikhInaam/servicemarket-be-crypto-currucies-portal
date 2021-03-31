package com.crypto.portal.cryptoportal.repository;

import com.crypto.portal.cryptoportal.entity.CurrencyExchangesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrenciesExchangeDataDumpRepository extends JpaRepository<CurrencyExchangesEntity, Integer> {
    List<CurrencyExchangesEntity> findAll();
}
