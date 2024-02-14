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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author drtorres
 */
@Entity
@Data
@Table(name = "warehouse", catalog = "encaja_v1", schema = "public")

public class Warehouse implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouse")
    private List<StockWareHouse> stockWareHouseList;
    @JoinColumn(name = "bussinesid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bussines bussinesid;

    public Warehouse() {
    }

    public Warehouse(String id) {
        this.id = id;
    }

    public Warehouse(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Warehouse(String id, String name, String description, String urlLogo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.urlLogo = urlLogo;
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
        if (!(object instanceof Warehouse)) {
            return false;
        }
        Warehouse other = (Warehouse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.drtorres.encaja.Warehouse[ id=" + id + " ]";
    }
    
}
