package com.smartbin.smart.bin.feature.controller;

import com.smartbin.smart.bin.feature.model.Smartbin;
import com.smartbin.smart.bin.feature.services.SmartBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/smartbin")
public class SmartBinController {
    @Autowired
    private SmartBinService smartBinService;
    public String saveSmartBin(@RequestBody Smartbin smartbin){
        return null;
    }


}
