/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "T_TIPO_ESTRUCTURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTipoEstructura.findAll", query = "SELECT t FROM TTipoEstructura t")
    , @NamedQuery(name = "TTipoEstructura.findByIdEstructura", query = "SELECT t FROM TTipoEstructura t WHERE t.idEstructura = :idEstructura")
    , @NamedQuery(name = "TTipoEstructura.findByDescripcion", query = "SELECT t FROM TTipoEstructura t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TTipoEstructura.findByLongitud", query = "SELECT t FROM TTipoEstructura t WHERE t.longitud = :longitud")})
public class TTipoEstructura implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ESTRUCTURA")
    private BigDecimal idEstructura;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "LONGITUD")
    private BigInteger longitud;

    public TTipoEstructura() {
    }

    public TTipoEstructura(BigDecimal idEstructura) {
        this.idEstructura = idEstructura;
    }

    public BigDecimal getIdEstructura() {
        return idEstructura;
    }

    public void setIdEstructura(BigDecimal idEstructura) {
        this.idEstructura = idEstructura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigInteger getLongitud() {
        return longitud;
    }

    public void setLongitud(BigInteger longitud) {
        this.longitud = longitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstructura != null ? idEstructura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTipoEstructura)) {
            return false;
        }
        TTipoEstructura other = (TTipoEstructura) object;
        if ((this.idEstructura == null && other.idEstructura != null) || (this.idEstructura != null && !this.idEstructura.equals(other.idEstructura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TTipoEstructura[ idEstructura=" + idEstructura + " ]";
    }
    
}
