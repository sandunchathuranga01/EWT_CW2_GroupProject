package com.EWTsystem.EWT.system.UserLogingSystem.Collector;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "REG_Collector_Data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class REGCollector {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
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
