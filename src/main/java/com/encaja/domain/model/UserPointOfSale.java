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
@Table(name = "user_point_of_sale", catalog = "encaja_v1", schema = "public")
@NamedQueries({
    @NamedQuery(name = "UserPointOfSale.findAll", query = "SELECT u FROM UserPointOfSale u"),
    @NamedQuery(name = "UserPointOfSale.findByPointOfSaleid", query = "SELECT u FROM UserPointOfSale u WHERE u.userPointOfSalePK.pointOfSaleid = :pointOfSaleid"),
    @NamedQuery(name = "UserPointOfSale.findByUsersid", query = "SELECT u FROM UserPointOfSale u WHERE u.userPointOfSalePK.usersid = :usersid"),
    @NamedQuery(name = "UserPointOfSale.findByRole", query = "SELECT u FROM UserPointOfSale u WHERE u.role = :role")})
public class UserPointOfSale implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserPointOfSalePK userPointOfSalePK;
    @Basic(optional = false)
    @Column(name = "role")
    private String role;
    @JoinColumn(name = "point_of_saleid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PointOfSale pointOfSale;
    @JoinColumn(name = "usersid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public UserPointOfSale() {
    }

    public UserPointOfSale(UserPointOfSalePK userPointOfSalePK) {
        this.userPointOfSalePK = userPointOfSalePK;
    }

    public UserPointOfSale(UserPointOfSalePK userPointOfSalePK, String role) {
        this.userPointOfSalePK = userPointOfSalePK;
        this.role = role;
    }

    public UserPointOfSale(String pointOfSaleid, String usersid) {
        this.userPointOfSalePK = new UserPointOfSalePK(pointOfSaleid, usersid);
    }

    public UserPointOfSalePK getUserPointOfSalePK() {
        return userPointOfSalePK;
    }

    public void setUserPointOfSalePK(UserPointOfSalePK userPointOfSalePK) {
        this.userPointOfSalePK = userPointOfSalePK;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public PointOfSale getPointOfSale() {
        return pointOfSale;
    }

    public void setPointOfSale(PointOfSale pointOfSale) {
        this.pointOfSale = pointOfSale;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userPointOfSalePK != null ? userPointOfSalePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserPointOfSale)) {
            return false;
        }
        UserPointOfSale other = (UserPointOfSale) object;
        if ((this.userPointOfSalePK == null && other.userPointOfSalePK != null) || (this.userPointOfSalePK != null && !this.userPointOfSalePK.equals(other.userPointOfSalePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.drtorres.encaja.UserPointOfSale[ userPointOfSalePK=" + userPointOfSalePK + " ]";
    }
    
}
