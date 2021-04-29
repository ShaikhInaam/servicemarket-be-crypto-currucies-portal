package com.crypto.portal.cryptoportal.repository;

import com.crypto.portal.cryptoportal.entity.TransactionLoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLoggerRepository extends JpaRepository<TransactionLoggerEntity, String> {

}