package com.crypto.portal.cryptoportal.repository;

import com.crypto.portal.cryptoportal.entity.CurrencyNewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface CurrencyNewsRepository extends JpaRepository<CurrencyNewsEntity, Integer> {

    //will get the news using date filter
    //if select date is empty will show an error message

    @Query("Select cn from CurrencyNewsEntity cn where cn.date =:date")
    List<CurrencyNewsEntity> findNewsByDate(@Param("date") LocalDate news_date);
}
