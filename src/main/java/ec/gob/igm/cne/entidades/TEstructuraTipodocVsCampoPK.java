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
public class TEstructuraTipodocVsCampoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ID_ESTRUCTURA")
    private BigInteger idEstructura;
    @Basic(optional = false)
    @Column(name = "ID_CAMPO")
    private BigInteger idCampo;

    public TEstructuraTipodocVsCampoPK() {
    }

    public TEstructuraTipodocVsCampoPK(BigInteger idEstructura, BigInteger idCampo) {
        this.idEstructura = idEstructura;
        this.idCampo = idCampo;
    }

    public BigInteger getIdEstructura() {
        return idEstructura;
    }

    public void setIdEstructura(BigInteger idEstructura) {
        this.idEstructura = idEstructura;
    }

    public BigInteger getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(BigInteger idCampo) {
        this.idCampo = idCampo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstructura != null ? idEstructura.hashCode() : 0);
        hash += (idCampo != null ? idCampo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TEstructuraTipodocVsCampoPK)) {
            return false;
        }
        TEstructuraTipodocVsCampoPK other = (TEstructuraTipodocVsCampoPK) object;
        if ((this.idEstructura == null && other.idEstructura != null) || (this.idEstructura != null && !this.idEstructura.equals(other.idEstructura))) {
            return false;
        }
        if ((this.idCampo == null && other.idCampo != null) || (this.idCampo != null && !this.idCampo.equals(other.idCampo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TEstructuraTipodocVsCampoPK[ idEstructura=" + idEstructura + ", idCampo=" + idCampo + " ]";
    }
    
}
