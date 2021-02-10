/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "T_OPERADORES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TOperadores.findAll", query = "SELECT t FROM TOperadores t")
    , @NamedQuery(name = "TOperadores.findByIdOperador", query = "SELECT t FROM TOperadores t WHERE t.idOperador = :idOperador")
    , @NamedQuery(name = "TOperadores.findByOperador", query = "SELECT t FROM TOperadores t WHERE t.operador = :operador")
    , @NamedQuery(name = "TOperadores.findByTipo", query = "SELECT t FROM TOperadores t WHERE t.tipo = :tipo")})
public class TOperadores implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_OPERADOR")
    private BigDecimal idOperador;
    @Column(name = "OPERADOR")
    private String operador;
    @Column(name = "TIPO")
    private String tipo;

    public TOperadores() {
    }

    public TOperadores(BigDecimal idOperador) {
        this.idOperador = idOperador;
    }

    public BigDecimal getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(BigDecimal idOperador) {
        this.idOperador = idOperador;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperador != null ? idOperador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TOperadores)) {
            return false;
        }
        TOperadores other = (TOperadores) object;
        if ((this.idOperador == null && other.idOperador != null) || (this.idOperador != null && !this.idOperador.equals(other.idOperador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TOperadores[ idOperador=" + idOperador + " ]";
    }
    
}
