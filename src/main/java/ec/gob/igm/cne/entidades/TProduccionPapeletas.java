/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "T_PRODUCCION_PAPELETAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TProduccionPapeletas.findAll", query = "SELECT t FROM TProduccionPapeletas t")
    , @NamedQuery(name = "TProduccionPapeletas.findByIdRegistro", query = "SELECT t FROM TProduccionPapeletas t WHERE t.idRegistro = :idRegistro")
    , @NamedQuery(name = "TProduccionPapeletas.findByIdProvincia", query = "SELECT t FROM TProduccionPapeletas t WHERE t.idProvincia = :idProvincia")
    , @NamedQuery(name = "TProduccionPapeletas.findByIdCircunscripcion", query = "SELECT t FROM TProduccionPapeletas t WHERE t.idCircunscripcion = :idCircunscripcion")
    , @NamedQuery(name = "TProduccionPapeletas.findByFormato", query = "SELECT t FROM TProduccionPapeletas t WHERE t.formato = :formato")
    , @NamedQuery(name = "TProduccionPapeletas.findByBobina", query = "SELECT t FROM TProduccionPapeletas t WHERE t.bobina = :bobina")
    , @NamedQuery(name = "TProduccionPapeletas.findByPliegos", query = "SELECT t FROM TProduccionPapeletas t WHERE t.pliegos = :pliegos")
    , @NamedQuery(name = "TProduccionPapeletas.findByFechaReg", query = "SELECT t FROM TProduccionPapeletas t WHERE t.fechaReg = :fechaReg")
    , @NamedQuery(name = "TProduccionPapeletas.findByTurno", query = "SELECT t FROM TProduccionPapeletas t WHERE t.turno = :turno")
    , @NamedQuery(name = "TProduccionPapeletas.findByOperador", query = "SELECT t FROM TProduccionPapeletas t WHERE t.operador = :operador")
    , @NamedQuery(name = "TProduccionPapeletas.findByPapeletas", query = "SELECT t FROM TProduccionPapeletas t WHERE t.papeletas = :papeletas")
    , @NamedQuery(name = "TProduccionPapeletas.findByUsuario", query = "SELECT t FROM TProduccionPapeletas t WHERE t.usuario = :usuario")
    , @NamedQuery(name = "TProduccionPapeletas.findByCantidadReal", query = "SELECT t FROM TProduccionPapeletas t WHERE t.cantidadReal = :cantidadReal")
    , @NamedQuery(name = "TProduccionPapeletas.findByMaterialNoconforme", query = "SELECT t FROM TProduccionPapeletas t WHERE t.materialNoconforme = :materialNoconforme")
    , @NamedQuery(name = "TProduccionPapeletas.findByCantidadPlacas", query = "SELECT t FROM TProduccionPapeletas t WHERE t.cantidadPlacas = :cantidadPlacas")
    , @NamedQuery(name = "TProduccionPapeletas.findByResponsableDiseno", query = "SELECT t FROM TProduccionPapeletas t WHERE t.responsableDiseno = :responsableDiseno")
    , @NamedQuery(name = "TProduccionPapeletas.findByTipoPapel", query = "SELECT t FROM TProduccionPapeletas t WHERE t.tipoPapel = :tipoPapel")
    , @NamedQuery(name = "TProduccionPapeletas.findByArmado", query = "SELECT t FROM TProduccionPapeletas t WHERE t.armado = :armado")
    , @NamedQuery(name = "TProduccionPapeletas.findByFormatoHoja", query = "SELECT t FROM TProduccionPapeletas t WHERE t.formatoHoja = :formatoHoja")
    , @NamedQuery(name = "TProduccionPapeletas.findByObservaciones", query = "SELECT t FROM TProduccionPapeletas t WHERE t.observaciones = :observaciones")
    , @NamedQuery(name = "TProduccionPapeletas.findByNPalet", query = "SELECT t FROM TProduccionPapeletas t WHERE t.nPalet = :nPalet")
    , @NamedQuery(name = "TProduccionPapeletas.findByPapeletasMaculatura", query = "SELECT t FROM TProduccionPapeletas t WHERE t.papeletasMaculatura = :papeletasMaculatura")
    , @NamedQuery(name = "TProduccionPapeletas.findByLppl", query = "SELECT t FROM TProduccionPapeletas t WHERE t.lppl = :lppl")})
