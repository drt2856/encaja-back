package com.encaja.infraestructure.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserDto {


    private String id;

    private String email;

    private String name;

    private String lastName;


    private Boolean enabled;

    private String password;

    private String urlImage;

    private String user;




}
