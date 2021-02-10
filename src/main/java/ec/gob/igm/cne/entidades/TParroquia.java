/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "T_PARROQUIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TParroquia.findAll", query = "SELECT t FROM TParroquia t")
    , @NamedQuery(name = "TParroquia.findByIdParroquia", query = "SELECT t FROM TParroquia t WHERE t.idParroquia = :idParroquia")
    , @NamedQuery(name = "TParroquia.findByParroquia", query = "SELECT t FROM TParroquia t WHERE t.parroquia = :parroquia")})
public class TParroquia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PARROQUIA")
    private Integer idParroquia;
    @Basic(optional = false)
    @Column(name = "PARROQUIA")
    private String parroquia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tParroquia")
    private List<TZona> tZonaList;
    @JoinColumn(name = "ID_CANTON", referencedColumnName = "ID_CANTON")
    @ManyToOne
    private TCanton idCanton;
    @OneToMany(mappedBy = "idParroquia")
    private List<TProduccionDisenio> tProduccionDisenioList;
    @OneToMany(mappedBy = "idParroquia")
    private List<TProduccionPapeletas> tProduccionPapeletasList;

    public TParroquia() {
    }

    public TParroquia(Integer idParroquia) {
        this.idParroquia = idParroquia;
    }

    public TParroquia(Integer idParroquia, String parroquia) {
        this.idParroquia = idParroquia;
        this.parroquia = parroquia;
    }

    public Integer getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(Integer idParroquia) {
        this.idParroquia = idParroquia;
    }

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

    @XmlTransient
    public List<TZona> getTZonaList() {
        return tZonaList;
    }

    public void setTZonaList(List<TZona> tZonaList) {
        this.tZonaList = tZonaList;
    }

    public TCanton getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(TCanton idCanton) {
        this.idCanton = idCanton;
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
        hash += (idParroquia != null ? idParroquia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TParroquia)) {
            return false;
        }
        TParroquia other = (TParroquia) object;
        if ((this.idParroquia == null && other.idParroquia != null) || (this.idParroquia != null && !this.idParroquia.equals(other.idParroquia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TParroquia[ idParroquia=" + idParroquia + " ]";
    }
    
}
