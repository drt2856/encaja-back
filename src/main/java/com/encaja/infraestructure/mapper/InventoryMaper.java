package com.encaja.infraestructure.mapper;

import com.encaja.domain.model.PointOfSaleProductInventory;
import com.encaja.domain.model.Product;
import com.encaja.domain.model.StockWareHouse;
import com.encaja.infraestructure.dto.InventoryDto;
import com.encaja.infraestructure.dto.ProductSelectDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InventoryMaper {

    public List<InventoryDto> ListPointOfSaleProductInventoryEntityToListInventoryDto(List<PointOfSaleProductInventory> pointOfSaleProductInventaryList) {
        return pointOfSaleProductInventaryList.stream()
                .map(optionPost -> new InventoryDto(optionPost))
                .collect(Collectors.toList());
    }
    public List<InventoryDto> ListStockWareHouseEntityToListInventoryDto(List<StockWareHouse> pointOfSaleProductInventaryList) {
        return pointOfSaleProductInventaryList.stream()
                .map(optionPost -> new InventoryDto(optionPost))
                .collect(Collectors.toList());
    }
    public List<ProductSelectDto> ListProductSelectedEntityToListProductSelectedDto(List<Product> products) {
        return products.stream()
                .map(p -> new ProductSelectDto(p))
                .collect(Collectors.toList());
    }
}
