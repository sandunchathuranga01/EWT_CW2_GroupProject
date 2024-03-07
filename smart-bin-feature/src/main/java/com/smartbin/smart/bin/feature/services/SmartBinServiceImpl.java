package com.smartbin.smart.bin.feature.services;

import com.smartbin.smart.bin.feature.model.Smartbin;
import com.smartbin.smart.bin.feature.repo.SmartBinRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmartBinServiceImpl implements SmartBinService{

    @Autowired
    private SmartBinRepo smartBinRepo;
    @Override
    public String save(Smartbin smartbin) {
        return smartBinRepo.save(smartbin).getId();

    }
}
