package com.encaja.infraestructure.controller;


import com.encaja.aplication.Iservice.IUserService;
import com.encaja.aplication.service.JwtService;
import com.encaja.domain.model.Users;
import com.encaja.infraestructure.dto.ChangePaswordDto;
import com.encaja.infraestructure.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;
    private final JwtService jwtService;

    @Autowired
    public UserController(IUserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public List<Users> findAll(@RequestHeader("Authorization") String authorizationHeader) {
        return userService.findAll();
    }

    @GetMapping(path = {"/find/{id}"})
    public ResponseEntity<Users> findById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }


    @PostMapping
    public Users createUser(@RequestParam("user") String usuarioStr,
                            @RequestParam(value = "file",required = false) MultipartFile file) {

        ObjectMapper objectMapper = new ObjectMapper();
        Users user = null;
        try {
            user = objectMapper.readValue(usuarioStr, Users.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return userService.createUser(user, file);
    }

    @PutMapping
    public Users updateUser(@RequestParam("user") String usuarioStr,
                            @RequestParam(value = "file",required = false) MultipartFile file) {

        ObjectMapper objectMapper = new ObjectMapper();
        Users user = null;
        try {
            user = objectMapper.readValue(usuarioStr, Users.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(user);
        return userService.update(user, file);
    }

    @DeleteMapping(path = {"/{id}"})
    public void delete(@PathVariable String id) {
        userService.deleteById(id);
    }

    @PatchMapping("/changePaswword")
    public Boolean changePaswword(@RequestBody ChangePaswordDto changePaswordDto) {
        return userService.changePaswword(changePaswordDto);
    }

    @PostMapping(value = "/addNewUserForBussines/{idOwner}")
    public Users createUser(@RequestParam("user") String usuarioStr,
                            @RequestParam(value = "file",required = false) MultipartFile file, @PathVariable String idOwner) {

        ObjectMapper objectMapper = new ObjectMapper();
        Users user = null;
        try {
            user = objectMapper.readValue(usuarioStr, Users.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return userService.addNewUserForBussines(idOwner, user, file);
    }


    @GetMapping("/findAllWorkersByOnwerUserName")
    public List<Users> findAllWorkersByOnwerUserName(@RequestHeader("Authorization") String authorizationHeader) {
        return userService.findAllWorkersByOnwerUserName(jwtService.extractUsernameByAuthorizationHeader(authorizationHeader));
    }



}
