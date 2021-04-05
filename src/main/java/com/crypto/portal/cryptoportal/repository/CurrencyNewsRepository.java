package com.crypto.portal.cryptoportal.repository;

import com.crypto.portal.cryptoportal.entity.CurrencyNewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyNewsRepository  extends JpaRepository<CurrencyNewsEntity,Integer> {


}
