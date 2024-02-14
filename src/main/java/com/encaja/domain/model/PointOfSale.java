/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.encaja.domain.model;

import java.io.Serializable;
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

/**
 *
 * @author drtorres
 */
@Entity
@Table(name = "point_of_sale", catalog = "encaja_v1", schema = "public")
@NamedQueries({
    @NamedQuery(name = "PointOfSale.findAll", query = "SELECT p FROM PointOfSale p"),
    @NamedQuery(name = "PointOfSale.findById", query = "SELECT p FROM PointOfSale p WHERE p.id = :id"),
    @NamedQuery(name = "PointOfSale.findByName", query = "SELECT p FROM PointOfSale p WHERE p.name = :name"),
    @NamedQuery(name = "PointOfSale.findByDescription", query = "SELECT p FROM PointOfSale p WHERE p.description = :description"),
    @NamedQuery(name = "PointOfSale.findByUrlLogo", query = "SELECT p FROM PointOfSale p WHERE p.urlLogo = :urlLogo"),
    @NamedQuery(name = "PointOfSale.findByEmail", query = "SELECT p FROM PointOfSale p WHERE p.email = :email"),
    @NamedQuery(name = "PointOfSale.findByPhone", query = "SELECT p FROM PointOfSale p WHERE p.phone = :phone"),
    @NamedQuery(name = "PointOfSale.findByAddres", query = "SELECT p FROM PointOfSale p WHERE p.addres = :addres")})
public class PointOfSale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "url_logo")
    private String urlLogo;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "addres")
    private String addres;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pointOfSaleid")
    private List<Sale> saleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pointOfSale")
    private List<UserPointOfSale> userPointOfSaleList;
    @JoinColumn(name = "bussinesid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bussines bussinesid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pointOfSale")
    private List<PointOfSaleProductInventory> pointOfSaleProductInventaryList;

    public PointOfSale() {
    }

    public PointOfSale(String id) {
        this.id = id;
    }

    public PointOfSale(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public List<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }

    public List<UserPointOfSale> getUserPointOfSaleList() {
        return userPointOfSaleList;
    }

    public void setUserPointOfSaleList(List<UserPointOfSale> userPointOfSaleList) {
        this.userPointOfSaleList = userPointOfSaleList;
    }

    public Bussines getBussinesid() {
        return bussinesid;
    }

    public void setBussinesid(Bussines bussinesid) {
        this.bussinesid = bussinesid;
    }

    public List<PointOfSaleProductInventory> getPointOfSaleProductInventaryList() {
        return pointOfSaleProductInventaryList;
    }

    public void setPointOfSaleProductInventaryList(List<PointOfSaleProductInventory> pointOfSaleProductInventaryList) {
        this.pointOfSaleProductInventaryList = pointOfSaleProductInventaryList;
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
        if (!(object instanceof PointOfSale)) {
            return false;
        }
        PointOfSale other = (PointOfSale) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.drtorres.encaja.PointOfSale[ id=" + id + " ]";
    }
    
}
