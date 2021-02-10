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
@Table(name = "T_PRODUCCION_DISENIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TProduccionDisenio.findAll", query = "SELECT t FROM TProduccionDisenio t")
    , @NamedQuery(name = "TProduccionDisenio.findByIdRegistro", query = "SELECT t FROM TProduccionDisenio t WHERE t.idRegistro = :idRegistro")
    , @NamedQuery(name = "TProduccionDisenio.findByIdProvincia", query = "SELECT t FROM TProduccionDisenio t WHERE t.idProvincia = :idProvincia")
    , @NamedQuery(name = "TProduccionDisenio.findByFechaReg", query = "SELECT t FROM TProduccionDisenio t WHERE t.fechaReg = :fechaReg")
    , @NamedQuery(name = "TProduccionDisenio.findByTurno", query = "SELECT t FROM TProduccionDisenio t WHERE t.turno = :turno")
    , @NamedQuery(name = "TProduccionDisenio.findByOperador", query = "SELECT t FROM TProduccionDisenio t WHERE t.operador = :operador")
    , @NamedQuery(name = "TProduccionDisenio.findByUsuario", query = "SELECT t FROM TProduccionDisenio t WHERE t.usuario = :usuario")
    , @NamedQuery(name = "TProduccionDisenio.findByEstado", query = "SELECT t FROM TProduccionDisenio t WHERE t.estado = :estado")
    , @NamedQuery(name = "TProduccionDisenio.findByCantidadPlanificada", query = "SELECT t FROM TProduccionDisenio t WHERE t.cantidadPlanificada = :cantidadPlanificada")
    , @NamedQuery(name = "TProduccionDisenio.findByCantidadEjecutada", query = "SELECT t FROM TProduccionDisenio t WHERE t.cantidadEjecutada = :cantidadEjecutada")})
public class TProduccionDisenio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private Long idRegistro;
    @Column(name = "ID_PROVINCIA")
    private Integer idProvincia;
    @Basic(optional = false)
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    @Column(name = "TURNO")
    private Long turno;
    @Column(name = "OPERADOR")
    private String operador;
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "CANTIDAD_PLANIFICADA")
    private BigInteger cantidadPlanificada;
    @Column(name = "CANTIDAD_EJECUTADA")
    private BigInteger cantidadEjecutada;
    @JoinColumn(name = "ID_CANTON", referencedColumnName = "ID_CANTON")
    @ManyToOne
    private TCanton idCanton;
    @JoinColumn(name = "COD_DIGNIDAD", referencedColumnName = "COD_DIGNIDAD")
    @ManyToOne
    private TDignidad codDignidad;
    @JoinColumn(name = "ID_PARROQUIA", referencedColumnName = "ID_PARROQUIA")
    @ManyToOne
    private TParroquia idParroquia;
    @JoinColumn(name = "ID_PRENSA", referencedColumnName = "ID_PRENSA")
    @ManyToOne
    private TPrensas idPrensa;

    public TProduccionDisenio() {
    }

    public TProduccionDisenio(Long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public TProduccionDisenio(Long idRegistro, Date fechaReg) {
        this.idRegistro = idRegistro;
        this.fechaReg = fechaReg;
    }

    public Long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Date getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(Date fechaReg) {
        this.fechaReg = fechaReg;
    }

    public Long getTurno() {
        return turno;
    }

    public void setTurno(Long turno) {
        this.turno = turno;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public TCanton getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(TCanton idCanton) {
        this.idCanton = idCanton;
    }

    public TDignidad getCodDignidad() {
        return codDignidad;
    }

    public void setCodDignidad(TDignidad codDignidad) {
        this.codDignidad = codDignidad;
    }

    public TParroquia getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(TParroquia idParroquia) {
        this.idParroquia = idParroquia;
    }

    public TPrensas getIdPrensa() {
        return idPrensa;
    }

    public void setIdPrensa(TPrensas idPrensa) {
        this.idPrensa = idPrensa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistro != null ? idRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TProduccionDisenio)) {
            return false;
        }
        TProduccionDisenio other = (TProduccionDisenio) object;
        if ((this.idRegistro == null && other.idRegistro != null) || (this.idRegistro != null && !this.idRegistro.equals(other.idRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TProduccionDisenio[ idRegistro=" + idRegistro + " ]";
    }
    
}
