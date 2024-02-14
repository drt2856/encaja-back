/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.encaja.domain.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author drtorres
 */
@Entity
@Data
@Table(name = "product", catalog = "encaja_v1", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description"),
    @NamedQuery(name = "Product.findByMediaUrl", query = "SELECT p FROM Product p WHERE p.mediaUrl = :mediaUrl"),
    @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
    @NamedQuery(name = "Product.findByPublic1", query = "SELECT p FROM Product p WHERE p.public1 = :public1")})
public class Product implements Serializable {

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
    @Column(name = "media_url")
    private String mediaUrl;
    @Basic(optional = false)
    @Column(name = "price")
    private float price;
    @Basic(optional = false)
    @Column(name = "public")
    private Boolean public1;
    @Basic(optional = true)
    @Column(name = "cost")
    private Float cost;
    @Column(name = "tags")
    private List<String> tags;

    @JoinColumn(name = "bussinesid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bussines bussinesid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<StockWareHouse> stockWareHouseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<SaleItem> saleItemList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<PointOfSaleProductInventory> pointOfSaleProductInventaryList;

    public Product() {
    }

    public Product(String id) {
        this.id = id;
    }

    public Product(String id, String name, String description, String mediaUrl, float price, Boolean public1, float cost, List<String> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.mediaUrl = mediaUrl;
        this.price = price;
        this.public1 = public1;
        this.cost = cost;
        this.tags = tags;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.drtorres.encaja.Product[ id=" + id + " ]";
    }
    
}
