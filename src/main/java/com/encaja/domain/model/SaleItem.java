/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.encaja.domain.model;

import java.io.Serializable;
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
@Table(name = "sale_item", catalog = "encaja_v1", schema = "public")
@NamedQueries({
    @NamedQuery(name = "SaleItem.findAll", query = "SELECT s FROM SaleItem s"),
    @NamedQuery(name = "SaleItem.findBySaleid", query = "SELECT s FROM SaleItem s WHERE s.saleItemPK.saleid = :saleid"),
    @NamedQuery(name = "SaleItem.findByProductid", query = "SELECT s FROM SaleItem s WHERE s.saleItemPK.productid = :productid"),
    @NamedQuery(name = "SaleItem.findByQuantity", query = "SELECT s FROM SaleItem s WHERE s.quantity = :quantity"),
    @NamedQuery(name = "SaleItem.findBySalePrice", query = "SELECT s FROM SaleItem s WHERE s.salePrice = :salePrice")})
public class SaleItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SaleItemPK saleItemPK;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @Column(name = "sale_price")
    private float salePrice;
    @JoinColumn(name = "productid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;
    @JoinColumn(name = "saleid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sale sale;

    public SaleItem() {
    }

    public SaleItem(SaleItemPK saleItemPK) {
        this.saleItemPK = saleItemPK;
    }

    public SaleItem(SaleItemPK saleItemPK, int quantity, float salePrice) {
        this.saleItemPK = saleItemPK;
        this.quantity = quantity;
        this.salePrice = salePrice;
    }

    public SaleItem(String saleid, String productid) {
        this.saleItemPK = new SaleItemPK(saleid, productid);
    }

    public SaleItemPK getSaleItemPK() {
        return saleItemPK;
    }

    public void setSaleItemPK(SaleItemPK saleItemPK) {
        this.saleItemPK = saleItemPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (saleItemPK != null ? saleItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaleItem)) {
            return false;
        }
        SaleItem other = (SaleItem) object;
        if ((this.saleItemPK == null && other.saleItemPK != null) || (this.saleItemPK != null && !this.saleItemPK.equals(other.saleItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.drtorres.encaja.SaleItem[ saleItemPK=" + saleItemPK + " ]";
    }
    
}
