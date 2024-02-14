package com.encaja.infraestructure.dto;

import lombok.Data;

@Data
public class ChangePaswordDto {
    private String idUser;
    private String oldPassword;
    private String newPassword;
}
