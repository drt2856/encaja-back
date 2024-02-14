package com.encaja.infraestructure.controller;


import com.encaja.aplication.Iservice.IProductService;
import com.encaja.aplication.service.JwtService;
import com.encaja.domain.model.Product;
import com.encaja.domain.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final IProductService productService;
    private final JwtService jwtService;

    @Autowired
    public ProductController(IProductService productService, JwtService jwtService) {
        this.productService = productService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(path = {"/find/{id}"})
    public ResponseEntity<Product> findById(@PathVariable String id) {
        return ResponseEntity.ok(productService.findById(id));
    }


    @PutMapping
    public Product updateProduct(@RequestParam("product") String productStr,
                                         @RequestParam(value = "file",required = false) MultipartFile file){

        ObjectMapper objectMapper = new ObjectMapper();
        Product product = null;
        try {
            product = objectMapper.readValue(productStr, Product.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return productService.update(product, file);
    }


    @DeleteMapping(path = {"/{id}"})
    public void delete(@PathVariable String id) {
        productService.deleteById(id);
    }

    @PostMapping(value = "/addNewProductForBussines")
    public Product createProduct(@RequestParam("product") String productStr,
                                  @RequestParam(value = "file",required = false) MultipartFile file,
                                  @RequestHeader("Authorization") String authorizationHeader) {

        ObjectMapper objectMapper = new ObjectMapper();
        Product product = null;
        try {
            product = objectMapper.readValue(productStr, Product.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return productService.addNewProductForBussines(jwtService.extractUsernameByAuthorizationHeader(authorizationHeader), product, file);
    }



}
