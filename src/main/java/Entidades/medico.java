/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import myUtil.ConversorDeDatas;

/**
 *
 * @author Gonsales
 */
@Entity
@Table(name = "medico")
@NamedQueries({
    @NamedQuery(name = "medico.findAll", query = "SELECT m FROM medico m")})
public class medico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "crm")
    private Integer crm;
    @Basic(optional = false)
    @Column(name = "nome_medico")
    private String nome_medico;
    @Basic(optional = false)
    @Column(name = "area")
    private String area;

    public medico() {
    }

    public medico(Integer crm) {
        this.crm = crm;
    }

    public medico(Integer crm, String nome_medico, String area) {
        this.crm = crm;
        this.nome_medico = nome_medico;
        this.area = area;
    }

    public Integer getcrm() {
        return crm;
    }

    public void setcrm(Integer crm) {
        this.crm = crm;
    }

    public String getnome_medico() {
        return nome_medico;
    }

    public void setnome_medico(String nome_medico) {
        this.nome_medico = nome_medico;
    }

    public String getarea() {
        return area;
    }

    public void setarea(String area) {
        this.area = area;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crm != null ? crm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof medico)) {
            return false;
        }
        medico other = (medico) object;
        if ((this.crm == null && other.crm != null) || (this.crm != null && !this.crm.equals(other.crm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
          ConversorDeDatas cd = new ConversorDeDatas();
        return crm + ";" + nome_medico + ";" + area;
    }
    
}

