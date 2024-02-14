package com.encaja.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenInfo {
    private String username;
    private String token;
    private String userId;
}
