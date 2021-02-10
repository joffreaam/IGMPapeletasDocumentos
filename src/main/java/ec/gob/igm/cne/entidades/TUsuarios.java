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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "T_USUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUsuarios.findAll", query = "SELECT t FROM TUsuarios t")
    , @NamedQuery(name = "TUsuarios.findByIdUsuario", query = "SELECT t FROM TUsuarios t WHERE t.idUsuario = :idUsuario")
    , @NamedQuery(name = "TUsuarios.findByUsuario", query = "SELECT t FROM TUsuarios t WHERE t.usuario = :usuario")
    , @NamedQuery(name = "TUsuarios.findByPassword", query = "SELECT t FROM TUsuarios t WHERE t.password = :password")
    , @NamedQuery(name = "TUsuarios.findByNombreCompleto", query = "SELECT t FROM TUsuarios t WHERE t.nombreCompleto = :nombreCompleto")
    , @NamedQuery(name = "TUsuarios.findByDignidad", query = "SELECT t FROM TUsuarios t WHERE t.dignidad = :dignidad")
    , @NamedQuery(name = "TUsuarios.findByBodega", query = "SELECT t FROM TUsuarios t WHERE t.bodega = :bodega")
    , @NamedQuery(name = "TUsuarios.findByPerfil", query = "SELECT t FROM TUsuarios t WHERE t.perfil = :perfil")})
public class TUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private BigDecimal idUsuario;
    @Column(name = "USUARIO")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "NOMBRE_COMPLETO")
    private String nombreCompleto;
    @Column(name = "DIGNIDAD")
    private String dignidad;
    @Column(name = "BODEGA")
    private BigInteger bodega;
    @Column(name = "PERFIL")
    private String perfil;
    @JoinColumn(name = "ID_DIGNIDAD", referencedColumnName = "COD_DIGNIDAD")
    @ManyToOne
    private TDignidad idDignidad;
    @JoinColumn(name = "ID_PERFIL", referencedColumnName = "IDPERFIL")
    @ManyToOne
    private TPerfil idPerfil;

    public TUsuarios() {
    }

    public TUsuarios(BigDecimal idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TUsuarios(BigDecimal idUsuario, String password) {
        this.idUsuario = idUsuario;
        this.password = password;
    }

    public BigDecimal getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigDecimal idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDignidad() {
        return dignidad;
    }

    public void setDignidad(String dignidad) {
        this.dignidad = dignidad;
    }

    public BigInteger getBodega() {
        return bodega;
    }

    public void setBodega(BigInteger bodega) {
        this.bodega = bodega;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public TDignidad getIdDignidad() {
        return idDignidad;
    }

    public void setIdDignidad(TDignidad idDignidad) {
        this.idDignidad = idDignidad;
    }

    public TPerfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(TPerfil idPerfil) {
        this.idPerfil = idPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUsuarios)) {
            return false;
        }
        TUsuarios other = (TUsuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TUsuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
