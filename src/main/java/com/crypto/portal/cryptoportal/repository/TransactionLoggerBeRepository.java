package com.crypto.portal.cryptoportal.repository;

import com.crypto.portal.cryptoportal.entity.TransactionLoggerBeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLoggerBeRepository extends JpaRepository<TransactionLoggerBeEntity, String> {

}
