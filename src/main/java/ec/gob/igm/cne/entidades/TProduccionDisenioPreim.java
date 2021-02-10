/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "T_PRODUCCION_DISENIO_PREIM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TProduccionDisenioPreim.findAll", query = "SELECT t FROM TProduccionDisenioPreim t")
    , @NamedQuery(name = "TProduccionDisenioPreim.findById", query = "SELECT t FROM TProduccionDisenioPreim t WHERE t.id = :id")
    , @NamedQuery(name = "TProduccionDisenioPreim.findByFecha", query = "SELECT t FROM TProduccionDisenioPreim t WHERE t.fecha = :fecha")
    , @NamedQuery(name = "TProduccionDisenioPreim.findByEstado", query = "SELECT t FROM TProduccionDisenioPreim t WHERE t.estado = :estado")
    , @NamedQuery(name = "TProduccionDisenioPreim.findByUsuario", query = "SELECT t FROM TProduccionDisenioPreim t WHERE t.usuario = :usuario")
    , @NamedQuery(name = "TProduccionDisenioPreim.findByTurno", query = "SELECT t FROM TProduccionDisenioPreim t WHERE t.turno = :turno")
    , @NamedQuery(name = "TProduccionDisenioPreim.findByIdPrensa", query = "SELECT t FROM TProduccionDisenioPreim t WHERE t.idPrensa = :idPrensa")
    , @NamedQuery(name = "TProduccionDisenioPreim.findByCantidadPlanificada", query = "SELECT t FROM TProduccionDisenioPreim t WHERE t.cantidadPlanificada = :cantidadPlanificada")
    , @NamedQuery(name = "TProduccionDisenioPreim.findByCantidadEjecutada", query = "SELECT t FROM TProduccionDisenioPreim t WHERE t.cantidadEjecutada = :cantidadEjecutada")})
public class TProduccionDisenioPreim implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "TURNO")
    private Long turno;
    @Column(name = "ID_PRENSA")
    private Short idPrensa;
    @Column(name = "CANTIDAD_PLANIFICADA")
    private BigInteger cantidadPlanificada;
    @Column(name = "CANTIDAD_EJECUTADA")
    private BigInteger cantidadEjecutada;
    @JoinColumn(name = "ID_TIPO_DOCUMENTO", referencedColumnName = "ID")
    @ManyToOne
    private TDocumentosPreimpresos idTipoDocumento;

    public TProduccionDisenioPreim() {
    }

    public TProduccionDisenioPreim(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getTurno() {
        return turno;
    }

    public void setTurno(Long turno) {
        this.turno = turno;
    }

    public Short getIdPrensa() {
        return idPrensa;
    }

    public void setIdPrensa(Short idPrensa) {
        this.idPrensa = idPrensa;
    }

    public BigInteger getCantidadPlanificada() {
        return cantidadPlanificada;
    }

    public void setCantidadPlanificada(BigInteger cantidadPlanificada) {
        this.cantidadPlanificada = cantidadPlanificada;
    }

    public BigInteger getCantidadEjecutada() {
        return cantidadEjecutada;
    }

    public void setCantidadEjecutada(BigInteger cantidadEjecutada) {
        this.cantidadEjecutada = cantidadEjecutada;
    }

    public TDocumentosPreimpresos getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(TDocumentosPreimpresos idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
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
        if (!(object instanceof TProduccionDisenioPreim)) {
            return false;
        }
        TProduccionDisenioPreim other = (TProduccionDisenioPreim) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TProduccionDisenioPreim[ id=" + id + " ]";
    }
    
}
