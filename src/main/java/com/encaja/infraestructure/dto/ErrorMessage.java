package com.encaja.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String id;
    private String status;
    private String errorMessage;
    private Date time;
}
