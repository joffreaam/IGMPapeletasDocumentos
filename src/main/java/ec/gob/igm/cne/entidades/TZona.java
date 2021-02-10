/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
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
@Table(name = "T_ZONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TZona.findAll", query = "SELECT t FROM TZona t")
    , @NamedQuery(name = "TZona.findByIdZona", query = "SELECT t FROM TZona t WHERE t.tZonaPK.idZona = :idZona")
    , @NamedQuery(name = "TZona.findByIdParroquia", query = "SELECT t FROM TZona t WHERE t.tZonaPK.idParroquia = :idParroquia")
    , @NamedQuery(name = "TZona.findByZona", query = "SELECT t FROM TZona t WHERE t.zona = :zona")})
public class TZona implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TZonaPK tZonaPK;
    @Column(name = "ZONA")
    private String zona;
    @JoinColumn(name = "ID_PARROQUIA", referencedColumnName = "ID_PARROQUIA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TParroquia tParroquia;

    public TZona() {
    }

    public TZona(TZonaPK tZonaPK) {
        this.tZonaPK = tZonaPK;
    }

    public TZona(int idZona, int idParroquia) {
        this.tZonaPK = new TZonaPK(idZona, idParroquia);
    }

    public TZonaPK getTZonaPK() {
        return tZonaPK;
    }

    public void setTZonaPK(TZonaPK tZonaPK) {
        this.tZonaPK = tZonaPK;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public TParroquia getTParroquia() {
        return tParroquia;
    }

    public void setTParroquia(TParroquia tParroquia) {
        this.tParroquia = tParroquia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tZonaPK != null ? tZonaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TZona)) {
            return false;
        }
        TZona other = (TZona) object;
        if ((this.tZonaPK == null && other.tZonaPK != null) || (this.tZonaPK != null && !this.tZonaPK.equals(other.tZonaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TZona[ tZonaPK=" + tZonaPK + " ]";
    }
    
}
