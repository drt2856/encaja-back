package com.encaja.infraestructure.controller;


import com.encaja.aplication.Iservice.IWarehouseService;
import com.encaja.aplication.service.JwtService;
import com.encaja.domain.model.Warehouse;
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
@RequestMapping("/warehouse")
public class WarehouseController {
    private final IWarehouseService warehouseService;
    private final JwtService jwtService;
    private final InventoryMaper inventoryMaper;

    @Autowired
    public WarehouseController(IWarehouseService warehouseService, JwtService jwtService, InventoryMaper inventoryMaper) {
        this.warehouseService = warehouseService;
        this.jwtService = jwtService;
        this.inventoryMaper = inventoryMaper;
    }

    @GetMapping
    public List<Warehouse> findAll() {
        return warehouseService.findAll();
    }

    @GetMapping(path = {"/find/{id}"})
    public ResponseEntity<Warehouse> findById(@PathVariable String id) {
        return ResponseEntity.ok(warehouseService.findById(id));
    }

    @GetMapping(path = {"/findAllByOnwerUserName"})
    public List<Warehouse> findAllByOnwerUserName(@RequestHeader("Authorization") String authorizationHeader) {
        return warehouseService.findAllByOnwerUserName(jwtService.extractUsernameByAuthorizationHeader(authorizationHeader));
    }



    @PutMapping
    public Warehouse updateWarehouse(@RequestParam("warehouse") String warehouseStr,
                                         @RequestParam(value = "file",required = false) MultipartFile file){

        ObjectMapper objectMapper = new ObjectMapper();
        Warehouse warehouse = null;
        try {
            warehouse = objectMapper.readValue(warehouseStr, Warehouse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return warehouseService.update(warehouse, file);
    }


    @DeleteMapping(path = {"/{id}"})
    public void delete(@PathVariable String id) {
        warehouseService.deleteById(id);
    }

    @PostMapping(value = "/addNewWarehouseForBussines")
    public Warehouse createUser(@RequestParam("warehouse") String warehouseStr,
                            @RequestParam(value = "file",required = false) MultipartFile file,
                            @RequestHeader("Authorization") String authorizationHeader) {

        ObjectMapper objectMapper = new ObjectMapper();
        Warehouse warehouse = null;
        try {
            warehouse = objectMapper.readValue(warehouseStr, Warehouse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return warehouseService.addNewWarehouseForBussines(jwtService.extractUsernameByAuthorizationHeader(authorizationHeader), warehouse, file);
    }

    @GetMapping(path = {"/findInventoryByWarehouseId/{id}"})
    public List<InventoryDto> findInventoryByWarehouseId(@PathVariable String id) {

        return inventoryMaper.ListStockWareHouseEntityToListInventoryDto(warehouseService.findInventoryByWarehouseId(id));
    }
    @GetMapping(path = {"/findNoExistInInventoryByWarehouseId/{id}"})
    public List<ProductSelectDto> findNoExistInInventoryByWarehouseId(@PathVariable String id) {

        return  inventoryMaper.ListProductSelectedEntityToListProductSelectedDto(warehouseService.findNoExistInInventoryByWarehouseId(id)) ;
    }

    @PostMapping("/addInventoryByListIdProducts/{id}")
    public void adedInventoryByListIdProducts(@RequestBody List<ProductSelectDto> listIdProducts, @PathVariable String id){

         warehouseService.addInventoryByListIdProducts(listIdProducts, id);
    }
    @GetMapping("/findAllProductsAvailableToUsersByWarehouseName/{warehouseName}")
    public List<Product> findAllProductsAvailableToUsersByWarehouseName(@PathVariable String warehouseName) {
        return warehouseService.findAllProductsAvailableToUsersByWarehouseName(warehouseName);
    }

}
