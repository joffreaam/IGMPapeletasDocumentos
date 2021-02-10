/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "T_CIRCUNSCRIPCION_CANTON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCircunscripcionCanton.findAll", query = "SELECT t FROM TCircunscripcionCanton t")
    , @NamedQuery(name = "TCircunscripcionCanton.findByIdProvincia", query = "SELECT t FROM TCircunscripcionCanton t WHERE t.tCircunscripcionCantonPK.idProvincia = :idProvincia")
    , @NamedQuery(name = "TCircunscripcionCanton.findByIdCanton", query = "SELECT t FROM TCircunscripcionCanton t WHERE t.tCircunscripcionCantonPK.idCanton = :idCanton")
    , @NamedQuery(name = "TCircunscripcionCanton.findByIdCircunscripcion", query = "SELECT t FROM TCircunscripcionCanton t WHERE t.tCircunscripcionCantonPK.idCircunscripcion = :idCircunscripcion")
    , @NamedQuery(name = "TCircunscripcionCanton.findByDescripcion", query = "SELECT t FROM TCircunscripcionCanton t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TCircunscripcionCanton.findByNumjunCircunscripcion", query = "SELECT t FROM TCircunscripcionCanton t WHERE t.numjunCircunscripcion = :numjunCircunscripcion")
    , @NamedQuery(name = "TCircunscripcionCanton.findByNumjunhomCircunscripcion", query = "SELECT t FROM TCircunscripcionCanton t WHERE t.numjunhomCircunscripcion = :numjunhomCircunscripcion")
    , @NamedQuery(name = "TCircunscripcionCanton.findByNumjunmujCircunscripcion", query = "SELECT t FROM TCircunscripcionCanton t WHERE t.numjunmujCircunscripcion = :numjunmujCircunscripcion")
    , @NamedQuery(name = "TCircunscripcionCanton.findByNumeleCircunscripcion", query = "SELECT t FROM TCircunscripcionCanton t WHERE t.numeleCircunscripcion = :numeleCircunscripcion")
    , @NamedQuery(name = "TCircunscripcionCanton.findByNumelehomCircunscripcion", query = "SELECT t FROM TCircunscripcionCanton t WHERE t.numelehomCircunscripcion = :numelehomCircunscripcion")
    , @NamedQuery(name = "TCircunscripcionCanton.findByNumelemujCircunscripcion", query = "SELECT t FROM TCircunscripcionCanton t WHERE t.numelemujCircunscripcion = :numelemujCircunscripcion")})
public class TCircunscripcionCanton implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TCircunscripcionCantonPK tCircunscripcionCantonPK;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "NUMJUN_CIRCUNSCRIPCION")
    private BigInteger numjunCircunscripcion;
    @Column(name = "NUMJUNHOM_CIRCUNSCRIPCION")
    private BigInteger numjunhomCircunscripcion;
    @Column(name = "NUMJUNMUJ_CIRCUNSCRIPCION")
    private BigInteger numjunmujCircunscripcion;
    @Column(name = "NUMELE_CIRCUNSCRIPCION")
    private BigInteger numeleCircunscripcion;
    @Column(name = "NUMELEHOM_CIRCUNSCRIPCION")
    private BigInteger numelehomCircunscripcion;
    @Column(name = "NUMELEMUJ_CIRCUNSCRIPCION")
    private BigInteger numelemujCircunscripcion;

    public TCircunscripcionCanton() {
    }

    public TCircunscripcionCanton(TCircunscripcionCantonPK tCircunscripcionCantonPK) {
        this.tCircunscripcionCantonPK = tCircunscripcionCantonPK;
    }

    public TCircunscripcionCanton(int idProvincia, int idCanton, int idCircunscripcion) {
        this.tCircunscripcionCantonPK = new TCircunscripcionCantonPK(idProvincia, idCanton, idCircunscripcion);
    }

    public TCircunscripcionCantonPK getTCircunscripcionCantonPK() {
        return tCircunscripcionCantonPK;
    }

    public void setTCircunscripcionCantonPK(TCircunscripcionCantonPK tCircunscripcionCantonPK) {
        this.tCircunscripcionCantonPK = tCircunscripcionCantonPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigInteger getNumjunCircunscripcion() {
        return numjunCircunscripcion;
    }

    public void setNumjunCircunscripcion(BigInteger numjunCircunscripcion) {
        this.numjunCircunscripcion = numjunCircunscripcion;
    }

    public BigInteger getNumjunhomCircunscripcion() {
        return numjunhomCircunscripcion;
    }

    public void setNumjunhomCircunscripcion(BigInteger numjunhomCircunscripcion) {
        this.numjunhomCircunscripcion = numjunhomCircunscripcion;
    }

    public BigInteger getNumjunmujCircunscripcion() {
        return numjunmujCircunscripcion;
    }

    public void setNumjunmujCircunscripcion(BigInteger numjunmujCircunscripcion) {
        this.numjunmujCircunscripcion = numjunmujCircunscripcion;
    }

    public BigInteger getNumeleCircunscripcion() {
        return numeleCircunscripcion;
    }

    public void setNumeleCircunscripcion(BigInteger numeleCircunscripcion) {
        this.numeleCircunscripcion = numeleCircunscripcion;
    }

    public BigInteger getNumelehomCircunscripcion() {
        return numelehomCircunscripcion;
    }

    public void setNumelehomCircunscripcion(BigInteger numelehomCircunscripcion) {
        this.numelehomCircunscripcion = numelehomCircunscripcion;
    }

    public BigInteger getNumelemujCircunscripcion() {
        return numelemujCircunscripcion;
    }

    public void setNumelemujCircunscripcion(BigInteger numelemujCircunscripcion) {
        this.numelemujCircunscripcion = numelemujCircunscripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tCircunscripcionCantonPK != null ? tCircunscripcionCantonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCircunscripcionCanton)) {
            return false;
        }
        TCircunscripcionCanton other = (TCircunscripcionCanton) object;
        if ((this.tCircunscripcionCantonPK == null && other.tCircunscripcionCantonPK != null) || (this.tCircunscripcionCantonPK != null && !this.tCircunscripcionCantonPK.equals(other.tCircunscripcionCantonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TCircunscripcionCanton[ tCircunscripcionCantonPK=" + tCircunscripcionCantonPK + " ]";
    }
    
}
