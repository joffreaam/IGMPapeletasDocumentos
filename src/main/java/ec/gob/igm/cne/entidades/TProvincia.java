/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "T_PROVINCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TProvincia.findAll", query = "SELECT t FROM TProvincia t")
    , @NamedQuery(name = "TProvincia.findByIdProvincia", query = "SELECT t FROM TProvincia t WHERE t.idProvincia = :idProvincia")
    , @NamedQuery(name = "TProvincia.findByProvincia", query = "SELECT t FROM TProvincia t WHERE t.provincia = :provincia")
    , @NamedQuery(name = "TProvincia.findByPrelacion", query = "SELECT t FROM TProvincia t WHERE t.prelacion = :prelacion")})
public class TProvincia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PROVINCIA")
    private Integer idProvincia;
    @Basic(optional = false)
    @Column(name = "PROVINCIA")
    private String provincia;
    @Column(name = "PRELACION")
    private BigInteger prelacion;
    @OneToMany(mappedBy = "idProvincia")
    private List<TCanton> tCantonList;
    @OneToMany(mappedBy = "idProvincia")
    private List<TProduccionDocumentos> tProduccionDocumentosList;

    public TProvincia() {
    }

    public TProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public TProvincia(Integer idProvincia, String provincia) {
        this.idProvincia = idProvincia;
        this.provincia = provincia;
    }

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public BigInteger getPrelacion() {
        return prelacion;
    }

    public void setPrelacion(BigInteger prelacion) {
        this.prelacion = prelacion;
    }

    @XmlTransient
    public List<TCanton> getTCantonList() {
        return tCantonList;
    }

    public void setTCantonList(List<TCanton> tCantonList) {
        this.tCantonList = tCantonList;
    }

    @XmlTransient
    public List<TProduccionDocumentos> getTProduccionDocumentosList() {
        return tProduccionDocumentosList;
    }

    public void setTProduccionDocumentosList(List<TProduccionDocumentos> tProduccionDocumentosList) {
        this.tProduccionDocumentosList = tProduccionDocumentosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProvincia != null ? idProvincia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TProvincia)) {
            return false;
        }
        TProvincia other = (TProvincia) object;
        if ((this.idProvincia == null && other.idProvincia != null) || (this.idProvincia != null && !this.idProvincia.equals(other.idProvincia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idProvincia + "-" + provincia;
    }
    
}
