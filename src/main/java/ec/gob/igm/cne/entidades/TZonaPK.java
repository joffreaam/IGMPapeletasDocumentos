/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author desarrollo
 */
@Embeddable
public class TZonaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ID_ZONA")
    private int idZona;
    @Basic(optional = false)
    @Column(name = "ID_PARROQUIA")
    private int idParroquia;

    public TZonaPK() {
    }

    public TZonaPK(int idZona, int idParroquia) {
        this.idZona = idZona;
        this.idParroquia = idParroquia;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public int getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(int idParroquia) {
        this.idParroquia = idParroquia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idZona;
        hash += (int) idParroquia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TZonaPK)) {
            return false;
        }
        TZonaPK other = (TZonaPK) object;
        if (this.idZona != other.idZona) {
            return false;
        }
        if (this.idParroquia != other.idParroquia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TZonaPK[ idZona=" + idZona + ", idParroquia=" + idParroquia + " ]";
    }
    
}
