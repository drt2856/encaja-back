package com.encaja.domain.repository;

import com.encaja.domain.model.PointOfSaleProductInventoryPK;
import com.encaja.domain.model.PointOfSaleProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointOfSaleProductInventoryRepository extends JpaRepository<PointOfSaleProductInventory, PointOfSaleProductInventoryPK> {

}
