package com.encaja.aplication.Iservice;


import com.encaja.domain.model.Bussines;
import com.encaja.domain.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface IBussinesService {

    List<Bussines> findAll();

    Bussines create(Bussines bussines, MultipartFile file);

    Bussines update(Bussines bussines, MultipartFile file);

    void deleteById(String id);

    Bussines findById(String id);

    Boolean existByBussinesName(String bussinesname);

    Boolean exitsById(String bussinesId);

    Bussines findByBussinesName(String bussinesname);

    Bussines findBussinesByUserName(String userName);
    Bussines findBussinesByUserId(String userId);

    Date setNewSubscribeDate(Date date);


    List<Product> findAllProductsByOnwerId(String userId);


}
