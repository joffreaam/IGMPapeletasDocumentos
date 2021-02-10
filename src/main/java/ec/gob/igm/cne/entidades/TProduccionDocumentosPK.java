/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author desarrollo
 */
@Embeddable
public class TProduccionDocumentosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private long idRegistro;
    @Basic(optional = false)
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;

    public TProduccionDocumentosPK() {
    }

    public TProduccionDocumentosPK(long idRegistro, Date fechaReg) {
        this.idRegistro = idRegistro;
        this.fechaReg = fechaReg;
    }

    public long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Date getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(Date fechaReg) {
        this.fechaReg = fechaReg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRegistro;
        hash += (fechaReg != null ? fechaReg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TProduccionDocumentosPK)) {
            return false;
        }
        TProduccionDocumentosPK other = (TProduccionDocumentosPK) object;
        if (this.idRegistro != other.idRegistro) {
            return false;
        }
        if ((this.fechaReg == null && other.fechaReg != null) || (this.fechaReg != null && !this.fechaReg.equals(other.fechaReg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TProduccionDocumentosPK[ idRegistro=" + idRegistro + ", fechaReg=" + fechaReg + " ]";
    }
    
}
