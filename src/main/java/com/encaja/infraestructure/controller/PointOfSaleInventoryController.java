package com.encaja.infraestructure.controller;


import com.encaja.aplication.Iservice.IPointOfSaleProductInventoryService;
import com.encaja.aplication.service.JwtService;
import com.encaja.domain.model.PointOfSaleProductInventory;
import com.encaja.domain.model.PointOfSaleProductInventoryPK;
import com.encaja.domain.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/pointOfSaleInventory")
public class PointOfSaleInventoryController {
    private final IPointOfSaleProductInventoryService pointOfSaleProductInventoryService;
    private final JwtService jwtService;

    @Autowired
    public PointOfSaleInventoryController(IPointOfSaleProductInventoryService pointOfSaleProductInventoryService, JwtService jwtService) {
        this.pointOfSaleProductInventoryService = pointOfSaleProductInventoryService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public List<PointOfSaleProductInventory> findAll() {
        return pointOfSaleProductInventoryService.findAll();
    }


    @PutMapping
    public PointOfSaleProductInventory updatePointOfSaleProductInventory(@RequestBody PointOfSaleProductInventory pointOfSaleProductInventory){

        return pointOfSaleProductInventoryService.update(pointOfSaleProductInventory);
    }


    @PutMapping (path = {"/deleteById"})
    public void delete(@RequestBody PointOfSaleProductInventoryPK id) {

        pointOfSaleProductInventoryService.deleteById(id);
    }






}
