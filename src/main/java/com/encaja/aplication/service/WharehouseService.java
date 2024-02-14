package com.encaja.aplication.service;

import com.encaja.aplication.Iservice.IBussinesService;
import com.encaja.aplication.Iservice.IWarehouseService;
import com.encaja.domain.model.*;
import com.encaja.domain.repository.WarehouseRepository;
import com.encaja.infraestructure.dto.ProductSelectDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WharehouseService implements IWarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final IBussinesService bussinesService;

    private final MediaService mediaService;

    @Autowired
    public WharehouseService(WarehouseRepository warehouseRepository, IBussinesService bussinesService, MediaService mediaService) {
        this.warehouseRepository = warehouseRepository;
        this.bussinesService = bussinesService;
        this.mediaService = mediaService;
    }


    @Override
    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse findById(String id) {
        if (warehouseRepository.existsById(id)) {

            return warehouseRepository.findById(id).get();
        } else {
            throw new EntityNotFoundException("No existe un punto de venta con este el id: " + id);
        }
    }

    @Override
    public Warehouse findByWarehouseName(String warehousename) {

        Optional<Warehouse> warehouse = warehouseRepository.findAll()
                .stream()
                .filter(u -> u.getName().equals(warehousename))
                .findFirst();

        if (warehouse.isPresent()) {
            return warehouse.get();
        } else {
            throw new EntityNotFoundException("No existe un punto de venta con este el nombre: " + warehousename);
        }

    }

    @Override
    public List<Warehouse> findAllByOnwerUserName(String ownerUsername) {
        String bussinesId = bussinesService.findBussinesByUserName(ownerUsername).getId();
        return findAll()
                .stream()
                .filter(warehouse -> {
                            if (warehouse.getBussinesid() != null) {
                                return warehouse.getBussinesid().getId().equals(bussinesId);
                            }
                            return false;
                        }
                )
                .collect(Collectors.toList());
    }

    @Override
    public Warehouse addNewWarehouseForBussines(String ownerUserName, Warehouse warehouse, MultipartFile uploadfile) {
        Bussines bussines = bussinesService.findBussinesByUserName(ownerUserName);
        warehouse.setBussinesid(new Bussines(bussines.getId()));
        warehouse = create(warehouse, uploadfile);
        return warehouse;
    }

    @Override
    public List<StockWareHouse> findInventoryByWarehouseId(String id) {
        return findById(id).getStockWareHouseList();
    }

    @Override
    public List<Product> findNoExistInInventoryByWarehouseId(String id) {
        List<Product> products = findById(id).getBussinesid().getProductList();
        List<StockWareHouse> points = findInventoryByWarehouseId(id);
        return products.stream()
                .filter(p -> points.stream().noneMatch(i -> i.getStockWareHousePK().getProductid().equals(p.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public void addInventoryByListIdProducts(List<ProductSelectDto> listIdProducts, String idWarehouse) {
        Warehouse point = findById(idWarehouse);

        listIdProducts.stream().forEach(prduct -> {
            if (prduct != null) {
                point.getStockWareHouseList().add(new StockWareHouse(new StockWareHousePK(idWarehouse, prduct.getId()), prduct.getAmount()));
            }
        });
        update(point, null);
    }


    @Override
    public Boolean exitsById(String warehouseId) {
        return warehouseRepository.existsById(warehouseId);
    }

    @Override
    public Warehouse create(Warehouse warehouse, MultipartFile uploadfile) {
        if (!existByWarehouseName(warehouse.getName())) {

            warehouse.setId(UUID.randomUUID().toString());
           if (uploadfile != null) {
                warehouse.setUrlLogo(mediaService.saveUserMedia(uploadfile, "warehouse"));
            } else {
                warehouse.setUrlLogo("");
            }


            return warehouseRepository.save(warehouse);
        } else {
            throw new EntityExistsException("This warehouse name is already registered");
        }
    }


    @Override
    public Warehouse update(Warehouse warehouse, MultipartFile uploadfile) {
        if (warehouseRepository.existsById(warehouse.getId())) {

            if (uploadfile != null) {
                warehouse.setUrlLogo(mediaService.saveUserMedia(uploadfile, "warehouse"));
            }


            return warehouseRepository.save(warehouse);
        } else {
            throw new EntityNotFoundException("This warehouse does not exist or There is no warehouse with this id");
        }
    }


    @Override
    public void deleteById(String id) {
        if (warehouseRepository.existsById(id)) {
            warehouseRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("This warehouse does not exist or There is no warehouse with this id");
        }

    }


    public Boolean existByWarehouseName(String warehousename) {
        return warehouseRepository.findAll().stream().anyMatch(warehouses -> warehouses.getName().equals(warehousename));
    }


    public List<Product> findAllProductsAvailableToUsersByWarehouseName(String warehouseName) {
        return findByWarehouseName(warehouseName).getStockWareHouseList().stream()
                .filter(warehouseProductInventary -> warehouseProductInventary.getQuantity() > 0 && warehouseProductInventary.getProduct().getPublic1())
                .map(StockWareHouse::getProduct)
                .collect(Collectors.toList());
    }

}
