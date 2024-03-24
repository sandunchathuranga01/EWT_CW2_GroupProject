package com.EWTsystem.EWT.system.UserLogingSystem.Client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "User_Data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;
    @Column(name ="Full_Name")
    private String fname;
    @Column(name ="Last_Name")
    private String lname;
    @Column(name ="Email")
    private String email;
    @Column(name ="Password")
    private String password;
    @Column(name ="mobile_number")
    private String mobileNumber;
    @Column(name ="Address")
    private String address;
    @Column(name = "create_dt")
    private String createDt;

}
