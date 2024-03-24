package com.EWT.DisposalPlace.Smartbin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/smartbins")
public class SmartbinController {

    @Autowired
    private SmartBinService smartBinService;

    @GetMapping("/filled")
    @ResponseBody
    public String getFilledBins() {
        return smartBinService.getFilledBins();
    }

    @GetMapping("/notfilled")
    @ResponseBody
    public String getNotFilledBins() {
        return smartBinService.getNotFilledBins();
    }
}
