package com.EWTsystem.EWT.system.UserLogingSystem.Admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdminDTO {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String password;

}
