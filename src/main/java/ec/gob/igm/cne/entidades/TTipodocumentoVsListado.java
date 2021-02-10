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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "T_TIPODOCUMENTO_VS_LISTADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTipodocumentoVsListado.findAll", query = "SELECT t FROM TTipodocumentoVsListado t")
    , @NamedQuery(name = "TTipodocumentoVsListado.findByIdKit", query = "SELECT t FROM TTipodocumentoVsListado t WHERE t.tTipodocumentoVsListadoPK.idKit = :idKit")
    , @NamedQuery(name = "TTipodocumentoVsListado.findByIdListado", query = "SELECT t FROM TTipodocumentoVsListado t WHERE t.tTipodocumentoVsListadoPK.idListado = :idListado")
    , @NamedQuery(name = "TTipodocumentoVsListado.findByIdEstructura", query = "SELECT t FROM TTipodocumentoVsListado t WHERE t.idEstructura = :idEstructura")
    , @NamedQuery(name = "TTipodocumentoVsListado.findByNumDocEstructura", query = "SELECT t FROM TTipodocumentoVsListado t WHERE t.numDocEstructura = :numDocEstructura")
    , @NamedQuery(name = "TTipodocumentoVsListado.findByOrdenEnKit", query = "SELECT t FROM TTipodocumentoVsListado t WHERE t.ordenEnKit = :ordenEnKit")
    , @NamedQuery(name = "TTipodocumentoVsListado.findByTipodocXParroquia", query = "SELECT t FROM TTipodocumentoVsListado t WHERE t.tipodocXParroquia = :tipodocXParroquia")})
public class TTipodocumentoVsListado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TTipodocumentoVsListadoPK tTipodocumentoVsListadoPK;
    @Column(name = "ID_ESTRUCTURA")
    private BigInteger idEstructura;
    @Column(name = "NUM_DOC_ESTRUCTURA")
    private String numDocEstructura;
    @Column(name = "ORDEN_EN_KIT")
    private BigInteger ordenEnKit;
    @Column(name = "TIPODOC_X_PARROQUIA")
    private String tipodocXParroquia;

    public TTipodocumentoVsListado() {
    }

    public TTipodocumentoVsListado(TTipodocumentoVsListadoPK tTipodocumentoVsListadoPK) {
        this.tTipodocumentoVsListadoPK = tTipodocumentoVsListadoPK;
    }

    public TTipodocumentoVsListado(BigInteger idKit, BigInteger idListado) {
        this.tTipodocumentoVsListadoPK = new TTipodocumentoVsListadoPK(idKit, idListado);
    }

    public TTipodocumentoVsListadoPK getTTipodocumentoVsListadoPK() {
        return tTipodocumentoVsListadoPK;
    }

    public void setTTipodocumentoVsListadoPK(TTipodocumentoVsListadoPK tTipodocumentoVsListadoPK) {
        this.tTipodocumentoVsListadoPK = tTipodocumentoVsListadoPK;
    }

    public BigInteger getIdEstructura() {
        return idEstructura;
    }

    public void setIdEstructura(BigInteger idEstructura) {
        this.idEstructura = idEstructura;
    }

    public String getNumDocEstructura() {
        return numDocEstructura;
    }

    public void setNumDocEstructura(String numDocEstructura) {
        this.numDocEstructura = numDocEstructura;
    }

    public BigInteger getOrdenEnKit() {
        return ordenEnKit;
    }

    public void setOrdenEnKit(BigInteger ordenEnKit) {
        this.ordenEnKit = ordenEnKit;
    }

    public String getTipodocXParroquia() {
        return tipodocXParroquia;
    }

    public void setTipodocXParroquia(String tipodocXParroquia) {
        this.tipodocXParroquia = tipodocXParroquia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tTipodocumentoVsListadoPK != null ? tTipodocumentoVsListadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTipodocumentoVsListado)) {
            return false;
        }
        TTipodocumentoVsListado other = (TTipodocumentoVsListado) object;
        if ((this.tTipodocumentoVsListadoPK == null && other.tTipodocumentoVsListadoPK != null) || (this.tTipodocumentoVsListadoPK != null && !this.tTipodocumentoVsListadoPK.equals(other.tTipodocumentoVsListadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TTipodocumentoVsListado[ tTipodocumentoVsListadoPK=" + tTipodocumentoVsListadoPK + " ]";
    }
    
}
