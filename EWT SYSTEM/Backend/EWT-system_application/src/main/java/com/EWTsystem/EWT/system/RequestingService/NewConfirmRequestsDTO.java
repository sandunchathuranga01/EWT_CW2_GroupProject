package com.EWTsystem.EWT.system.RequestingService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewConfirmRequestsDTO {
    int requestid;
    int contact_number;
    String address;
    String service_type;
    String situation;

}