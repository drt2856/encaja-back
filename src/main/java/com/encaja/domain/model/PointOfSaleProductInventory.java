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
@Table(name = "point_of_sale_product_inventary", catalog = "encaja_v1", schema = "public")
public class PointOfSaleProductInventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PointOfSaleProductInventoryPK pointOfSaleProductInventaryPK;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @JsonIgnore
    @JoinColumn(name = "point_of_saleid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PointOfSale pointOfSale;

    @JoinColumn(name = "productid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public PointOfSaleProductInventory() {
    }

    public PointOfSaleProductInventory(PointOfSaleProductInventoryPK pointOfSaleProductInventaryPK) {
        this.pointOfSaleProductInventaryPK = pointOfSaleProductInventaryPK;
    }

    public PointOfSaleProductInventory(PointOfSaleProductInventoryPK pointOfSaleProductInventaryPK, int quantity) {
        this.pointOfSaleProductInventaryPK = pointOfSaleProductInventaryPK;
        this.quantity = quantity;
    }

    public PointOfSaleProductInventory(String pointOfSaleid, String productid) {
        this.pointOfSaleProductInventaryPK = new PointOfSaleProductInventoryPK(pointOfSaleid, productid);
    }

    public PointOfSaleProductInventoryPK getPointOfSaleProductInventaryPK() {
        return pointOfSaleProductInventaryPK;
    }

    public void setPointOfSaleProductInventaryPK(PointOfSaleProductInventoryPK pointOfSaleProductInventaryPK) {
        this.pointOfSaleProductInventaryPK = pointOfSaleProductInventaryPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PointOfSale getPointOfSale() {
        return pointOfSale;
    }

    public void setPointOfSale(PointOfSale pointOfSale) {
        this.pointOfSale = pointOfSale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pointOfSaleProductInventaryPK != null ? pointOfSaleProductInventaryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PointOfSaleProductInventory)) {
            return false;
        }
        PointOfSaleProductInventory other = (PointOfSaleProductInventory) object;
        if ((this.pointOfSaleProductInventaryPK == null && other.pointOfSaleProductInventaryPK != null) || (this.pointOfSaleProductInventaryPK != null && !this.pointOfSaleProductInventaryPK.equals(other.pointOfSaleProductInventaryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.drtorres.encaja.PointOfSaleProductInventary[ pointOfSaleProductInventaryPK=" + pointOfSaleProductInventaryPK + " ]";
    }
    
}
