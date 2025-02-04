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
@Table(name = "enfermeira")
@NamedQueries({
    @NamedQuery(name = "enfermeira.findAll", query = "SELECT n FROM enfermeira n")})
public class enfermeira implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cip")
    private Integer cip;
    @Basic(optional = false)
    @Column(name = "nome_enfermeira")
    private String nome_enfermeira;
    @Basic(optional = false)
    @Column(name = "idade")
    private String idade;

    public enfermeira() {
    }

    public enfermeira(Integer cip) {
        this.cip = cip;
    }

    public enfermeira(Integer cip, String nome_enfermeira, String idade) {
        this.cip = cip;
        this.nome_enfermeira = nome_enfermeira;
        this.idade = idade;
    }

    public Integer getcip() {
        return cip;
    }

    public void setcip(Integer cip) {
        this.cip = cip;
    }

    public String getnome_enfermeira() {
        return nome_enfermeira;
    }

    public void setnome_enfermeira(String nome_enfermeira) {
        this.nome_enfermeira = nome_enfermeira;
    }

    public String getidade() {
        return idade;
    }

    public void setidade(String idade) {
        this.idade = idade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cip != null ? cip.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof enfermeira)) {
            return false;
        }
        enfermeira other = (enfermeira) object;
        if ((this.cip == null && other.cip != null) || (this.cip != null && !this.cip.equals(other.cip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
          ConversorDeDatas cd = new ConversorDeDatas();
        return cip + ";" + nome_enfermeira + ";" + idade;
    }
    
}

