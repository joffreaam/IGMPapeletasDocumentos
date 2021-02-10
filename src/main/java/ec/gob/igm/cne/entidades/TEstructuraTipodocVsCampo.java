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
@Table(name = "T_ESTRUCTURA_TIPODOC_VS_CAMPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TEstructuraTipodocVsCampo.findAll", query = "SELECT t FROM TEstructuraTipodocVsCampo t")
    , @NamedQuery(name = "TEstructuraTipodocVsCampo.findByIdEstructura", query = "SELECT t FROM TEstructuraTipodocVsCampo t WHERE t.tEstructuraTipodocVsCampoPK.idEstructura = :idEstructura")
    , @NamedQuery(name = "TEstructuraTipodocVsCampo.findByIdCampo", query = "SELECT t FROM TEstructuraTipodocVsCampo t WHERE t.tEstructuraTipodocVsCampoPK.idCampo = :idCampo")
    , @NamedQuery(name = "TEstructuraTipodocVsCampo.findByOrden", query = "SELECT t FROM TEstructuraTipodocVsCampo t WHERE t.orden = :orden")})
public class TEstructuraTipodocVsCampo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TEstructuraTipodocVsCampoPK tEstructuraTipodocVsCampoPK;
    @Column(name = "ORDEN")
    private BigInteger orden;

    public TEstructuraTipodocVsCampo() {
    }

    public TEstructuraTipodocVsCampo(TEstructuraTipodocVsCampoPK tEstructuraTipodocVsCampoPK) {
        this.tEstructuraTipodocVsCampoPK = tEstructuraTipodocVsCampoPK;
    }

    public TEstructuraTipodocVsCampo(BigInteger idEstructura, BigInteger idCampo) {
        this.tEstructuraTipodocVsCampoPK = new TEstructuraTipodocVsCampoPK(idEstructura, idCampo);
    }

    public TEstructuraTipodocVsCampoPK getTEstructuraTipodocVsCampoPK() {
        return tEstructuraTipodocVsCampoPK;
    }

    public void setTEstructuraTipodocVsCampoPK(TEstructuraTipodocVsCampoPK tEstructuraTipodocVsCampoPK) {
        this.tEstructuraTipodocVsCampoPK = tEstructuraTipodocVsCampoPK;
    }

    public BigInteger getOrden() {
        return orden;
    }

    public void setOrden(BigInteger orden) {
        this.orden = orden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tEstructuraTipodocVsCampoPK != null ? tEstructuraTipodocVsCampoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TEstructuraTipodocVsCampo)) {
            return false;
        }
        TEstructuraTipodocVsCampo other = (TEstructuraTipodocVsCampo) object;
        if ((this.tEstructuraTipodocVsCampoPK == null && other.tEstructuraTipodocVsCampoPK != null) || (this.tEstructuraTipodocVsCampoPK != null && !this.tEstructuraTipodocVsCampoPK.equals(other.tEstructuraTipodocVsCampoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TEstructuraTipodocVsCampo[ tEstructuraTipodocVsCampoPK=" + tEstructuraTipodocVsCampoPK + " ]";
    }
    
}