public class TProduccionPapeletas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private Long idRegistro;
    @Column(name = "ID_PROVINCIA")
    private Integer idProvincia;
    @Column(name = "ID_CIRCUNSCRIPCION")
    private Short idCircunscripcion;
    @Column(name = "FORMATO")
    private String formato;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BOBINA")
    private BigDecimal bobina;
    @Column(name = "PLIEGOS")
    private Long pliegos;
    @Basic(optional = false)
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    @Column(name = "TURNO")
    private Long turno;
    @Column(name = "OPERADOR")
    private String operador;
    @Column(name = "PAPELETAS")
    private Long papeletas;
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "CANTIDAD_REAL")
    private Long cantidadReal;
    @Column(name = "MATERIAL_NOCONFORME")
    private BigInteger materialNoconforme;
    @Column(name = "CANTIDAD_PLACAS")
    private Long cantidadPlacas;
    @Column(name = "RESPONSABLE_DISENO")
    private String responsableDiseno;
    @Column(name = "TIPO_PAPEL")
    private String tipoPapel;
    @Column(name = "ARMADO")
    private Long armado;
    @Column(name = "FORMATO_HOJA")
    private String formatoHoja;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "N_PALET")
    private Long nPalet;
    @Column(name = "PAPELETAS_MACULATURA")
    private Long papeletasMaculatura;
    @Column(name = "LPPL")
    private Short lppl;
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

    public TProduccionPapeletas() {
    }

    public TProduccionPapeletas(Long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public TProduccionPapeletas(Long idRegistro, Date fechaReg) {
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

    public Short getIdCircunscripcion() {
        return idCircunscripcion;
    }

    public void setIdCircunscripcion(Short idCircunscripcion) {
        this.idCircunscripcion = idCircunscripcion;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public BigDecimal getBobina() {
        return bobina;
    }

    public void setBobina(BigDecimal bobina) {
        this.bobina = bobina;
    }

    public Long getPliegos() {
        return pliegos;
    }

    public void setPliegos(Long pliegos) {
        this.pliegos = pliegos;
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

    public Long getPapeletas() {
        return papeletas;
    }

    public void setPapeletas(Long papeletas) {
        this.papeletas = papeletas;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getCantidadReal() {
        return cantidadReal;
    }

    public void setCantidadReal(Long cantidadReal) {
        this.cantidadReal = cantidadReal;
    }

    public BigInteger getMaterialNoconforme() {
        return materialNoconforme;
    }

    public void setMaterialNoconforme(BigInteger materialNoconforme) {
        this.materialNoconforme = materialNoconforme;
    }

    public Long getCantidadPlacas() {
        return cantidadPlacas;
    }

    public void setCantidadPlacas(Long cantidadPlacas) {
        this.cantidadPlacas = cantidadPlacas;
    }

    public String getResponsableDiseno() {
        return responsableDiseno;
    }

    public void setResponsableDiseno(String responsableDiseno) {
        this.responsableDiseno = responsableDiseno;
    }

    public String getTipoPapel() {
        return tipoPapel;
    }

    public void setTipoPapel(String tipoPapel) {
        this.tipoPapel = tipoPapel;
    }

    public Long getArmado() {
        return armado;
    }

    public void setArmado(Long armado) {
        this.armado = armado;
    }

    public String getFormatoHoja() {
        return formatoHoja;
    }

    public void setFormatoHoja(String formatoHoja) {
        this.formatoHoja = formatoHoja;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Long getNPalet() {
        return nPalet;
    }

    public void setNPalet(Long nPalet) {
        this.nPalet = nPalet;
    }

    public Long getPapeletasMaculatura() {
        return papeletasMaculatura;
    }

    public void setPapeletasMaculatura(Long papeletasMaculatura) {
        this.papeletasMaculatura = papeletasMaculatura;
    }

    public Short getLppl() {
        return lppl;
    }

    public void setLppl(Short lppl) {
        this.lppl = lppl;
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
        if (!(object instanceof TProduccionPapeletas)) {
            return false;
        }
        TProduccionPapeletas other = (TProduccionPapeletas) object;
        if ((this.idRegistro == null && other.idRegistro != null) || (this.idRegistro != null && !this.idRegistro.equals(other.idRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TProduccionPapeletas[ idRegistro=" + idRegistro + " ]";
    }
    
}
