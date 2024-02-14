package com.encaja.infraestructure.controller;


import com.encaja.aplication.Iservice.IPointOfSaleService;
import com.encaja.aplication.service.JwtService;
import com.encaja.domain.model.PointOfSale;
import com.encaja.domain.model.Product;
import com.encaja.infraestructure.dto.InventoryDto;
import com.encaja.infraestructure.dto.ProductSelectDto;
import com.encaja.infraestructure.mapper.InventoryMaper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/pointOfSale")
public class PointOfSaleController {
    private final IPointOfSaleService pointOfSaleService;
    private final JwtService jwtService;
    private final InventoryMaper inventoryMaper;

    @Autowired
    public PointOfSaleController(IPointOfSaleService pointOfSaleService, JwtService jwtService, InventoryMaper inventoryMaper) {
        this.pointOfSaleService = pointOfSaleService;
        this.jwtService = jwtService;
        this.inventoryMaper = inventoryMaper;
    }

    @GetMapping
    public List<PointOfSale> findAll() {
        return pointOfSaleService.findAll();
    }

    @GetMapping(path = {"/find/{id}"})
    public ResponseEntity<PointOfSale> findById(@PathVariable String id) {
        return ResponseEntity.ok(pointOfSaleService.findById(id));
    }

    @GetMapping(path = {"/findAllByOnwerUserName"})
    public List<PointOfSale> findAllByOnwerUserName(@RequestHeader("Authorization") String authorizationHeader) {
        return pointOfSaleService.findAllByOnwerUserName(jwtService.extractUsernameByAuthorizationHeader(authorizationHeader));
    }



    @PutMapping
    public PointOfSale updatePointOfSale(@RequestParam("pointOfSale") String pointOfSaleStr,
                                         @RequestParam(value = "file",required = false) MultipartFile file){

        ObjectMapper objectMapper = new ObjectMapper();
        PointOfSale pointOfSale = null;
        try {
            pointOfSale = objectMapper.readValue(pointOfSaleStr, PointOfSale.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return pointOfSaleService.update(pointOfSale, file);
    }


    @DeleteMapping(path = {"/{id}"})
    public void delete(@PathVariable String id) {
        pointOfSaleService.deleteById(id);
    }

    @PostMapping(value = "/addNewPointOfSaleForBussines")
    public PointOfSale createUser(@RequestParam("pointOfSale") String pointOfSaleStr,
                            @RequestParam(value = "file",required = false) MultipartFile file,
                            @RequestHeader("Authorization") String authorizationHeader) {

        ObjectMapper objectMapper = new ObjectMapper();
        PointOfSale pointOfSale = null;
        try {
            pointOfSale = objectMapper.readValue(pointOfSaleStr, PointOfSale.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return pointOfSaleService.addNewPointOfSaleForBussines(jwtService.extractUsernameByAuthorizationHeader(authorizationHeader), pointOfSale, file);
    }

    @GetMapping(path = {"/findInventoryByPointOfSaleId/{id}"})
    public List<InventoryDto> findInventoryByPointOfSaleId(@PathVariable String id) {

        return inventoryMaper.ListPointOfSaleProductInventoryEntityToListInventoryDto(pointOfSaleService.findInventoryByPointOfSaleId(id));
    }
    @GetMapping(path = {"/findNoExistInInventoryByPointOfSaleId/{id}"})
    public List<ProductSelectDto> findNoExistInInventoryByPointOfSaleId(@PathVariable String id) {

        return  inventoryMaper.ListProductSelectedEntityToListProductSelectedDto(pointOfSaleService.findNoExistInInventoryByPointOfSaleId(id)) ;
    }

    @PostMapping("/addInventoryByListIdProducts/{id}")
    public void adedInventoryByListIdProducts(@RequestBody List<ProductSelectDto> listIdProducts, @PathVariable String id){

         pointOfSaleService.addInventoryByListIdProducts(listIdProducts, id);
    }
    @GetMapping("/findAllProductsAvailableToUsersByPointOfSaleName/{pointOfSaleName}")
    public List<Product> findAllProductsAvailableToUsersByPointOfSaleName(@PathVariable String pointOfSaleName) {
        return pointOfSaleService.findAllProductsAvailableToUsersByPointOfSaleName(pointOfSaleName);
    }

}
