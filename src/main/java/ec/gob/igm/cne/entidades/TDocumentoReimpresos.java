/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JATIVA_MERCEDES
 */
@Entity
@Table(name = "T_DOCUMENTO_REIMPRESOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDocumentoReimpresos.findAll", query = "SELECT t FROM TDocumentoReimpresos t")
    , @NamedQuery(name = "TDocumentoReimpresos.findByCodProvincia", query = "SELECT t FROM TDocumentoReimpresos t WHERE t.codProvincia = :codProvincia")
    , @NamedQuery(name = "TDocumentoReimpresos.findByCodCanton", query = "SELECT t FROM TDocumentoReimpresos t WHERE t.codCanton = :codCanton")
    , @NamedQuery(name = "TDocumentoReimpresos.findByCodParroquia", query = "SELECT t FROM TDocumentoReimpresos t WHERE t.codParroquia = :codParroquia")
    , @NamedQuery(name = "TDocumentoReimpresos.findByCodZona", query = "SELECT t FROM TDocumentoReimpresos t WHERE t.codZona = :codZona")
    , @NamedQuery(name = "TDocumentoReimpresos.findBySexo", query = "SELECT t FROM TDocumentoReimpresos t WHERE t.sexo = :sexo")
    , @NamedQuery(name = "TDocumentoReimpresos.findByJunta", query = "SELECT t FROM TDocumentoReimpresos t WHERE t.junta = :junta")
    , @NamedQuery(name = "TDocumentoReimpresos.findByCodCircunscripcion", query = "SELECT t FROM TDocumentoReimpresos t WHERE t.codCircunscripcion = :codCircunscripcion")
    , @NamedQuery(name = "TDocumentoReimpresos.findByIdSeq", query = "SELECT t FROM TDocumentoReimpresos t WHERE t.idSeq = :idSeq")
    , @NamedQuery(name = "TDocumentoReimpresos.findByCodOrden", query = "SELECT t FROM TDocumentoReimpresos t WHERE t.codOrden = :codOrden")})
public class TDocumentoReimpresos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "COD_PROVINCIA")
    private Short codProvincia;
    @Column(name = "COD_CANTON")
    private Short codCanton;
    @Column(name = "COD_PARROQUIA")
    private Short codParroquia;
    @Column(name = "COD_ZONA")
    private Short codZona;
    @Size(max = 1)
    @Column(name = "SEXO")
    private String sexo;
    @Column(name = "JUNTA")
    private Short junta;
    @Size(max = 6)
    @Column(name = "COD_CIRCUNSCRIPCION")
    private String codCircunscripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SEQ")
    private BigDecimal idSeq;
    @Column(name = "COD_ORDEN")
    private BigInteger codOrden;
    @JoinColumn(name = "ID_LISTADO", referencedColumnName = "ID_LISTADO")
    @ManyToOne
    private TListadoDocumentos idListado;

    public TDocumentoReimpresos() {
    }

    public TDocumentoReimpresos(BigDecimal idSeq) {
        this.idSeq = idSeq;
    }

    public Short getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(Short codProvincia) {
        this.codProvincia = codProvincia;
    }

    public Short getCodCanton() {
        return codCanton;
    }

    public void setCodCanton(Short codCanton) {
        this.codCanton = codCanton;
    }

    public Short getCodParroquia() {
        return codParroquia;
    }

    public void setCodParroquia(Short codParroquia) {
        this.codParroquia = codParroquia;
    }

    public Short getCodZona() {
        return codZona;
    }

    public void setCodZona(Short codZona) {
        this.codZona = codZona;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Short getJunta() {
        return junta;
    }

    public void setJunta(Short junta) {
        this.junta = junta;
    }

    public String getCodCircunscripcion() {
        return codCircunscripcion;
    }

    public void setCodCircunscripcion(String codCircunscripcion) {
        this.codCircunscripcion = codCircunscripcion;
    }

    public BigDecimal getIdSeq() {
        return idSeq;
    }

    public void setIdSeq(BigDecimal idSeq) {
        this.idSeq = idSeq;
    }

    public BigInteger getCodOrden() {
        return codOrden;
    }

    public void setCodOrden(BigInteger codOrden) {
        this.codOrden = codOrden;
    }

    public TListadoDocumentos getIdListado() {
        return idListado;
    }

    public void setIdListado(TListadoDocumentos idListado) {
        this.idListado = idListado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeq != null ? idSeq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDocumentoReimpresos)) {
            return false;
        }
        TDocumentoReimpresos other = (TDocumentoReimpresos) object;
        if ((this.idSeq == null && other.idSeq != null) || (this.idSeq != null && !this.idSeq.equals(other.idSeq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TDocumentoReimpresos[ idSeq=" + idSeq + " ]";
    }
    
}
