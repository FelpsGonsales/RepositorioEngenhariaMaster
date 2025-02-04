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
@Table(name = "tecnico_enfermagem")
@NamedQueries({
    @NamedQuery(name = "tecnico_enfermagem.findAll", query = "SELECT m FROM tecnico_enfermagem m")})
public class tecnico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "identificador")
    private Integer identificador;
    @Basic(optional = false)
    @Column(name = "nome_tecnico")
    private String nome_tecnico;
    @Basic(optional = false)
    @Column(name = "area_tecnico")
    private String area_tecnico;

    public tecnico() {
    }

    public tecnico(Integer identificador) {
        this.identificador = identificador;
    }

    public tecnico(Integer identificador, String nome_tecnico, String area_tecnico) {
        this.identificador = identificador;
        this.nome_tecnico = nome_tecnico;
        this.area_tecnico = area_tecnico;
    }

    public Integer getidentificador() {
        return identificador;
    }

    public void setidentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getnome_tecnico() {
        return nome_tecnico;
    }

    public void setnome_tecnico(String nome_tecnico) {
        this.nome_tecnico = nome_tecnico;
    }

    public String getarea_tecnico() {
        return area_tecnico;
    }

    public void setarea_tecnico(String area_tecnico) {
        this.area_tecnico = area_tecnico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificador != null ? identificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof tecnico)) {
            return false;
        }
        tecnico other = (tecnico) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
          ConversorDeDatas cd = new ConversorDeDatas();
        return identificador + ";" + nome_tecnico + ";" + area_tecnico;
    }
    
}

