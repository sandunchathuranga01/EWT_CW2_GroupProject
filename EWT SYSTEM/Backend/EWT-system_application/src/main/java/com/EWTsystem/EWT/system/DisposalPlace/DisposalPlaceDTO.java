package com.EWTsystem.EWT.system.DisposalPlace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DisposalPlaceDTO {
    private int locationID;
    private String name;
    private String address;
    private double lat;
    private double lng;

}
