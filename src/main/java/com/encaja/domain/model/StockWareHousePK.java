/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.encaja.domain.model;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *
 * @author drtorres
 */
@Embeddable
public class StockWareHousePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "warehouseid")
    private String warehouseid;
    @Basic(optional = false)
    @Column(name = "productid")
    private String productid;

    public StockWareHousePK() {
    }

    public StockWareHousePK(String warehouseid, String productid) {
        this.warehouseid = warehouseid;
        this.productid = productid;
    }

    public String getWarehouseid() {
        return warehouseid;
    }

    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (warehouseid != null ? warehouseid.hashCode() : 0);
        hash += (productid != null ? productid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StockWareHousePK)) {
            return false;
        }
        StockWareHousePK other = (StockWareHousePK) object;
        if ((this.warehouseid == null && other.warehouseid != null) || (this.warehouseid != null && !this.warehouseid.equals(other.warehouseid))) {
            return false;
        }
        if ((this.productid == null && other.productid != null) || (this.productid != null && !this.productid.equals(other.productid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.drtorres.encaja.StockWareHousePK[ warehouseid=" + warehouseid + ", productid=" + productid + " ]";
    }
    
}
