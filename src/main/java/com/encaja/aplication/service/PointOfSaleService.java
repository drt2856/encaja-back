package com.encaja.aplication.service;

import com.encaja.aplication.Iservice.IBussinesService;
import com.encaja.aplication.Iservice.IPointOfSaleService;
import com.encaja.domain.model.*;
import com.encaja.domain.repository.PointOfSaleRepository;
import com.encaja.infraestructure.dto.ProductSelectDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PointOfSaleService implements IPointOfSaleService {

    private final PointOfSaleRepository pointOfSaleRepository;
    private final IBussinesService bussinesService;

    private final MediaService mediaService;

    @Autowired
    public PointOfSaleService(PointOfSaleRepository pointOfSaleRepository, IBussinesService bussinesService, MediaService mediaService) {
        this.pointOfSaleRepository = pointOfSaleRepository;
        this.bussinesService = bussinesService;
        this.mediaService = mediaService;
    }


    @Override
    public List<PointOfSale> findAll() {
        return pointOfSaleRepository.findAll();
    }

    @Override
    public PointOfSale findById(String id) {
        if (pointOfSaleRepository.existsById(id)) {

            return pointOfSaleRepository.findById(id).get();
        } else {
            throw new EntityNotFoundException("No existe un punto de venta con este el id: " + id);
        }
    }

    @Override
    public PointOfSale findByPointOfSaleName(String pointOfSalename) {

        Optional<PointOfSale> pointOfSale = pointOfSaleRepository.findAll()
                .stream()
                .filter(u -> u.getName().equals(pointOfSalename))
                .findFirst();

        if (pointOfSale.isPresent()) {
            return pointOfSale.get();
        } else {
            throw new EntityNotFoundException("No existe un punto de venta con este el nombre: " + pointOfSalename);
        }

    }

    @Override
    public List<PointOfSale> findAllByOnwerUserName(String ownerUsername) {
        String bussinesId = bussinesService.findBussinesByUserName(ownerUsername).getId();
        return findAll()
                .stream()
                .filter(pointOfSale -> {
                            if (pointOfSale.getBussinesid() != null) {
                                return pointOfSale.getBussinesid().getId().equals(bussinesId);
                            }
                            return false;
                        }
                )
                .collect(Collectors.toList());
    }

    @Override
    public PointOfSale addNewPointOfSaleForBussines(String ownerUserName, PointOfSale pointOfSale, MultipartFile uploadfile) {
        Bussines bussines = bussinesService.findBussinesByUserName(ownerUserName);
        pointOfSale.setBussinesid(new Bussines(bussines.getId()));
        pointOfSale = create(pointOfSale, uploadfile);
        return pointOfSale;
    }

    @Override
    public List<PointOfSaleProductInventory> findInventoryByPointOfSaleId(String id) {
        return findById(id).getPointOfSaleProductInventaryList();
    }

    @Override
    public List<Product> findNoExistInInventoryByPointOfSaleId(String id) {
        List<Product> products = findById(id).getBussinesid().getProductList();
        List<PointOfSaleProductInventory> points = findInventoryByPointOfSaleId(id);
        return products.stream()
                .filter(p -> points.stream().noneMatch(i -> i.getPointOfSaleProductInventaryPK().getProductid().equals(p.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public void addInventoryByListIdProducts(List<ProductSelectDto> listIdProducts, String idPointOfSale) {
        PointOfSale point = findById(idPointOfSale);

        listIdProducts.stream().forEach(prduct -> {
            if (prduct != null) {
                point.getPointOfSaleProductInventaryList().add(new PointOfSaleProductInventory(new PointOfSaleProductInventoryPK(idPointOfSale, prduct.getId()), prduct.getAmount()));
            }
        });
        update(point, null);
    }


    @Override
    public Boolean exitsById(String pointOfSaleId) {
        return pointOfSaleRepository.existsById(pointOfSaleId);
    }

    @Override
    public PointOfSale create(PointOfSale pointOfSale, MultipartFile uploadfile) {
        if (!existByPointOfSaleName(pointOfSale.getName())) {

            pointOfSale.setId(UUID.randomUUID().toString());
            if (uploadfile != null) {
                pointOfSale.setUrlLogo(mediaService.saveUserMedia(uploadfile, "pointOfSale"));
            } else {
                pointOfSale.setUrlLogo("");
            }


            return pointOfSaleRepository.save(pointOfSale);
        } else {
            throw new EntityExistsException("This pointOfSale name is already registered");
        }
    }


    @Override
    public PointOfSale update(PointOfSale pointOfSale, MultipartFile uploadfile) {
        if (pointOfSaleRepository.existsById(pointOfSale.getId())) {

            if (uploadfile != null) {
                pointOfSale.setUrlLogo(mediaService.saveUserMedia(uploadfile, "pointOfSale"));
            }


            return pointOfSaleRepository.save(pointOfSale);
        } else {
            throw new EntityNotFoundException("This pointOfSale does not exist or There is no pointOfSale with this id");
        }
    }


    @Override
    public void deleteById(String id) {
        if (pointOfSaleRepository.existsById(id)) {
            pointOfSaleRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("This pointOfSale does not exist or There is no pointOfSale with this id");
        }

    }


    public Boolean existByPointOfSaleName(String pointOfSalename) {
        return pointOfSaleRepository.findAll().stream().anyMatch(pointOfSales -> pointOfSales.getName().equals(pointOfSalename));
    }


    public List<Product> findAllProductsAvailableToUsersByPointOfSaleName(String pointOfSaleName) {
        return findByPointOfSaleName(pointOfSaleName).getPointOfSaleProductInventaryList().stream()
                .filter(pointOfSaleProductInventary -> pointOfSaleProductInventary.getQuantity() > 0 && pointOfSaleProductInventary.getProduct().getPublic1())
                .map(PointOfSaleProductInventory::getProduct)
                .collect(Collectors.toList());
    }

}
