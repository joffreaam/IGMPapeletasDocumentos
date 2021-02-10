/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "T_LISTADO_DOCUMENTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TListadoDocumentos.findAll", query = "SELECT t FROM TListadoDocumentos t")
    , @NamedQuery(name = "TListadoDocumentos.findByIdListado", query = "SELECT t FROM TListadoDocumentos t WHERE t.idListado = :idListado")
    , @NamedQuery(name = "TListadoDocumentos.findByDescripcion", query = "SELECT t FROM TListadoDocumentos t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TListadoDocumentos.findByLeebarras", query = "SELECT t FROM TListadoDocumentos t WHERE t.leebarras = :leebarras")
    , @NamedQuery(name = "TListadoDocumentos.findByEstado", query = "SELECT t FROM TListadoDocumentos t WHERE t.estado = :estado")})
public class TListadoDocumentos implements Serializable {

    @Size(max = 13)
    @Column(name = "CODIGO_TIPODOC")
    private String codigoTipodoc;
    @OneToMany(mappedBy = "idListado")
    private Collection<TDocumentoReimpresos> tDocumentoReimpresosCollection;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_LISTADO")
    private BigDecimal idListado;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "LEEBARRAS")
    private Character leebarras;
    @Column(name = "ESTADO")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tListadoDocumentos")
    private List<TOrdenDocumento> tOrdenDocumentoList;

    public TListadoDocumentos() {
    }

    public TListadoDocumentos(BigDecimal idListado) {
        this.idListado = idListado;
    }

    public BigDecimal getIdListado() {
        return idListado;
    }

    public void setIdListado(BigDecimal idListado) {
        this.idListado = idListado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Character getLeebarras() {
        return leebarras;
    }

    public void setLeebarras(Character leebarras) {
        this.leebarras = leebarras;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<TOrdenDocumento> getTOrdenDocumentoList() {
        return tOrdenDocumentoList;
    }

    public void setTOrdenDocumentoList(List<TOrdenDocumento> tOrdenDocumentoList) {
        this.tOrdenDocumentoList = tOrdenDocumentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idListado != null ? idListado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TListadoDocumentos)) {
            return false;
        }
        TListadoDocumentos other = (TListadoDocumentos) object;
        if ((this.idListado == null && other.idListado != null) || (this.idListado != null && !this.idListado.equals(other.idListado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TListadoDocumentos[ idListado=" + idListado + " ]";
    }

    public String getCodigoTipodoc() {
        return codigoTipodoc;
    }

    public void setCodigoTipodoc(String codigoTipodoc) {
        this.codigoTipodoc = codigoTipodoc;
    }

    @XmlTransient
    public Collection<TDocumentoReimpresos> getTDocumentoReimpresosCollection() {
        return tDocumentoReimpresosCollection;
    }

    public void setTDocumentoReimpresosCollection(Collection<TDocumentoReimpresos> tDocumentoReimpresosCollection) {
        this.tDocumentoReimpresosCollection = tDocumentoReimpresosCollection;
    }
    
}
