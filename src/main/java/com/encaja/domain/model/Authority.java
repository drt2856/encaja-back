/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.encaja.domain.model;

import java.io.Serializable;
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
@Table(name = "authority", catalog = "encaja_v1", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Authority.findAll", query = "SELECT a FROM Authority a"),
    @NamedQuery(name = "Authority.findByUsersid", query = "SELECT a FROM Authority a WHERE a.authorityPK.usersid = :usersid"),
    @NamedQuery(name = "Authority.findByAuthority", query = "SELECT a FROM Authority a WHERE a.authorityPK.authority = :authority")})
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AuthorityPK authorityPK;
    @JoinColumn(name = "usersid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Authority() {
    }

    public Authority(AuthorityPK authorityPK) {
        this.authorityPK = authorityPK;
    }

    public Authority(String usersid, String authority) {
        this.authorityPK = new AuthorityPK(usersid, authority);
    }

    public AuthorityPK getAuthorityPK() {
        return authorityPK;
    }

    public void setAuthorityPK(AuthorityPK authorityPK) {
        this.authorityPK = authorityPK;
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
        hash += (authorityPK != null ? authorityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authority)) {
            return false;
        }
        Authority other = (Authority) object;
        if ((this.authorityPK == null && other.authorityPK != null) || (this.authorityPK != null && !this.authorityPK.equals(other.authorityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.drtorres.encaja.Authority[ authorityPK=" + authorityPK + " ]";
    }
    
}
