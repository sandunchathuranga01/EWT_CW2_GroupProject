package com.example.DemoRequest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Request {
    @Id
    private int ID;
    private long Cnumber;
    private String Csituation;
    private String locationId;
    private String requiredService;
}
