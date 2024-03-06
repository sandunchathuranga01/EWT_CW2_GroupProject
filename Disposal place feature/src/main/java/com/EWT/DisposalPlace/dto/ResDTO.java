package com.EWT.DisposalPlace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
public class ResDTO {
    private String code;
    private String message;
    private Object content;
}
