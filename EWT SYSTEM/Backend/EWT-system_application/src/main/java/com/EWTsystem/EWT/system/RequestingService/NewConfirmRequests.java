package com.EWTsystem.EWT.system.RequestingService;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Confirm_Client_Request")
public class NewConfirmRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int requestid;
    int contact_number;
    String address;
    String service_type;
    String situation;

}
