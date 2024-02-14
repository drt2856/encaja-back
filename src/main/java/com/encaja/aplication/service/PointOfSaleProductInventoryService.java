package com.encaja.aplication.service;

import com.encaja.aplication.Iservice.IBussinesService;
import com.encaja.aplication.Iservice.IPointOfSaleProductInventoryService;
import com.encaja.domain.model.Bussines;
import com.encaja.domain.model.PointOfSaleProductInventory;
import com.encaja.domain.model.PointOfSaleProductInventoryPK;
import com.encaja.domain.model.Product;
import com.encaja.domain.repository.PointOfSaleProductInventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PointOfSaleProductInventoryService implements IPointOfSaleProductInventoryService {

    private final PointOfSaleProductInventoryRepository pointOfSaleProductInventoryRepository;
    private final IBussinesService bussinesService;
    private final MediaService mediaService;
    @Autowired
    public PointOfSaleProductInventoryService(PointOfSaleProductInventoryRepository pointOfSaleProductInventoryRepository, IBussinesService bussinesService, MediaService mediaService) {
        this.pointOfSaleProductInventoryRepository = pointOfSaleProductInventoryRepository;
        this.bussinesService = bussinesService;
        this.mediaService = mediaService;
    }


    @Override
    public List<PointOfSaleProductInventory> findAll() {
        return pointOfSaleProductInventoryRepository.findAll();
    }


    @Override
    public PointOfSaleProductInventory findById(PointOfSaleProductInventoryPK id) {
        if (pointOfSaleProductInventoryRepository.existsById(id)) {

            return pointOfSaleProductInventoryRepository.findById(id).get();
        } else {
            throw new EntityNotFoundException("No existe un producto en inventario con este id: "+id);
        }
    }



    @Override
    public PointOfSaleProductInventory addNewPointOfSaleProductInventaryForBussines(String s, PointOfSaleProductInventory pointOfSalePointOfSaleProductInventaryInventary, MultipartFile file) {
        return null;
    }



    @Override
    public PointOfSaleProductInventory create(PointOfSaleProductInventory pointOfSaleProductInventory) {


        return pointOfSaleProductInventoryRepository.save(pointOfSaleProductInventory);

    }


    @Override
    public PointOfSaleProductInventory update(PointOfSaleProductInventory pointOfSaleProductInventory) {
        if (pointOfSaleProductInventoryRepository.existsById(pointOfSaleProductInventory.getPointOfSaleProductInventaryPK())) {

            return pointOfSaleProductInventoryRepository.save(pointOfSaleProductInventory);
        } else {
            throw new EntityNotFoundException("No existe un producto en inventario con este id: "+pointOfSaleProductInventory);
        }
    }


    @Override
    public void deleteById(PointOfSaleProductInventoryPK id) {
        if (pointOfSaleProductInventoryRepository.existsById(id)) {
            pointOfSaleProductInventoryRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No existe un producto en inventario con este id: "+id);
        }

    }



}
