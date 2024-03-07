package com.smartbin.smart.bin.feature.repo;

import com.smartbin.smart.bin.feature.model.Smartbin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartBinRepo extends MongoRepository<Smartbin,String> {
}
