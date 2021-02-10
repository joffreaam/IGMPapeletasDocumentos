/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "T_ORDEN_DOCUMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TOrdenDocumento.findAll", query = "SELECT t FROM TOrdenDocumento t")
    , @NamedQuery(name = "TOrdenDocumento.findByIdOrdenDocumento", query = "SELECT t FROM TOrdenDocumento t WHERE t.tOrdenDocumentoPK.idOrdenDocumento = :idOrdenDocumento")
    , @NamedQuery(name = "TOrdenDocumento.findByIdKit", query = "SELECT t FROM TOrdenDocumento t WHERE t.tOrdenDocumentoPK.idKit = :idKit")
    , @NamedQuery(name = "TOrdenDocumento.findByIdListado", query = "SELECT t FROM TOrdenDocumento t WHERE t.tOrdenDocumentoPK.idListado = :idListado")
    , @NamedQuery(name = "TOrdenDocumento.findByOrden", query = "SELECT t FROM TOrdenDocumento t WHERE t.orden = :orden")
    , @NamedQuery(name = "TOrdenDocumento.findByTipo", query = "SELECT t FROM TOrdenDocumento t WHERE t.tipo = :tipo")})
public class TOrdenDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TOrdenDocumentoPK tOrdenDocumentoPK;
    @Column(name = "ORDEN")
    private BigInteger orden;
    @Column(name = "TIPO")
    private BigInteger tipo;
    @JoinColumn(name = "ID_LISTADO", referencedColumnName = "ID_LISTADO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TListadoDocumentos tListadoDocumentos;
    @JoinColumn(name = "ID_KIT", referencedColumnName = "ID_KIT", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TTipoDocumento tTipoDocumento;

    public TOrdenDocumento() {
    }

    public TOrdenDocumento(TOrdenDocumentoPK tOrdenDocumentoPK) {
        this.tOrdenDocumentoPK = tOrdenDocumentoPK;
    }

    public TOrdenDocumento(BigInteger idOrdenDocumento, BigInteger idKit, BigInteger idListado) {
        this.tOrdenDocumentoPK = new TOrdenDocumentoPK(idOrdenDocumento, idKit, idListado);
    }

    public TOrdenDocumentoPK getTOrdenDocumentoPK() {
        return tOrdenDocumentoPK;
    }

    public void setTOrdenDocumentoPK(TOrdenDocumentoPK tOrdenDocumentoPK) {
        this.tOrdenDocumentoPK = tOrdenDocumentoPK;
    }

    public BigInteger getOrden() {
        return orden;
    }

    public void setOrden(BigInteger orden) {
        this.orden = orden;
    }

    public BigInteger getTipo() {
        return tipo;
    }

    public void setTipo(BigInteger tipo) {
        this.tipo = tipo;
    }

    public TListadoDocumentos getTListadoDocumentos() {
        return tListadoDocumentos;
    }

    public void setTListadoDocumentos(TListadoDocumentos tListadoDocumentos) {
        this.tListadoDocumentos = tListadoDocumentos;
    }

    public TTipoDocumento getTTipoDocumento() {
        return tTipoDocumento;
    }

    public void setTTipoDocumento(TTipoDocumento tTipoDocumento) {
        this.tTipoDocumento = tTipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tOrdenDocumentoPK != null ? tOrdenDocumentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TOrdenDocumento)) {
            return false;
        }
        TOrdenDocumento other = (TOrdenDocumento) object;
        if ((this.tOrdenDocumentoPK == null && other.tOrdenDocumentoPK != null) || (this.tOrdenDocumentoPK != null && !this.tOrdenDocumentoPK.equals(other.tOrdenDocumentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TOrdenDocumento[ tOrdenDocumentoPK=" + tOrdenDocumentoPK + " ]";
    }
    
}
