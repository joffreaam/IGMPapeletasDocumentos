/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "T_TIPO_DOCUMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTipoDocumento.findAll", query = "SELECT t FROM TTipoDocumento t")
    , @NamedQuery(name = "TTipoDocumento.findByIdKit", query = "SELECT t FROM TTipoDocumento t WHERE t.idKit = :idKit")
    , @NamedQuery(name = "TTipoDocumento.findByDescripcion", query = "SELECT t FROM TTipoDocumento t WHERE t.descripcion = :descripcion")})
public class TTipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_KIT")
    private BigDecimal idKit;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tTipoDocumento")
    private List<TOrdenDocumento> tOrdenDocumentoList;

    public TTipoDocumento() {
    }

    public TTipoDocumento(BigDecimal idKit) {
        this.idKit = idKit;
    }

    public BigDecimal getIdKit() {
        return idKit;
    }

    public void setIdKit(BigDecimal idKit) {
        this.idKit = idKit;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<TOrdenDocumento> getTOrdenDocumentoList() {
        return tOrdenDocumentoList;
    }

    public void setTOrdenDocumentoList(List<TOrdenDocumento> tOrdenDocumentoList) {
        this.tOrdenDocumentoList = tOrdenDocumentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKit != null ? idKit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTipoDocumento)) {
            return false;
        }
        TTipoDocumento other = (TTipoDocumento) object;
        if ((this.idKit == null && other.idKit != null) || (this.idKit != null && !this.idKit.equals(other.idKit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idKit+"-"+descripcion;
    }
    
}
