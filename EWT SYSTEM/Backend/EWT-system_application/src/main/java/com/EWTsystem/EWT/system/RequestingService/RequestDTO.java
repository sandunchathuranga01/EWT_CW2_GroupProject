package com.EWTsystem.EWT.system.RequestingService;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestDTO {
    int requestid;
    int contact_number;
    String address;
    String service_type;
    String situation;

}