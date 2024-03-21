package com.EWTsystem.EWT.system.SmartBin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SmartBinDTO {
    private int binId;
    private Long distance;
    private float BinCapacity;
    private String Address;
    private double Lat;
    private double Lng;

}