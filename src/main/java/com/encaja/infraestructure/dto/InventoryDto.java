package com.encaja.infraestructure.dto;

import com.encaja.domain.model.PointOfSaleProductInventory;
import com.encaja.domain.model.StockWareHouse;
import lombok.Data;

@Data
public class InventoryDto {
    String productId;
    String utlMediaProduct;
    String nameProduct;
    Integer amountStock;
    Float price;
    Float cost;

    public InventoryDto(PointOfSaleProductInventory pointOfSaleProductInventary) {
        this.productId = pointOfSaleProductInventary.getProduct().getId();
        this.utlMediaProduct = pointOfSaleProductInventary.getProduct().getMediaUrl();
        this.nameProduct = pointOfSaleProductInventary.getProduct().getName();
        this.amountStock = pointOfSaleProductInventary.getQuantity();
        this.price=pointOfSaleProductInventary.getProduct().getPrice();
        this.cost=pointOfSaleProductInventary.getProduct().getCost();
    }

    public InventoryDto(StockWareHouse stockWareHouse) {
        this.productId = stockWareHouse.getProduct().getId();
        this.utlMediaProduct = stockWareHouse.getProduct().getMediaUrl();
        this.nameProduct = stockWareHouse.getProduct().getName();
        this.amountStock = stockWareHouse.getQuantity();
    }
}
