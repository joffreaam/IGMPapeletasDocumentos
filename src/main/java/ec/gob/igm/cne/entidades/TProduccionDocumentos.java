/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "T_PRODUCCION_DOCUMENTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TProduccionDocumentos.findAll", query = "SELECT t FROM TProduccionDocumentos t")
    , @NamedQuery(name = "TProduccionDocumentos.findByIdRegistro", query = "SELECT t FROM TProduccionDocumentos t WHERE t.tProduccionDocumentosPK.idRegistro = :idRegistro")
    , @NamedQuery(name = "TProduccionDocumentos.findByIdCircunscripcion", query = "SELECT t FROM TProduccionDocumentos t WHERE t.idCircunscripcion = :idCircunscripcion")
    , @NamedQuery(name = "TProduccionDocumentos.findByFechaReg", query = "SELECT t FROM TProduccionDocumentos t WHERE t.tProduccionDocumentosPK.fechaReg = :fechaReg")
    , @NamedQuery(name = "TProduccionDocumentos.findByDocumentos", query = "SELECT t FROM TProduccionDocumentos t WHERE t.documentos = :documentos")
    , @NamedQuery(name = "TProduccionDocumentos.findByUsuario", query = "SELECT t FROM TProduccionDocumentos t WHERE t.usuario = :usuario")
    , @NamedQuery(name = "TProduccionDocumentos.findByProceso", query = "SELECT t FROM TProduccionDocumentos t WHERE t.proceso = :proceso")
    , @NamedQuery(name = "TProduccionDocumentos.findByTurno", query = "SELECT t FROM TProduccionDocumentos t WHERE t.turno = :turno")
    , @NamedQuery(name = "TProduccionDocumentos.findBySupervisor", query = "SELECT t FROM TProduccionDocumentos t WHERE t.supervisor = :supervisor")
    , @NamedQuery(name = "TProduccionDocumentos.findByIdKit", query = "SELECT t FROM TProduccionDocumentos t WHERE t.idKit = :idKit")})
public class TProduccionDocumentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TProduccionDocumentosPK tProduccionDocumentosPK;
    @Column(name = "ID_CIRCUNSCRIPCION")
    private Short idCircunscripcion;
    @Column(name = "DOCUMENTOS")
    private Long documentos;
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "PROCESO")
    private String proceso;
    @Column(name = "TURNO")
    private Short turno;
    @Column(name = "SUPERVISOR")
    private String supervisor;
    @Column(name = "ID_KIT")
    private BigInteger idKit;
    @JoinColumn(name = "ID_PROVINCIA", referencedColumnName = "ID_PROVINCIA")
    @ManyToOne
    private TProvincia idProvincia;

    public TProduccionDocumentos() {
    }

    public TProduccionDocumentos(TProduccionDocumentosPK tProduccionDocumentosPK) {
        this.tProduccionDocumentosPK = tProduccionDocumentosPK;
    }

    public TProduccionDocumentos(long idRegistro, Date fechaReg) {
        this.tProduccionDocumentosPK = new TProduccionDocumentosPK(idRegistro, fechaReg);
    }

    public TProduccionDocumentosPK getTProduccionDocumentosPK() {
        return tProduccionDocumentosPK;
    }

    public void setTProduccionDocumentosPK(TProduccionDocumentosPK tProduccionDocumentosPK) {
        this.tProduccionDocumentosPK = tProduccionDocumentosPK;
    }

    public Short getIdCircunscripcion() {
        return idCircunscripcion;
    }

    public void setIdCircunscripcion(Short idCircunscripcion) {
        this.idCircunscripcion = idCircunscripcion;
    }

    public Long getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Long documentos) {
        this.documentos = documentos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public Short getTurno() {
        return turno;
    }

    public void setTurno(Short turno) {
        this.turno = turno;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public BigInteger getIdKit() {
        return idKit;
    }

    public void setIdKit(BigInteger idKit) {
        this.idKit = idKit;
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
        hash += (tProduccionDocumentosPK != null ? tProduccionDocumentosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TProduccionDocumentos)) {
            return false;
        }
        TProduccionDocumentos other = (TProduccionDocumentos) object;
        if ((this.tProduccionDocumentosPK == null && other.tProduccionDocumentosPK != null) || (this.tProduccionDocumentosPK != null && !this.tProduccionDocumentosPK.equals(other.tProduccionDocumentosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TProduccionDocumentos[ tProduccionDocumentosPK=" + tProduccionDocumentosPK + " ]";
    }
    
}
