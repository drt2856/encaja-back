package com.encaja.aplication.Iservice;


import com.encaja.domain.model.StockWareHouse;
import com.encaja.domain.model.Warehouse;
import com.encaja.domain.model.Product;
import com.encaja.infraestructure.dto.ProductSelectDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IWarehouseService {

    List<Warehouse> findAll();

    Warehouse create(Warehouse warehouse, MultipartFile uploadfile);

    Warehouse update(Warehouse warehouse,MultipartFile uploadfile);

    void deleteById(String id);

    Warehouse findById(String id);


    Boolean exitsById(String warehouseId);

    Warehouse findByWarehouseName(String warehousename);

    List<Warehouse> findAllByOnwerUserName(String OnwerUserName);


    Warehouse addNewWarehouseForBussines(String ownerUserName, Warehouse warehouse, MultipartFile file);

    List<StockWareHouse> findInventoryByWarehouseId(String id);

    List<Product> findNoExistInInventoryByWarehouseId(String id);

    void addInventoryByListIdProducts(List<ProductSelectDto> listIdProducts, String idWarehouse);

    List<Product> findAllProductsAvailableToUsersByWarehouseName(String warehouseName);
}
