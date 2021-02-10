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
public class TCircunscripcionCantonPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ID_PROVINCIA")
    private int idProvincia;
    @Basic(optional = false)
    @Column(name = "ID_CANTON")
    private int idCanton;
    @Basic(optional = false)
    @Column(name = "ID_CIRCUNSCRIPCION")
    private int idCircunscripcion;

    public TCircunscripcionCantonPK() {
    }

    public TCircunscripcionCantonPK(int idProvincia, int idCanton, int idCircunscripcion) {
        this.idProvincia = idProvincia;
        this.idCanton = idCanton;
        this.idCircunscripcion = idCircunscripcion;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public int getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(int idCanton) {
        this.idCanton = idCanton;
    }

    public int getIdCircunscripcion() {
        return idCircunscripcion;
    }

    public void setIdCircunscripcion(int idCircunscripcion) {
        this.idCircunscripcion = idCircunscripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProvincia;
        hash += (int) idCanton;
        hash += (int) idCircunscripcion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCircunscripcionCantonPK)) {
            return false;
        }
        TCircunscripcionCantonPK other = (TCircunscripcionCantonPK) object;
        if (this.idProvincia != other.idProvincia) {
            return false;
        }
        if (this.idCanton != other.idCanton) {
            return false;
        }
        if (this.idCircunscripcion != other.idCircunscripcion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TCircunscripcionCantonPK[ idProvincia=" + idProvincia + ", idCanton=" + idCanton + ", idCircunscripcion=" + idCircunscripcion + " ]";
    }
    
}
