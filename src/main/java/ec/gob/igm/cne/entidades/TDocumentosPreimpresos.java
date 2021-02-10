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
@Table(name = "T_DOCUMENTOS_PREIMPRESOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDocumentosPreimpresos.findAll", query = "SELECT t FROM TDocumentosPreimpresos t")
    , @NamedQuery(name = "TDocumentosPreimpresos.findById", query = "SELECT t FROM TDocumentosPreimpresos t WHERE t.id = :id")
    , @NamedQuery(name = "TDocumentosPreimpresos.findByDocumento", query = "SELECT t FROM TDocumentosPreimpresos t WHERE t.documento = :documento")
    , @NamedQuery(name = "TDocumentosPreimpresos.findByTotal", query = "SELECT t FROM TDocumentosPreimpresos t WHERE t.total = :total")})
public class TDocumentosPreimpresos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DOCUMENTO")
    private String documento;
    @Column(name = "TOTAL")
    private Long total;
    @OneToMany(mappedBy = "idTipoDocumento")
    private List<TProduccionPreimpresos> tProduccionPreimpresosList;
    @OneToMany(mappedBy = "idTipoDocumento")
    private List<TProduccionDisenioPreim> tProduccionDisenioPreimList;

    public TDocumentosPreimpresos() {
    }

    public TDocumentosPreimpresos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @XmlTransient
    public List<TProduccionPreimpresos> getTProduccionPreimpresosList() {
        return tProduccionPreimpresosList;
    }

    public void setTProduccionPreimpresosList(List<TProduccionPreimpresos> tProduccionPreimpresosList) {
        this.tProduccionPreimpresosList = tProduccionPreimpresosList;
    }

    @XmlTransient
    public List<TProduccionDisenioPreim> getTProduccionDisenioPreimList() {
        return tProduccionDisenioPreimList;
    }

    public void setTProduccionDisenioPreimList(List<TProduccionDisenioPreim> tProduccionDisenioPreimList) {
        this.tProduccionDisenioPreimList = tProduccionDisenioPreimList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDocumentosPreimpresos)) {
            return false;
        }
        TDocumentosPreimpresos other = (TDocumentosPreimpresos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TDocumentosPreimpresos[ id=" + id + " ]";
    }
    
}
