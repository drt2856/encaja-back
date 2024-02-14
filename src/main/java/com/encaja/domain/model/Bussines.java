/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.encaja.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author drtorres
 */
@Entity
@Data
@Table(name = "bussines", catalog = "encaja_v1", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Bussines.findAll", query = "SELECT b FROM Bussines b"),
    @NamedQuery(name = "Bussines.findById", query = "SELECT b FROM Bussines b WHERE b.id = :id"),
    @NamedQuery(name = "Bussines.findByName", query = "SELECT b FROM Bussines b WHERE b.name = :name"),
    @NamedQuery(name = "Bussines.findByDescription", query = "SELECT b FROM Bussines b WHERE b.description = :description"),
    @NamedQuery(name = "Bussines.findByLicenseFreezeDate", query = "SELECT b FROM Bussines b WHERE b.licenseFreezeDate = :licenseFreezeDate"),
    @NamedQuery(name = "Bussines.findByUrlLogo", query = "SELECT b FROM Bussines b WHERE b.urlLogo = :urlLogo"),
    @NamedQuery(name = "Bussines.findByEmail", query = "SELECT b FROM Bussines b WHERE b.email = :email"),
    @NamedQuery(name = "Bussines.findByPhone", query = "SELECT b FROM Bussines b WHERE b.phone = :phone"),
    @NamedQuery(name = "Bussines.findByAddress", query = "SELECT b FROM Bussines b WHERE b.address = :address")})
public class Bussines implements Serializable {

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
    @Basic(optional = false)
    @Column(name = "license_freeze_date")
    @Temporal(TemporalType.DATE)
    private Date licenseFreezeDate;
    @Column(name = "url_logo")
    private String urlLogo;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bussinesid")
    private List<Product> productList;
    @JoinColumn(name = "ownerid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users ownerid;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bussinesid")
    private List<PointOfSale> pointOfSaleList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bussinesid")
    private List<Warehouse> warehouseList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bussinesid")
    private List<Users> usersList;

    public Bussines() {
    }

    public Bussines(String id) {
        this.id = id;
    }

    public Bussines(String id, String name, Date licenseFreezeDate) {
        this.id = id;
        this.name = name;
        this.licenseFreezeDate = licenseFreezeDate;
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
        if (!(object instanceof Bussines)) {
            return false;
        }
        Bussines other = (Bussines) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.drtorres.encaja.Bussines[ id=" + id + " ]";
    }
    
}
