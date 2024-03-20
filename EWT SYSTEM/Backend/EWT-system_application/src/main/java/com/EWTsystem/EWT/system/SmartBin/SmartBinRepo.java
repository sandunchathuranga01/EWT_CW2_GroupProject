package com.EWTsystem.EWT.system.SmartBin;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SmartBinRepo extends JpaRepository<SmartBin,Integer> {

    //filter filled and not filled smart bins from database

    @Query(value = "SELECT * FROM smart_binX WHERE distance < 10", nativeQuery = true)
    List<SmartBin> filledSmartBins();

    @Query(value = "SELECT * FROM smart_binX WHERE distance >= 10", nativeQuery = true)
    List<SmartBin> getNotFilledSmartBins();
}

