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
@Table(name = "T_ESTRUCTURA_CAMPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TEstructuraCampo.findAll", query = "SELECT t FROM TEstructuraCampo t")
    , @NamedQuery(name = "TEstructuraCampo.findByIdCampo", query = "SELECT t FROM TEstructuraCampo t WHERE t.idCampo = :idCampo")
    , @NamedQuery(name = "TEstructuraCampo.findByDescripcion", query = "SELECT t FROM TEstructuraCampo t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TEstructuraCampo.findByLongitud", query = "SELECT t FROM TEstructuraCampo t WHERE t.longitud = :longitud")
    , @NamedQuery(name = "TEstructuraCampo.findByValorDefault", query = "SELECT t FROM TEstructuraCampo t WHERE t.valorDefault = :valorDefault")})
public class TEstructuraCampo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CAMPO")
    private BigDecimal idCampo;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "LONGITUD")
    private BigInteger longitud;
    @Column(name = "VALOR_DEFAULT")
    private String valorDefault;

    public TEstructuraCampo() {
    }

    public TEstructuraCampo(BigDecimal idCampo) {
        this.idCampo = idCampo;
    }

    public BigDecimal getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(BigDecimal idCampo) {
        this.idCampo = idCampo;
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

    public String getValorDefault() {
        return valorDefault;
    }

    public void setValorDefault(String valorDefault) {
        this.valorDefault = valorDefault;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCampo != null ? idCampo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TEstructuraCampo)) {
            return false;
        }
        TEstructuraCampo other = (TEstructuraCampo) object;
        if ((this.idCampo == null && other.idCampo != null) || (this.idCampo != null && !this.idCampo.equals(other.idCampo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TEstructuraCampo[ idCampo=" + idCampo + " ]";
    }
    
}
