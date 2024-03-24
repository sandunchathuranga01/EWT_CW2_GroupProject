package com.EWT.DisposalPlace.Controller;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class LoginRequest {
    @Id
    private String username;
    private String password;


}
