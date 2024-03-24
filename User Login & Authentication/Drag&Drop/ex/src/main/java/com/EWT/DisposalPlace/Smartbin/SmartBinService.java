package com.EWT.DisposalPlace.Smartbin;
import org.springframework.stereotype.Service;

@Service
public class SmartBinService {
    public String getFilledBins(){
        return "Filled bins information: [Bin1, Bin2, Bin3]";
}


    public String getNotFilledBins(){
        return "Not filled bins information : [Bin4, Bin5, Bin6]";
    }

    public void toggleBinsVisibility(){


    }
}
