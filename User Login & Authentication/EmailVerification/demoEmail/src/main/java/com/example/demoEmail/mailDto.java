package com.example.demoEmail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class mailDto {
    private String toMail;
    private String message;
    private String subject;
}
