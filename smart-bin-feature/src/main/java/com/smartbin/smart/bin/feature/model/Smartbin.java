package com.smartbin.smart.bin.feature.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Builder
@Document(collation = "smartBins")
public class Smartbin {
    private String id;
    private int binNumber;
    private String address;
    private double latitude;
    private double longitude;
}
