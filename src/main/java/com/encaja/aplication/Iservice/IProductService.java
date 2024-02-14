package com.encaja.aplication.Iservice;


import com.encaja.domain.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface IProductService {

    List<Product> findAll();

    Product create(Product product, MultipartFile uploadfile);

    Product update(Product product,MultipartFile file);

    void deleteById(String id);

    Product findById(String id);

    Boolean existByProductName(String productname);

    Boolean exitsById(String productId);

    Product findByProductName(String productname);

    Date setNewSubscribeDate(Date date);


    Product addNewProductForBussines(String s, Product product, MultipartFile file);
}
