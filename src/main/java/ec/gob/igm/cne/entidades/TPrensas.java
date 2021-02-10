/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "T_PRENSAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TPrensas.findAll", query = "SELECT t FROM TPrensas t")
    , @NamedQuery(name = "TPrensas.findByIdPrensa", query = "SELECT t FROM TPrensas t WHERE t.idPrensa = :idPrensa")
    , @NamedQuery(name = "TPrensas.findByDescripcion", query = "SELECT t FROM TPrensas t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TPrensas.findByBobinas", query = "SELECT t FROM TPrensas t WHERE t.bobinas = :bobinas")
    , @NamedQuery(name = "TPrensas.findByPliegos", query = "SELECT t FROM TPrensas t WHERE t.pliegos = :pliegos")})
public class TPrensas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PRENSA")
    private Short idPrensa;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "BOBINAS")
    private Short bobinas;
    @Column(name = "PLIEGOS")
    private Short pliegos;
    @OneToMany(mappedBy = "idPrensa")
    private List<TProduccionDisenio> tProduccionDisenioList;
    @OneToMany(mappedBy = "idPrensa")
    private List<TProduccionPapeletas> tProduccionPapeletasList;

    public TPrensas() {
    }

    public TPrensas(Short idPrensa) {
        this.idPrensa = idPrensa;
    }

    public Short getIdPrensa() {
        return idPrensa;
    }

    public void setIdPrensa(Short idPrensa) {
        this.idPrensa = idPrensa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Short getBobinas() {
        return bobinas;
    }

    public void setBobinas(Short bobinas) {
        this.bobinas = bobinas;
    }

    public Short getPliegos() {
        return pliegos;
    }

    public void setPliegos(Short pliegos) {
        this.pliegos = pliegos;
    }

    @XmlTransient
    public List<TProduccionDisenio> getTProduccionDisenioList() {
        return tProduccionDisenioList;
    }

    public void setTProduccionDisenioList(List<TProduccionDisenio> tProduccionDisenioList) {
        this.tProduccionDisenioList = tProduccionDisenioList;
    }

    @XmlTransient
    public List<TProduccionPapeletas> getTProduccionPapeletasList() {
        return tProduccionPapeletasList;
    }

    public void setTProduccionPapeletasList(List<TProduccionPapeletas> tProduccionPapeletasList) {
        this.tProduccionPapeletasList = tProduccionPapeletasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrensa != null ? idPrensa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TPrensas)) {
            return false;
        }
        TPrensas other = (TPrensas) object;
        if ((this.idPrensa == null && other.idPrensa != null) || (this.idPrensa != null && !this.idPrensa.equals(other.idPrensa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TPrensas[ idPrensa=" + idPrensa + " ]";
    }
    
}
