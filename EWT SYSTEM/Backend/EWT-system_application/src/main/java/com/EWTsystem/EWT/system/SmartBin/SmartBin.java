package com.EWTsystem.EWT.system.SmartBin;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "smart_binX")
public class SmartBin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bin_id")
    private int binId;

    @Column(name = "distance")
    private Long distance;

    private float BinCapacity;
    private String Address;
    private double Lat;
    private double Lng;

}



