/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author desarrollo
 */
@Embeddable
public class TOrdenDocumentoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ID_ORDEN_DOCUMENTO")
    private BigInteger idOrdenDocumento;
    @Basic(optional = false)
    @Column(name = "ID_KIT")
    private BigInteger idKit;
    @Basic(optional = false)
    @Column(name = "ID_LISTADO")
    private BigInteger idListado;

    public TOrdenDocumentoPK() {
    }

    public TOrdenDocumentoPK(BigInteger idOrdenDocumento, BigInteger idKit, BigInteger idListado) {
        this.idOrdenDocumento = idOrdenDocumento;
        this.idKit = idKit;
        this.idListado = idListado;
    }

    public BigInteger getIdOrdenDocumento() {
        return idOrdenDocumento;
    }

    public void setIdOrdenDocumento(BigInteger idOrdenDocumento) {
        this.idOrdenDocumento = idOrdenDocumento;
    }

    public BigInteger getIdKit() {
        return idKit;
    }

    public void setIdKit(BigInteger idKit) {
        this.idKit = idKit;
    }

    public BigInteger getIdListado() {
        return idListado;
    }

    public void setIdListado(BigInteger idListado) {
        this.idListado = idListado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdenDocumento != null ? idOrdenDocumento.hashCode() : 0);
        hash += (idKit != null ? idKit.hashCode() : 0);
        hash += (idListado != null ? idListado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TOrdenDocumentoPK)) {
            return false;
        }
        TOrdenDocumentoPK other = (TOrdenDocumentoPK) object;
        if ((this.idOrdenDocumento == null && other.idOrdenDocumento != null) || (this.idOrdenDocumento != null && !this.idOrdenDocumento.equals(other.idOrdenDocumento))) {
            return false;
        }
        if ((this.idKit == null && other.idKit != null) || (this.idKit != null && !this.idKit.equals(other.idKit))) {
            return false;
        }
        if ((this.idListado == null && other.idListado != null) || (this.idListado != null && !this.idListado.equals(other.idListado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TOrdenDocumentoPK[ idOrdenDocumento=" + idOrdenDocumento + ", idKit=" + idKit + ", idListado=" + idListado + " ]";
    }
    
}
