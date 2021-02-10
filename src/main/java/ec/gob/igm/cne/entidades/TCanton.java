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
@Table(name = "T_CANTON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCanton.findAll", query = "SELECT t FROM TCanton t")
    , @NamedQuery(name = "TCanton.findByIdCanton", query = "SELECT t FROM TCanton t WHERE t.idCanton = :idCanton")
    , @NamedQuery(name = "TCanton.findByCanton", query = "SELECT t FROM TCanton t WHERE t.canton = :canton")})
public class TCanton implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CANTON")
    private Integer idCanton;
    @Basic(optional = false)
    @Column(name = "CANTON")
    private String canton;
    @OneToMany(mappedBy = "idCanton")
    private List<TParroquia> tParroquiaList;
    @JoinColumn(name = "ID_PROVINCIA", referencedColumnName = "ID_PROVINCIA")
    @ManyToOne
    private TProvincia idProvincia;
    @OneToMany(mappedBy = "idCanton")
    private List<TProduccionDisenio> tProduccionDisenioList;
    @OneToMany(mappedBy = "idCanton")
    private List<TProduccionPapeletas> tProduccionPapeletasList;

    public TCanton() {
    }

    public TCanton(Integer idCanton) {
        this.idCanton = idCanton;
    }

    public TCanton(Integer idCanton, String canton) {
        this.idCanton = idCanton;
        this.canton = canton;
    }

    public Integer getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(Integer idCanton) {
        this.idCanton = idCanton;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    @XmlTransient
    public List<TParroquia> getTParroquiaList() {
        return tParroquiaList;
    }

    public void setTParroquiaList(List<TParroquia> tParroquiaList) {
        this.tParroquiaList = tParroquiaList;
    }

    public TProvincia getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(TProvincia idProvincia) {
        this.idProvincia = idProvincia;
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
        hash += (idCanton != null ? idCanton.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCanton)) {
            return false;
        }
        TCanton other = (TCanton) object;
        if ((this.idCanton == null && other.idCanton != null) || (this.idCanton != null && !this.idCanton.equals(other.idCanton))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TCanton[ idCanton=" + idCanton + " ]";
    }
    
}
