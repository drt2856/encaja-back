package com.encaja.aplication.service;

import com.encaja.aplication.Iservice.IBussinesService;
import com.encaja.domain.model.Bussines;
import com.encaja.domain.model.Product;
import com.encaja.domain.repository.BussinesRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BussinesService implements IBussinesService {

    private final  BussinesRepository bussinesRepository;
    private final MediaService mediaService;


    @Autowired
    public BussinesService(BussinesRepository bussinesRepository, MediaService mediaService) {
        this.bussinesRepository = bussinesRepository;
        this.mediaService = mediaService;
    }


    @Override
    public List<Bussines> findAll() {
        return bussinesRepository.findAll();
    }

    @Override
    public Bussines findById(String id) {
        if (bussinesRepository.existsById(id)) {

            return bussinesRepository.findById(id).get();
        } else {
            throw new EntityNotFoundException("There is no bussines with this id");
        }
    }

    @Override
    public Bussines findByBussinesName(String bussinesname) {

        Optional<Bussines> bussines = bussinesRepository.findAll()
                .stream()
                .filter(u -> u.getName().equals(bussinesname))
                .findFirst();

        if (bussines.isPresent()) {
            return bussines.get();
        } else {
            throw new EntityNotFoundException("This bussines does not exist or There is no bussines with this BussinesName");
        }

    }

    @Override
    public Bussines findBussinesByUserName(String userName) {
        Optional<Bussines> bussines = bussinesRepository.findAll()
                .stream()
                .filter(u -> u.getOwnerid().getUsername().equals(userName))
                .findFirst();

        if (bussines.isPresent()) {
            return bussines.get();
        } else {
            throw new EntityNotFoundException("Este usuario no tiene ningun negocio creado o es inexistente");
        }
    }

    @Override
    public Bussines findBussinesByUserId(String userId) {
        Optional<Bussines> bussines = bussinesRepository.findAll()
                .stream()
                .filter(u -> u.getOwnerid().getId().equals(userId))
                .findFirst();

        if (bussines.isPresent()) {
            return bussines.get();
        } else {
            throw new EntityNotFoundException("Este usuario no tiene ningun negocio creado o es inexistente");
        }
    }

    @Override
    public Date setNewSubscribeDate(Date date) {
        return null;
    }



    @Override
    public List<Product> findAllProductsByOnwerId(String userId) {
        return findBussinesByUserId(userId).getProductList();
    }


    @Override
    public Boolean exitsById(String bussinesId) {
        return bussinesRepository.existsById(bussinesId);
    }

    @Override
    public Bussines create(Bussines bussines, MultipartFile file) {
        if (!existByBussinesName(bussines.getName())) {

            if (findAll().stream().anyMatch(bussines1 -> bussines1.getOwnerid().getId().equals(bussines.getOwnerid().getId()))) {
                throw new EntityExistsException("Usted ya tiene un negocio asingando, de ser necesario delimitar mas sun negocio concidere crear otro punto de venta o sucursal");
            }
            bussines.setId(UUID.randomUUID().toString());
            LocalDate local = LocalDate.now().plusDays(45);
            bussines.setLicenseFreezeDate(new Date(local.getYear(),local.getMonthValue(),local.getDayOfMonth()));

            if(file!=null){
                bussines.setUrlLogo(mediaService.saveUserMedia(file,"bussines"));
            }else{
                bussines.setUrlLogo("");
            }


            return bussinesRepository.save(bussines);
        } else {
            throw new EntityExistsException("This bussines name is already registered");
        }
    }


    @Override
    public Bussines update(Bussines bussines, MultipartFile file) {
        if (bussinesRepository.existsById(bussines.getId())) {
            if(file!=null){
                bussines.setUrlLogo(mediaService.saveUserMedia(file,"bussines"));
            }


            return bussinesRepository.save(bussines);
        } else {
            throw new EntityNotFoundException("This bussines does not exist or There is no bussines with this id");
        }
    }


    @Override
    public void deleteById(String id) {
        if (bussinesRepository.existsById(id)) {
            bussinesRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("This bussines does not exist or There is no bussines with this id");
        }

    }


    @Override
    public Boolean existByBussinesName(String bussinesname) {
        return bussinesRepository.findAll().stream().anyMatch(bussiness -> bussiness.getName().equals(bussinesname));
    }
}
