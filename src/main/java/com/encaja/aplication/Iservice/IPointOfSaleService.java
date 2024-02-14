package com.encaja.aplication.Iservice;


import com.encaja.domain.model.PointOfSale;
import com.encaja.domain.model.PointOfSaleProductInventory;
import com.encaja.domain.model.Product;
import com.encaja.infraestructure.dto.ProductSelectDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPointOfSaleService {

    List<PointOfSale> findAll();

    PointOfSale create(PointOfSale pointOfSale, MultipartFile uploadfile);

    PointOfSale update(PointOfSale pointOfSale,MultipartFile uploadfile);

    void deleteById(String id);

    PointOfSale findById(String id);


    Boolean exitsById(String pointOfSaleId);

    PointOfSale findByPointOfSaleName(String pointOfSalename);

    List<PointOfSale> findAllByOnwerUserName(String OnwerUserName);


    PointOfSale addNewPointOfSaleForBussines(String ownerUserName, PointOfSale pointOfSale, MultipartFile file);

    List<PointOfSaleProductInventory> findInventoryByPointOfSaleId(String id);

    List<Product> findNoExistInInventoryByPointOfSaleId(String id);

    void addInventoryByListIdProducts(List<ProductSelectDto> listIdProducts, String idPointOfSale);

    List<Product> findAllProductsAvailableToUsersByPointOfSaleName(String pointOfSaleName);
}
