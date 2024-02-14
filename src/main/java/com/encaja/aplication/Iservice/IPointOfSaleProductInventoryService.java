package com.encaja.aplication.Iservice;

import com.encaja.domain.model.PointOfSaleProductInventory;
import com.encaja.domain.model.PointOfSaleProductInventoryPK;
import com.encaja.domain.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface IPointOfSaleProductInventoryService {
    List<PointOfSaleProductInventory> findAll();

    PointOfSaleProductInventory create(PointOfSaleProductInventory pointOfSalePointOfSaleProductInventaryInventary);

    PointOfSaleProductInventory update(PointOfSaleProductInventory pointOfSalePointOfSaleProductInventaryInventary);

    void deleteById(PointOfSaleProductInventoryPK id);

    PointOfSaleProductInventory findById(PointOfSaleProductInventoryPK id);




    PointOfSaleProductInventory addNewPointOfSaleProductInventaryForBussines(String s, PointOfSaleProductInventory pointOfSalePointOfSaleProductInventaryInventary, MultipartFile file);



}
