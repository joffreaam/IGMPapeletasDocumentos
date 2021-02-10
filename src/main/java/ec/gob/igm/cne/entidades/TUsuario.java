/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "T_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUsuario.findAll", query = "SELECT t FROM TUsuario t")
    , @NamedQuery(name = "TUsuario.findByIdUsuario", query = "SELECT t FROM TUsuario t WHERE t.idUsuario = :idUsuario")
    , @NamedQuery(name = "TUsuario.findByUsuario", query = "SELECT t FROM TUsuario t WHERE t.usuario = :usuario")
    , @NamedQuery(name = "TUsuario.findByPassword", query = "SELECT t FROM TUsuario t WHERE t.password = :password")
    , @NamedQuery(name = "TUsuario.findByPerfil", query = "SELECT t FROM TUsuario t WHERE t.perfil = :perfil")
    , @NamedQuery(name = "TUsuario.findByNombreCompleto", query = "SELECT t FROM TUsuario t WHERE t.nombreCompleto = :nombreCompleto")
    , @NamedQuery(name = "TUsuario.findByDignidad", query = "SELECT t FROM TUsuario t WHERE t.dignidad = :dignidad")
    , @NamedQuery(name = "TUsuario.findByIdDignidad", query = "SELECT t FROM TUsuario t WHERE t.idDignidad = :idDignidad")
    , @NamedQuery(name = "TUsuario.findByBodega", query = "SELECT t FROM TUsuario t WHERE t.bodega = :bodega")
    , @NamedQuery(name = "TUsuario.findByUsuario&Passwod", query = "SELECT t FROM TUsuario t WHERE t.usuario = :usuario and t.password = :password")})
public class TUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
//private BigDecimal idUsuario;    
    private BigDecimal idUsuario;
    @Column(name = "USUARIO")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PERFIL")
    private String perfil;
    @Column(name = "NOMBRE_COMPLETO")
    private String nombreCompleto;
    @Column(name = "DIGNIDAD")
    private String dignidad;
    @Column(name = "ID_DIGNIDAD")
    private Short idDignidad;
    @Column(name = "BODEGA")
    private BigInteger bodega;
    @OneToMany(mappedBy = "idUsuario")
    private List<TDignidadXUsuario> tDignidadXUsuarioList;

    public TUsuario() {
    }

    public TUsuario(BigDecimal idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TUsuario(BigDecimal idUsuario, String password) {
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

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
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

    public Short getIdDignidad() {
        return idDignidad;
    }

    public void setIdDignidad(Short idDignidad) {
        this.idDignidad = idDignidad;
    }

    public BigInteger getBodega() {
        return bodega;
    }

    public void setBodega(BigInteger bodega) {
        this.bodega = bodega;
    }

    @XmlTransient
    public List<TDignidadXUsuario> getTDignidadXUsuarioList() {
        return tDignidadXUsuarioList;
    }

    public void setTDignidadXUsuarioList(List<TDignidadXUsuario> tDignidadXUsuarioList) {
        this.tDignidadXUsuarioList = tDignidadXUsuarioList;
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
        if (!(object instanceof TUsuario)) {
            return false;
        }
        TUsuario other = (TUsuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Id:" + idUsuario
                + "\nUsuario:" + usuario
                + "\nNombre:" + nombreCompleto
                + "\nPerfil:" + perfil
                + "\nDignidad:" + dignidad
                + "\nBodega:" + bodega;
    }

}
