package com.encaja.aplication.service;

import com.encaja.aplication.Iservice.IBussinesService;
import com.encaja.aplication.Iservice.IProductService;
import com.encaja.domain.model.Bussines;
import com.encaja.domain.model.Product;
import com.encaja.domain.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final IBussinesService bussinesService;
    private final MediaService mediaService;
    @Autowired
    public ProductService(ProductRepository productRepository, IBussinesService bussinesService, MediaService mediaService) {
        this.productRepository = productRepository;
        this.bussinesService = bussinesService;
        this.mediaService = mediaService;
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(String id) {
        if (productRepository.existsById(id)) {

            return productRepository.findById(id).get();
        } else {
            throw new EntityNotFoundException("There is no product with this id");
        }
    }

    @Override
    public Product findByProductName(String productname) {

        Optional<Product> product = productRepository.findAll()
                .stream()
                .filter(u -> u.getName().equals(productname))
                .findFirst();

        if (product.isPresent()) {
            return product.get();
        } else {
            throw new EntityNotFoundException("This product does not exist or There is no product with this ProductName");
        }

    }

    @Override
    public Date setNewSubscribeDate(Date date) {
        return null;
    }



    @Override
    public Product addNewProductForBussines(String ownerUserName, Product product, MultipartFile uploadfile) {
        Bussines bussines = bussinesService.findBussinesByUserName(ownerUserName);
        product.setBussinesid(new Bussines(bussines.getId()));
        product = create(product,uploadfile);
        return product;
    }


    @Override
    public Boolean exitsById(String productId) {
        return productRepository.existsById(productId);
    }

    @Override
    public Product create(Product product, MultipartFile uploadfile) {


        product.setId(UUID.randomUUID().toString());
        if(uploadfile!=null){
            product.setMediaUrl(mediaService.saveUserMedia(uploadfile,"product"));
        }else{
            product.setMediaUrl("");
        }

        return productRepository.save(product);

    }


    @Override
    public Product update(Product product,MultipartFile uploadfile) {
        if (productRepository.existsById(product.getId())) {
            if(uploadfile!=null){
                product.setMediaUrl(mediaService.saveUserMedia(uploadfile,"product"));
            }

            return productRepository.save(product);
        } else {
            throw new EntityNotFoundException("This product does not exist or There is no product with this id");
        }
    }


    @Override
    public void deleteById(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("This product does not exist or There is no product with this id");
        }

    }


    @Override
    public Boolean existByProductName(String productname) {
        return productRepository.findAll().stream().anyMatch(products -> products.getName().equals(productname));
    }
}
