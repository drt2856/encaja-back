package com.encaja.infraestructure.Exeptions;

public class BadAutentication extends RuntimeException{
    public BadAutentication(String exeption){
        super(exeption);
    }
}
