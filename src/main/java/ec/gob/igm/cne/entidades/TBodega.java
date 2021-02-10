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
@Table(name = "T_BODEGA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TBodega.findAll", query = "SELECT t FROM TBodega t")
    , @NamedQuery(name = "TBodega.findByIdbodega", query = "SELECT t FROM TBodega t WHERE t.idbodega = :idbodega")
    , @NamedQuery(name = "TBodega.findByDescripcion", query = "SELECT t FROM TBodega t WHERE t.descripcion = :descripcion")})
public class TBodega implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDBODEGA")
    private BigDecimal idbodega;
    @Column(name = "DESCRIPCION")
    private String descripcion;

    public TBodega() {
    }

    public TBodega(BigDecimal idbodega) {
        this.idbodega = idbodega;
    }

    public BigDecimal getIdbodega() {
        return idbodega;
    }

    public void setIdbodega(BigDecimal idbodega) {
        this.idbodega = idbodega;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbodega != null ? idbodega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TBodega)) {
            return false;
        }
        TBodega other = (TBodega) object;
        if ((this.idbodega == null && other.idbodega != null) || (this.idbodega != null && !this.idbodega.equals(other.idbodega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idbodega + "-" + descripcion;
    }
    
}
