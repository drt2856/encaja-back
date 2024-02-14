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
public class UserPointOfSalePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "point_of_saleid")
    private String pointOfSaleid;
    @Basic(optional = false)
    @Column(name = "usersid")
    private String usersid;

    public UserPointOfSalePK() {
    }

    public UserPointOfSalePK(String pointOfSaleid, String usersid) {
        this.pointOfSaleid = pointOfSaleid;
        this.usersid = usersid;
    }

    public String getPointOfSaleid() {
        return pointOfSaleid;
    }

    public void setPointOfSaleid(String pointOfSaleid) {
        this.pointOfSaleid = pointOfSaleid;
    }

    public String getUsersid() {
        return usersid;
    }

    public void setUsersid(String usersid) {
        this.usersid = usersid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pointOfSaleid != null ? pointOfSaleid.hashCode() : 0);
        hash += (usersid != null ? usersid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserPointOfSalePK)) {
            return false;
        }
        UserPointOfSalePK other = (UserPointOfSalePK) object;
        if ((this.pointOfSaleid == null && other.pointOfSaleid != null) || (this.pointOfSaleid != null && !this.pointOfSaleid.equals(other.pointOfSaleid))) {
            return false;
        }
        if ((this.usersid == null && other.usersid != null) || (this.usersid != null && !this.usersid.equals(other.usersid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.drtorres.encaja.UserPointOfSalePK[ pointOfSaleid=" + pointOfSaleid + ", usersid=" + usersid + " ]";
    }
    
}
