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
public class PointOfSaleProductInventoryPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "point_of_saleid")
    private String pointOfSaleid;
    @Basic(optional = false)
    @Column(name = "productid")
    private String productid;

    public PointOfSaleProductInventoryPK() {
    }

    public PointOfSaleProductInventoryPK(String pointOfSaleid, String productid) {
        this.pointOfSaleid = pointOfSaleid;
        this.productid = productid;
    }

    public String getPointOfSaleid() {
        return pointOfSaleid;
    }

    public void setPointOfSaleid(String pointOfSaleid) {
        this.pointOfSaleid = pointOfSaleid;
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
        hash += (pointOfSaleid != null ? pointOfSaleid.hashCode() : 0);
        hash += (productid != null ? productid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PointOfSaleProductInventoryPK)) {
            return false;
        }
        PointOfSaleProductInventoryPK other = (PointOfSaleProductInventoryPK) object;
        if ((this.pointOfSaleid == null && other.pointOfSaleid != null) || (this.pointOfSaleid != null && !this.pointOfSaleid.equals(other.pointOfSaleid))) {
            return false;
        }
        if ((this.productid == null && other.productid != null) || (this.productid != null && !this.productid.equals(other.productid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.drtorres.encaja.PointOfSaleProductInventaryPK[ pointOfSaleid=" + pointOfSaleid + ", productid=" + productid + " ]";
    }
    
}
