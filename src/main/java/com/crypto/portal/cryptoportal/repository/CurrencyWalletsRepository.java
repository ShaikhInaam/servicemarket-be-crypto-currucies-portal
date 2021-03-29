package com.crypto.portal.cryptoportal.repository;

import com.crypto.portal.cryptoportal.dto.CurrencyWalletsDto;
import com.crypto.portal.cryptoportal.entity.CurrencyWalletsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyWalletsRepository extends JpaRepository<CurrencyWalletsEntity,String> {
}
