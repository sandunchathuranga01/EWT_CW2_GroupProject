package com.EWT.DisposalPlace.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "disposal_places")
public class DisposalPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationID;
    private String name;
    private String address;
    private double lat;
    private double lng;
}
