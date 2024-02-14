package com.encaja.infraestructure.controller;


import com.encaja.aplication.Iservice.IBussinesService;
import com.encaja.aplication.service.JwtService;
import com.encaja.domain.model.Bussines;
import com.encaja.domain.model.Product;
import com.encaja.domain.model.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/bussines")
public class BussinesController {
    private final IBussinesService bussinesService;
    private final JwtService jwtService;
    @Autowired
    public BussinesController(IBussinesService bussinesService, JwtService jwtService) {
        this.bussinesService = bussinesService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public List<Bussines> findAll() {
        return bussinesService.findAll();
    }

    @GetMapping(path = {"/find/{id}"})
    public ResponseEntity<Bussines> findById(@PathVariable String id) {
        return ResponseEntity.ok(bussinesService.findById(id));
    }
    @GetMapping(path = {"/findBussinesByUserName/{userName}"})
    public ResponseEntity<Bussines> findBussinesByUserName(@PathVariable String userName,@RequestHeader("Authorization") String authorizationHeader) {

        System.out.println(jwtService.extractUsernameByAuthorizationHeader(authorizationHeader));
        return ResponseEntity.ok(bussinesService.findBussinesByUserName(userName));
    }

    @GetMapping(path = {"/findAllProductsByOnwerId/{onwerId}"})
    public ResponseEntity<List<Product>> findAllProductsByOnwerId(@PathVariable String onwerId) {
        return ResponseEntity.ok(bussinesService.findAllProductsByOnwerId(onwerId));
    }



    @PostMapping
    public Bussines createBussines(@RequestParam("bussines") String bussinesSrt,
                            @RequestParam(value = "file",required = false) MultipartFile file) {

        ObjectMapper objectMapper = new ObjectMapper();
        Bussines bussines = null;
        try {
            bussines = objectMapper.readValue(bussinesSrt, Bussines.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return bussinesService.create(bussines, file);
    }

    @PutMapping
    public Bussines UpdateBussines(@RequestParam("bussines") String bussinesSrt,
                                   @RequestParam(value = "file",required = false) MultipartFile file) {

        ObjectMapper objectMapper = new ObjectMapper();
        Bussines bussines = null;
        try {
            bussines = objectMapper.readValue(bussinesSrt, Bussines.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return bussinesService.update(bussines, file);
    }


    @DeleteMapping(path = {"/{id}"})
    public void delete(@PathVariable String id) {
        bussinesService.deleteById(id);
    }



}
