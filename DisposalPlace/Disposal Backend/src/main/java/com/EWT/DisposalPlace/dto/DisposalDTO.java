package com.EWT.DisposalPlace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DisposalDTO {
    private int locationID;
    private String name;
    private String address;
    private double lat;
    private double lng;

}
