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
@Table(name = "T_PRODUCCION_PREIMPRESOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TProduccionPreimpresos.findAll", query = "SELECT t FROM TProduccionPreimpresos t")
    , @NamedQuery(name = "TProduccionPreimpresos.findById", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.id = :id")
    , @NamedQuery(name = "TProduccionPreimpresos.findByFecha", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.fecha = :fecha")
    , @NamedQuery(name = "TProduccionPreimpresos.findByEstado", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.estado = :estado")
    , @NamedQuery(name = "TProduccionPreimpresos.findByCantidadConforme", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.cantidadConforme = :cantidadConforme")
    , @NamedQuery(name = "TProduccionPreimpresos.findByMaterialNoconforme", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.materialNoconforme = :materialNoconforme")
    , @NamedQuery(name = "TProduccionPreimpresos.findByUsuario", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.usuario = :usuario")
    , @NamedQuery(name = "TProduccionPreimpresos.findByTurno", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.turno = :turno")
    , @NamedQuery(name = "TProduccionPreimpresos.findByFormato", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.formato = :formato")
    , @NamedQuery(name = "TProduccionPreimpresos.findByIdPrensa", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.idPrensa = :idPrensa")
    , @NamedQuery(name = "TProduccionPreimpresos.findByFormatoHoja", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.formatoHoja = :formatoHoja")
    , @NamedQuery(name = "TProduccionPreimpresos.findByPliegosBobinas", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.pliegosBobinas = :pliegosBobinas")
    , @NamedQuery(name = "TProduccionPreimpresos.findByOperador", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.operador = :operador")
    , @NamedQuery(name = "TProduccionPreimpresos.findByFormatosPapel", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.formatosPapel = :formatosPapel")
    , @NamedQuery(name = "TProduccionPreimpresos.findByArmado", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.armado = :armado")
    , @NamedQuery(name = "TProduccionPreimpresos.findByMaterialMaculatura", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.materialMaculatura = :materialMaculatura")
    , @NamedQuery(name = "TProduccionPreimpresos.findByCantidadPlacas", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.cantidadPlacas = :cantidadPlacas")
    , @NamedQuery(name = "TProduccionPreimpresos.findByNPalet", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.nPalet = :nPalet")
    , @NamedQuery(name = "TProduccionPreimpresos.findByObservaciones", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.observaciones = :observaciones")
    , @NamedQuery(name = "TProduccionPreimpresos.findByResponsableDiseno", query = "SELECT t FROM TProduccionPreimpresos t WHERE t.responsableDiseno = :responsableDiseno")})
public class TProduccionPreimpresos implements Serializable {

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
    @Column(name = "CANTIDAD_CONFORME")
    private Long cantidadConforme;
    @Column(name = "MATERIAL_NOCONFORME")
    private BigInteger materialNoconforme;
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "TURNO")
    private Long turno;
    @Column(name = "FORMATO")
    private String formato;
    @Column(name = "ID_PRENSA")
    private Short idPrensa;
    @Column(name = "FORMATO_HOJA")
    private String formatoHoja;
    @Column(name = "PLIEGOS_BOBINAS")
    private Long pliegosBobinas;
    @Column(name = "OPERADOR")
    private String operador;
    @Column(name = "FORMATOS_PAPEL")
    private String formatosPapel;
    @Column(name = "ARMADO")
    private Long armado;
    @Column(name = "MATERIAL_MACULATURA")
    private Long materialMaculatura;
    @Column(name = "CANTIDAD_PLACAS")
    private Long cantidadPlacas;
    @Column(name = "N_PALET")
    private Long nPalet;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "RESPONSABLE_DISENO")
    private String responsableDiseno;
    @JoinColumn(name = "ID_TIPO_DOCUMENTO", referencedColumnName = "ID")
    @ManyToOne
    private TDocumentosPreimpresos idTipoDocumento;

    public TProduccionPreimpresos() {
    }

    public TProduccionPreimpresos(Integer id) {
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

    public Long getCantidadConforme() {
        return cantidadConforme;
    }

    public void setCantidadConforme(Long cantidadConforme) {
        this.cantidadConforme = cantidadConforme;
    }

    public BigInteger getMaterialNoconforme() {
        return materialNoconforme;
    }

    public void setMaterialNoconforme(BigInteger materialNoconforme) {
        this.materialNoconforme = materialNoconforme;
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

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Short getIdPrensa() {
        return idPrensa;
    }

    public void setIdPrensa(Short idPrensa) {
        this.idPrensa = idPrensa;
    }

    public String getFormatoHoja() {
        return formatoHoja;
    }

    public void setFormatoHoja(String formatoHoja) {
        this.formatoHoja = formatoHoja;
    }

    public Long getPliegosBobinas() {
        return pliegosBobinas;
    }

    public void setPliegosBobinas(Long pliegosBobinas) {
        this.pliegosBobinas = pliegosBobinas;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getFormatosPapel() {
        return formatosPapel;
    }

    public void setFormatosPapel(String formatosPapel) {
        this.formatosPapel = formatosPapel;
    }

    public Long getArmado() {
        return armado;
    }

    public void setArmado(Long armado) {
        this.armado = armado;
    }

    public Long getMaterialMaculatura() {
        return materialMaculatura;
    }

    public void setMaterialMaculatura(Long materialMaculatura) {
        this.materialMaculatura = materialMaculatura;
    }

    public Long getCantidadPlacas() {
        return cantidadPlacas;
    }

    public void setCantidadPlacas(Long cantidadPlacas) {
        this.cantidadPlacas = cantidadPlacas;
    }

    public Long getNPalet() {
        return nPalet;
    }

    public void setNPalet(Long nPalet) {
        this.nPalet = nPalet;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getResponsableDiseno() {
        return responsableDiseno;
    }

    public void setResponsableDiseno(String responsableDiseno) {
        this.responsableDiseno = responsableDiseno;
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
        if (!(object instanceof TProduccionPreimpresos)) {
            return false;
        }
        TProduccionPreimpresos other = (TProduccionPreimpresos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TProduccionPreimpresos[ id=" + id + " ]";
    }
    
}
