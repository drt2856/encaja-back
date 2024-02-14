package com.encaja.infraestructure.controller;


import com.encaja.aplication.Iservice.IPointOfSaleProductInventoryService;
import com.encaja.aplication.Iservice.IPointOfSaleService;
import com.encaja.aplication.service.JwtService;
import com.encaja.aplication.service.UserService;
import com.encaja.domain.model.Product;
import com.encaja.infraestructure.dto.TokenInfo;
import com.encaja.infraestructure.dto.UserPass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class AuthenticationController {
    private static final Logger LOGGER = LoggerFactory.getLogger("UserReguister");

    private final JwtService jwtService;
    private final IPointOfSaleService pointOfSaleService;
    private final AuthenticationManager authenticationManager;

    private final UserService userService;
    @GetMapping
    public String authenticasdste(@RequestBody UserPass userpass) {
        return "Esto esta funcionando";
    }

    @Autowired
    public AuthenticationController(JwtService jwtService, IPointOfSaleService pointOfSaleService, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtService = jwtService;
        this.pointOfSaleService = pointOfSaleService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }




    @PostMapping("/public/authentication")
    public ResponseEntity<TokenInfo> authenticate(@RequestBody UserPass userpass) {
        if (userpass == null || userpass.getUserName() == null || userpass.getPassword() == null) {
            LOGGER.error("Usuario: " + null + " Mensaje: Se han enviado datos nulos" + userpass);
        } else {
            if (userpass.getUserName().isBlank() || userpass.getPassword().isBlank()) {
                LOGGER.error("Usuario: " + userpass.getUserName() + " Mensaje: Se han enviado datos vacios");
            } else {
                try {

                    Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userpass.getUserName(), userpass.getPassword()));
                    final UserDetails userDetails = jwtService.loadUserByUsername(userpass.getUserName());

                    final String jwt = jwtService.generateToken(userDetails);
                    TokenInfo tokenInfo = new TokenInfo(userpass.getUserName(),jwt, userService.findByUserName(userpass.getUserName()).getId());
                    LOGGER.debug("Usuario: " + userpass.getUserName() + " Mensaje: Logeo exitoso");
                    return ResponseEntity.ok(tokenInfo);
                } catch (Exception e) {
                    e.fillInStackTrace();
                    LOGGER.info("Usuario: " + userpass.getUserName() + " Mensaje: datos incorrectos en el logueo" + e.fillInStackTrace());
                    throw new BadCredentialsException("Usuario: " + userpass.getUserName() + " Mensaje: datos incorrectos en el logueo " + e.getMessage());
                }
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/public/findAllProductsAvailableToUsersByPointOfSaleName/{pointOfSaleName}")
    public List<Product> findAllProductsAvailableToUsersByPointOfSaleName(@PathVariable String pointOfSaleName) {
        return pointOfSaleService.findAllProductsAvailableToUsersByPointOfSaleName(pointOfSaleName);
    }
}