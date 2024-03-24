package com.example.DemoRequest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestDTO {
    private int ID;
    private long Cnumber;
    private String Csituation;
    private String locationId;
    private String requiredService;
}
