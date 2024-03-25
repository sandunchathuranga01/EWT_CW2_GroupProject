package com.EWTsystem.EWT.system.RequestingService;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.events.Event;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Client_Request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int requestid;
    int contact_number;
    String address;
    String service_type;
    String situation;

}
