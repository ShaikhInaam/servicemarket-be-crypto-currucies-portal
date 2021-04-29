package com.crypto.portal.cryptoportal.service.impl;

import com.crypto.portal.cryptoportal.entity.TransactionLoggerBeEntity;
import com.crypto.portal.cryptoportal.entity.TransactionLoggerEntity;
import com.crypto.portal.cryptoportal.repository.TransactionLoggerBeRepository;
import com.crypto.portal.cryptoportal.repository.TransactionLoggerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class TransactionBeLoggerService {

    @Autowired
    TransactionLoggerBeRepository repository;

    public void log(String trxId, String url, Object request, Object response, String method){
        try{
            ObjectMapper Obj = new ObjectMapper();
            String requestStr = "{}";
            if(request !=null){
                requestStr = Obj.writeValueAsString(request);
            }

            String responseStr = "{}";
            if(response != null){
                responseStr = Obj.writeValueAsString(response);
            }
            TransactionLoggerBeEntity entity = TransactionLoggerBeEntity.builder()
                    .requestJson(requestStr).responseJson(responseStr).transactionId(trxId)
                    .time(new Timestamp(System.currentTimeMillis())).method(method).url(url).build();

            repository.save(entity);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}


