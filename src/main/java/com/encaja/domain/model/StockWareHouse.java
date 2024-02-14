/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.encaja.domain.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author drtorres
 */
@Entity
@Table(name = "stock_ware_house", catalog = "encaja_v1", schema = "public")
@NamedQueries({
    @NamedQuery(name = "StockWareHouse.findAll", query = "SELECT s FROM StockWareHouse s"),
    @NamedQuery(name = "StockWareHouse.findByWarehouseid", query = "SELECT s FROM StockWareHouse s WHERE s.stockWareHousePK.warehouseid = :warehouseid"),
    @NamedQuery(name = "StockWareHouse.findByProductid", query = "SELECT s FROM StockWareHouse s WHERE s.stockWareHousePK.productid = :productid"),
    @NamedQuery(name = "StockWareHouse.findByQuantity", query = "SELECT s FROM StockWareHouse s WHERE s.quantity = :quantity")})
public class StockWareHouse implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StockWareHousePK stockWareHousePK;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @JsonIgnore
    @JoinColumn(name = "productid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;
    @JsonIgnore
    @JoinColumn(name = "warehouseid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Warehouse warehouse;

    public StockWareHouse() {
    }

    public StockWareHouse(StockWareHousePK stockWareHousePK) {
        this.stockWareHousePK = stockWareHousePK;
    }

    public StockWareHouse(StockWareHousePK stockWareHousePK, int quantity) {
        this.stockWareHousePK = stockWareHousePK;
        this.quantity = quantity;
    }

    public StockWareHouse(String warehouseid, String productid) {
        this.stockWareHousePK = new StockWareHousePK(warehouseid, productid);
    }

    public StockWareHousePK getStockWareHousePK() {
        return stockWareHousePK;
    }

    public void setStockWareHousePK(StockWareHousePK stockWareHousePK) {
        this.stockWareHousePK = stockWareHousePK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stockWareHousePK != null ? stockWareHousePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StockWareHouse)) {
            return false;
        }
        StockWareHouse other = (StockWareHouse) object;
        if ((this.stockWareHousePK == null && other.stockWareHousePK != null) || (this.stockWareHousePK != null && !this.stockWareHousePK.equals(other.stockWareHousePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.drtorres.encaja.StockWareHouse[ stockWareHousePK=" + stockWareHousePK + " ]";
    }
    
}
