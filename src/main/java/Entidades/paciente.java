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
@Table(name = "paciente")
@NamedQueries({
    @NamedQuery(name = "paciente.findAll", query = "SELECT m FROM paciente m")})
public class paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idpaciente")
    private Integer idpaciente;
    @Basic(optional = false)
    @Column(name = "nome_paciente")
    private String nome_paciente;
    @Basic(optional = false)
    @Column(name = "idade")
    private String idade;

    public paciente() {
    }

    public paciente(Integer idpaciente) {
        this.idpaciente = idpaciente;
    }

    public paciente(Integer idpaciente, String nome_paciente, String idade) {
        this.idpaciente = idpaciente;
        this.nome_paciente = nome_paciente;
        this.idade = idade;
    }

    public Integer getidpaciente() {
        return idpaciente;
    }

    public void setidpaciente(Integer idpaciente) {
        this.idpaciente = idpaciente;
    }

    public String getnome_paciente() {
        return nome_paciente;
    }

    public void setnome_paciente(String nome_paciente) {
        this.nome_paciente = nome_paciente;
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
        hash += (idpaciente != null ? idpaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof paciente)) {
            return false;
        }
        paciente other = (paciente) object;
        if ((this.idpaciente == null && other.idpaciente != null) || (this.idpaciente != null && !this.idpaciente.equals(other.idpaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
          ConversorDeDatas cd = new ConversorDeDatas();
        return idpaciente + ";" + nome_paciente + ";" + idade;
    }
    
}

