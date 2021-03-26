package com.crypto.portal.cryptoportal.repository;

import com.crypto.portal.cryptoportal.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, String> {
}
