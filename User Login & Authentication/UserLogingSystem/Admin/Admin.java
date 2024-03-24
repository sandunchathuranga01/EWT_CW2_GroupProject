package com.EWTsystem.EWT.system.UserLogingSystem.Admin;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Admin_Data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;
    @Column(name ="Admin_First_Name")
    private String fname;
    @Column(name ="Admin_Last_Name")
    private String lname;
    @Column(name ="Admin_Email")
    private String email;
    @Column(name ="Admin_Password")
    private String password;

}

