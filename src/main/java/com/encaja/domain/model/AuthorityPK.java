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
public class AuthorityPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "usersid")
    private String usersid;
    @Basic(optional = false)
    @Column(name = "authority")
    private String authority;

    public AuthorityPK() {
    }

    public AuthorityPK(String usersid, String authority) {
        this.usersid = usersid;
        this.authority = authority;
    }

    public String getUsersid() {
        return usersid;
    }

    public void setUsersid(String usersid) {
        this.usersid = usersid;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usersid != null ? usersid.hashCode() : 0);
        hash += (authority != null ? authority.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthorityPK)) {
            return false;
        }
        AuthorityPK other = (AuthorityPK) object;
        if ((this.usersid == null && other.usersid != null) || (this.usersid != null && !this.usersid.equals(other.usersid))) {
            return false;
        }
        if ((this.authority == null && other.authority != null) || (this.authority != null && !this.authority.equals(other.authority))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.drtorres.encaja.AuthorityPK[ usersid=" + usersid + ", authority=" + authority + " ]";
    }
    
}
