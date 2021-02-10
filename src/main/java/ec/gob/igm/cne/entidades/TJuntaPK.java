/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author desarrollo
 */
@Embeddable
public class TJuntaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "COD_PROVINCIA")
    private short codProvincia;
    @Basic(optional = false)
    @Column(name = "COD_CANTON")
    private short codCanton;
    @Basic(optional = false)
    @Column(name = "COD_PARROQUIA")
    private short codParroquia;
    @Basic(optional = false)
    @Column(name = "COD_ZONA")
    private short codZona;
    @Basic(optional = false)
    @Column(name = "SEXO")
    private String sexo;
    @Basic(optional = false)
    @Column(name = "JUNTA")
    private short junta;
    @Basic(optional = false)
    @Column(name = "COD_CIRCUNSCRIPCION")
    private String codCircunscripcion;

    public TJuntaPK() {
    }

    public TJuntaPK(short codProvincia, short codCanton, short codParroquia, short codZona, String sexo, short junta, String codCircunscripcion) {
        this.codProvincia = codProvincia;
        this.codCanton = codCanton;
        this.codParroquia = codParroquia;
        this.codZona = codZona;
        this.sexo = sexo;
        this.junta = junta;
        this.codCircunscripcion = codCircunscripcion;
    }

    public short getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(short codProvincia) {
        this.codProvincia = codProvincia;
    }

    public short getCodCanton() {
        return codCanton;
    }

    public void setCodCanton(short codCanton) {
        this.codCanton = codCanton;
    }

    public short getCodParroquia() {
        return codParroquia;
    }

    public void setCodParroquia(short codParroquia) {
        this.codParroquia = codParroquia;
    }

    public short getCodZona() {
        return codZona;
    }

    public void setCodZona(short codZona) {
        this.codZona = codZona;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public short getJunta() {
        return junta;
    }

    public void setJunta(short junta) {
        this.junta = junta;
    }

    public String getCodCircunscripcion() {
        return codCircunscripcion;
    }

    public void setCodCircunscripcion(String codCircunscripcion) {
        this.codCircunscripcion = codCircunscripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codProvincia;
        hash += (int) codCanton;
        hash += (int) codParroquia;
        hash += (int) codZona;
        hash += (sexo != null ? sexo.hashCode() : 0);
        hash += (int) junta;
        hash += (codCircunscripcion != null ? codCircunscripcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TJuntaPK)) {
            return false;
        }
        TJuntaPK other = (TJuntaPK) object;
        if (this.codProvincia != other.codProvincia) {
            return false;
        }
        if (this.codCanton != other.codCanton) {
            return false;
        }
        if (this.codParroquia != other.codParroquia) {
            return false;
        }
        if (this.codZona != other.codZona) {
            return false;
        }
        if ((this.sexo == null && other.sexo != null) || (this.sexo != null && !this.sexo.equals(other.sexo))) {
            return false;
        }
        if (this.junta != other.junta) {
            return false;
        }
        if ((this.codCircunscripcion == null && other.codCircunscripcion != null) || (this.codCircunscripcion != null && !this.codCircunscripcion.equals(other.codCircunscripcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TJuntaPK[ codProvincia=" + codProvincia + ", codCanton=" + codCanton + ", codParroquia=" + codParroquia + ", codZona=" + codZona + ", sexo=" + sexo + ", junta=" + junta + ", codCircunscripcion=" + codCircunscripcion + " ]";
    }
    
}
