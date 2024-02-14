/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.encaja.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author drtorres
 */
@Entity
@Table(name = "sale", catalog = "encaja_v1", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Sale.findAll", query = "SELECT s FROM Sale s"),
    @NamedQuery(name = "Sale.findById", query = "SELECT s FROM Sale s WHERE s.id = :id"),
    @NamedQuery(name = "Sale.findByBussinesid", query = "SELECT s FROM Sale s WHERE s.bussinesid = :bussinesid"),
    @NamedQuery(name = "Sale.findByCashier", query = "SELECT s FROM Sale s WHERE s.cashier = :cashier"),
    @NamedQuery(name = "Sale.findByDate", query = "SELECT s FROM Sale s WHERE s.date = :date")})
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "bussinesid")
    private String bussinesid;
    @Basic(optional = false)
    @Column(name = "cashier")
    private String cashier;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "point_of_saleid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PointOfSale pointOfSaleid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sale")
    private List<SaleItem> saleItemList;

    public Sale() {
    }

    public Sale(String id) {
        this.id = id;
    }

    public Sale(String id, String bussinesid, String cashier, Date date) {
        this.id = id;
        this.bussinesid = bussinesid;
        this.cashier = cashier;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBussinesid() {
        return bussinesid;
    }

    public void setBussinesid(String bussinesid) {
        this.bussinesid = bussinesid;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PointOfSale getPointOfSaleid() {
        return pointOfSaleid;
    }

    public void setPointOfSaleid(PointOfSale pointOfSaleid) {
        this.pointOfSaleid = pointOfSaleid;
    }

    public List<SaleItem> getSaleItemList() {
        return saleItemList;
    }

    public void setSaleItemList(List<SaleItem> saleItemList) {
        this.saleItemList = saleItemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sale)) {
            return false;
        }
        Sale other = (Sale) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.drtorres.encaja.Sale[ id=" + id + " ]";
    }
    
}
