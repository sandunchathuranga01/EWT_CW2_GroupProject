package com.EWTsystem.EWT.system.UserLogingSystem.Collector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class REGCollectorDTO {
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String contactnumber;
    private String address;
    private String drivingid;
    private String nic;
    private String modelOfvehicale;
    private String vehicleregno;
    private String status;

}
