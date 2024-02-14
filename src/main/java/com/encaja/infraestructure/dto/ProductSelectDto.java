package com.encaja.infraestructure.dto;

import com.encaja.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductSelectDto {
    String id;
    String name;
    String urlMedia;
    boolean selected;
    int  amount;




    public ProductSelectDto(Product product) {
        this.id=product.getId();
        this.selected = false;
        this.urlMedia = product.getMediaUrl();
        this.name = product.getName();
        this.amount= 0;
    }

}
