package com.EWTsystem.EWT.system.UserLogingSystem.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class
AuthUserDTO {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String mobileNumber;
    private String address;

}
