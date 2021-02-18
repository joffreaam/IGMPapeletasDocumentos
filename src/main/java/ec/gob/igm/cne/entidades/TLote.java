/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VERA_MAYRA
 */
@Entity
@Table(name = "T_LOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TLote.findAll", query = "SELECT t FROM TLote t")
    , @NamedQuery(name = "TLote.findByIdLote", query = "SELECT t FROM TLote t WHERE t.tLotePK.idLote = :idLote")
    , @NamedQuery(name = "TLote.findByEstado", query = "SELECT t FROM TLote t WHERE t.estado = :estado")
    , @NamedQuery(name = "TLote.findByFechaCreacion", query = "SELECT t FROM TLote t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TLote.findByUsuarioCreacion", query = "SELECT t FROM TLote t WHERE t.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "TLote.findByTipo", query = "SELECT t FROM TLote t WHERE t.tLotePK.tipo = :tipo")
    , @NamedQuery(name = "TLote.findByFechaDespachado", query = "SELECT t FROM TLote t WHERE t.fechaDespachado = :fechaDespachado")
    , @NamedQuery(name = "TLote.findByUsuarioDespachado", query = "SELECT t FROM TLote t WHERE t.usuarioDespachado = :usuarioDespachado")
    , @NamedQuery(name = "TLote.findByFechaRecibido", query = "SELECT t FROM TLote t WHERE t.fechaRecibido = :fechaRecibido")
    , @NamedQuery(name = "TLote.findByUsuarioRecibido", query = "SELECT t FROM TLote t WHERE t.usuarioRecibido = :usuarioRecibido")
    , @NamedQuery(name = "TLote.findByIdLoteAnterior", query = "SELECT t FROM TLote t WHERE t.idLoteAnterior = :idLoteAnterior")
    , @NamedQuery(name = "TLote.findByFechaCierre", query = "SELECT t FROM TLote t WHERE t.fechaCierre = :fechaCierre")
    , @NamedQuery(name = "TLote.findByUsuarioCierre", query = "SELECT t FROM TLote t WHERE t.usuarioCierre = :usuarioCierre")
    , @NamedQuery(name = "TLote.findByIdBodega", query = "SELECT t FROM TLote t WHERE t.idBodega = :idBodega")
    , @NamedQuery(name = "TLote.findByFechaEntregaActa", query = "SELECT t FROM TLote t WHERE t.fechaEntregaActa = :fechaEntregaActa")
    , @NamedQuery(name = "TLote.findByEstadoImpresion", query = "SELECT t FROM TLote t WHERE t.estadoImpresion = :estadoImpresion")})
public class TLote implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TLotePK tLotePK;
    @Size(max = 15)
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 50)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Column(name = "FECHA_DESPACHADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDespachado;
    @Size(max = 50)
    @Column(name = "USUARIO_DESPACHADO")
    private String usuarioDespachado;
    @Column(name = "FECHA_RECIBIDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRecibido;
    @Size(max = 50)
    @Column(name = "USUARIO_RECIBIDO")
    private String usuarioRecibido;
    @Column(name = "ID_LOTE_ANTERIOR")
    private Integer idLoteAnterior;
    @Column(name = "FECHA_CIERRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierre;
    @Size(max = 50)
    @Column(name = "USUARIO_CIERRE")
    private String usuarioCierre;
    @Column(name = "ID_BODEGA")
    private Short idBodega;
    @Column(name = "FECHA_ENTREGA_ACTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntregaActa;
    @Size(max = 15)
    @Column(name = "ESTADO_IMPRESION")
    private String estadoImpresion;
    @JoinColumn(name = "ID_PROVINCIA", referencedColumnName = "ID_PROVINCIA")
    @ManyToOne
    private TProvincia idProvincia;

    public TLote() {
    }

    public TLote(TLotePK tLotePK) {
        this.tLotePK = tLotePK;
    }

    public TLote(int idLote, String tipo) {
        this.tLotePK = new TLotePK(idLote, tipo);
    }

    public TLotePK getTLotePK() {
        return tLotePK;
    }

    public void setTLotePK(TLotePK tLotePK) {
        this.tLotePK = tLotePK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaDespachado() {
        return fechaDespachado;
    }

    public void setFechaDespachado(Date fechaDespachado) {
        this.fechaDespachado = fechaDespachado;
    }

    public String getUsuarioDespachado() {
        return usuarioDespachado;
    }

    public void setUsuarioDespachado(String usuarioDespachado) {
        this.usuarioDespachado = usuarioDespachado;
    }

    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public String getUsuarioRecibido() {
        return usuarioRecibido;
    }

    public void setUsuarioRecibido(String usuarioRecibido) {
        this.usuarioRecibido = usuarioRecibido;
    }

    public Integer getIdLoteAnterior() {
        return idLoteAnterior;
    }

    public void setIdLoteAnterior(Integer idLoteAnterior) {
        this.idLoteAnterior = idLoteAnterior;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getUsuarioCierre() {
        return usuarioCierre;
    }

    public void setUsuarioCierre(String usuarioCierre) {
        this.usuarioCierre = usuarioCierre;
    }

    public Short getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(Short idBodega) {
        this.idBodega = idBodega;
    }

    public Date getFechaEntregaActa() {
        return fechaEntregaActa;
    }

    public void setFechaEntregaActa(Date fechaEntregaActa) {
        this.fechaEntregaActa = fechaEntregaActa;
    }

    public String getEstadoImpresion() {
        return estadoImpresion;
    }

    public void setEstadoImpresion(String estadoImpresion) {
        this.estadoImpresion = estadoImpresion;
    }

    public TProvincia getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(TProvincia idProvincia) {
        this.idProvincia = idProvincia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tLotePK != null ? tLotePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TLote)) {
            return false;
        }
        TLote other = (TLote) object;
        if ((this.tLotePK == null && other.tLotePK != null) || (this.tLotePK != null && !this.tLotePK.equals(other.tLotePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TLote[ tLotePK=" + tLotePK + " ]";
    }
    
}
