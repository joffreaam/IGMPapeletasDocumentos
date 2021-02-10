/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.entidades;

import java.io.Serializable;
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
@Table(name = "T_JUNTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TJunta.findAll", query = "SELECT t FROM TJunta t")
    , @NamedQuery(name = "TJunta.findByCodProvincia", query = "SELECT t FROM TJunta t WHERE t.tJuntaPK.codProvincia = :codProvincia")
    , @NamedQuery(name = "TJunta.findByCodCanton", query = "SELECT t FROM TJunta t WHERE t.tJuntaPK.codCanton = :codCanton")
    , @NamedQuery(name = "TJunta.findByCodParroquia", query = "SELECT t FROM TJunta t WHERE t.tJuntaPK.codParroquia = :codParroquia")
    , @NamedQuery(name = "TJunta.findByCodZona", query = "SELECT t FROM TJunta t WHERE t.tJuntaPK.codZona = :codZona")
    , @NamedQuery(name = "TJunta.findBySexo", query = "SELECT t FROM TJunta t WHERE t.tJuntaPK.sexo = :sexo")
    , @NamedQuery(name = "TJunta.findByJunta", query = "SELECT t FROM TJunta t WHERE t.tJuntaPK.junta = :junta")
    , @NamedQuery(name = "TJunta.findByNumeleJunta", query = "SELECT t FROM TJunta t WHERE t.numeleJunta = :numeleJunta")
    , @NamedQuery(name = "TJunta.findByStatusJunta", query = "SELECT t FROM TJunta t WHERE t.statusJunta = :statusJunta")
    , @NamedQuery(name = "TJunta.findByCedpriJunta", query = "SELECT t FROM TJunta t WHERE t.cedpriJunta = :cedpriJunta")
    , @NamedQuery(name = "TJunta.findByDigpriJunta", query = "SELECT t FROM TJunta t WHERE t.digpriJunta = :digpriJunta")
    , @NamedQuery(name = "TJunta.findByNompriJunta", query = "SELECT t FROM TJunta t WHERE t.nompriJunta = :nompriJunta")
    , @NamedQuery(name = "TJunta.findByCedultJunta", query = "SELECT t FROM TJunta t WHERE t.cedultJunta = :cedultJunta")
    , @NamedQuery(name = "TJunta.findByDigultJunta", query = "SELECT t FROM TJunta t WHERE t.digultJunta = :digultJunta")
    , @NamedQuery(name = "TJunta.findByNomultJunta", query = "SELECT t FROM TJunta t WHERE t.nomultJunta = :nomultJunta")
    , @NamedQuery(name = "TJunta.findByCodProceso", query = "SELECT t FROM TJunta t WHERE t.codProceso = :codProceso")
    , @NamedQuery(name = "TJunta.findByCodActJunta", query = "SELECT t FROM TJunta t WHERE t.codActJunta = :codActJunta")
    , @NamedQuery(name = "TJunta.findByCod1", query = "SELECT t FROM TJunta t WHERE t.cod1 = :cod1")
    , @NamedQuery(name = "TJunta.findByCod2", query = "SELECT t FROM TJunta t WHERE t.cod2 = :cod2")
    , @NamedQuery(name = "TJunta.findByCod3", query = "SELECT t FROM TJunta t WHERE t.cod3 = :cod3")
    , @NamedQuery(name = "TJunta.findByCod4", query = "SELECT t FROM TJunta t WHERE t.cod4 = :cod4")
    , @NamedQuery(name = "TJunta.findByCod5", query = "SELECT t FROM TJunta t WHERE t.cod5 = :cod5")
    , @NamedQuery(name = "TJunta.findByCod6", query = "SELECT t FROM TJunta t WHERE t.cod6 = :cod6")
    , @NamedQuery(name = "TJunta.findByCod7", query = "SELECT t FROM TJunta t WHERE t.cod7 = :cod7")
    , @NamedQuery(name = "TJunta.findByCod8", query = "SELECT t FROM TJunta t WHERE t.cod8 = :cod8")
    , @NamedQuery(name = "TJunta.findByCod9", query = "SELECT t FROM TJunta t WHERE t.cod9 = :cod9")
    , @NamedQuery(name = "TJunta.findByCod10", query = "SELECT t FROM TJunta t WHERE t.cod10 = :cod10")
    , @NamedQuery(name = "TJunta.findByCod11", query = "SELECT t FROM TJunta t WHERE t.cod11 = :cod11")
    , @NamedQuery(name = "TJunta.findByCod12", query = "SELECT t FROM TJunta t WHERE t.cod12 = :cod12")
    , @NamedQuery(name = "TJunta.findByCodCircunscripcion", query = "SELECT t FROM TJunta t WHERE t.tJuntaPK.codCircunscripcion = :codCircunscripcion")})
public class TJunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TJuntaPK tJuntaPK;
    @Column(name = "NUMELE_JUNTA")
    private Short numeleJunta;
    @Column(name = "STATUS_JUNTA")
    private String statusJunta;
    @Column(name = "CEDPRI_JUNTA")
    private String cedpriJunta;
    @Column(name = "DIGPRI_JUNTA")
    private Short digpriJunta;
    @Column(name = "NOMPRI_JUNTA")
    private String nompriJunta;
    @Column(name = "CEDULT_JUNTA")
    private String cedultJunta;
    @Column(name = "DIGULT_JUNTA")
    private Short digultJunta;
    @Column(name = "NOMULT_JUNTA")
    private String nomultJunta;
    @Column(name = "COD_PROCESO")
    private String codProceso;
    @Column(name = "COD_ACT_JUNTA")
    private String codActJunta;
    @Column(name = "COD1")
    private String cod1;
    @Column(name = "COD2")
    private String cod2;
    @Column(name = "COD3")
    private String cod3;
    @Column(name = "COD4")
    private String cod4;
    @Column(name = "COD5")
    private String cod5;
    @Column(name = "COD6")
    private String cod6;
    @Column(name = "COD7")
    private String cod7;
    @Column(name = "COD8")
    private String cod8;
    @Column(name = "COD9")
    private String cod9;
    @Column(name = "COD10")
    private String cod10;
    @Column(name = "COD11")
    private String cod11;
    @Column(name = "COD12")
    private String cod12;

    public TJunta() {
    }

    public TJunta(TJuntaPK tJuntaPK) {
        this.tJuntaPK = tJuntaPK;
    }

    public TJunta(short codProvincia, short codCanton, short codParroquia, short codZona, String sexo, short junta, String codCircunscripcion) {
        this.tJuntaPK = new TJuntaPK(codProvincia, codCanton, codParroquia, codZona, sexo, junta, codCircunscripcion);
    }

    public TJuntaPK getTJuntaPK() {
        return tJuntaPK;
    }

    public void setTJuntaPK(TJuntaPK tJuntaPK) {
        this.tJuntaPK = tJuntaPK;
    }

    public Short getNumeleJunta() {
        return numeleJunta;
    }

    public void setNumeleJunta(Short numeleJunta) {
        this.numeleJunta = numeleJunta;
    }

    public String getStatusJunta() {
        return statusJunta;
    }

    public void setStatusJunta(String statusJunta) {
        this.statusJunta = statusJunta;
    }

    public String getCedpriJunta() {
        return cedpriJunta;
    }

    public void setCedpriJunta(String cedpriJunta) {
        this.cedpriJunta = cedpriJunta;
    }

    public Short getDigpriJunta() {
        return digpriJunta;
    }

    public void setDigpriJunta(Short digpriJunta) {
        this.digpriJunta = digpriJunta;
    }

    public String getNompriJunta() {
        return nompriJunta;
    }

    public void setNompriJunta(String nompriJunta) {
        this.nompriJunta = nompriJunta;
    }

    public String getCedultJunta() {
        return cedultJunta;
    }

    public void setCedultJunta(String cedultJunta) {
        this.cedultJunta = cedultJunta;
    }

    public Short getDigultJunta() {
        return digultJunta;
    }

    public void setDigultJunta(Short digultJunta) {
        this.digultJunta = digultJunta;
    }

    public String getNomultJunta() {
        return nomultJunta;
    }

    public void setNomultJunta(String nomultJunta) {
        this.nomultJunta = nomultJunta;
    }

    public String getCodProceso() {
        return codProceso;
    }

    public void setCodProceso(String codProceso) {
        this.codProceso = codProceso;
    }

    public String getCodActJunta() {
        return codActJunta;
    }

    public void setCodActJunta(String codActJunta) {
        this.codActJunta = codActJunta;
    }

    public String getCod1() {
        return cod1;
    }

    public void setCod1(String cod1) {
        this.cod1 = cod1;
    }

    public String getCod2() {
        return cod2;
    }

    public void setCod2(String cod2) {
        this.cod2 = cod2;
    }

    public String getCod3() {
        return cod3;
    }

    public void setCod3(String cod3) {
        this.cod3 = cod3;
    }

    public String getCod4() {
        return cod4;
    }

    public void setCod4(String cod4) {
        this.cod4 = cod4;
    }

    public String getCod5() {
        return cod5;
    }

    public void setCod5(String cod5) {
        this.cod5 = cod5;
    }

    public String getCod6() {
        return cod6;
    }

    public void setCod6(String cod6) {
        this.cod6 = cod6;
    }

    public String getCod7() {
        return cod7;
    }

    public void setCod7(String cod7) {
        this.cod7 = cod7;
    }

    public String getCod8() {
        return cod8;
    }

    public void setCod8(String cod8) {
        this.cod8 = cod8;
    }

    public String getCod9() {
        return cod9;
    }

    public void setCod9(String cod9) {
        this.cod9 = cod9;
    }

    public String getCod10() {
        return cod10;
    }

    public void setCod10(String cod10) {
        this.cod10 = cod10;
    }

    public String getCod11() {
        return cod11;
    }

    public void setCod11(String cod11) {
        this.cod11 = cod11;
    }

    public String getCod12() {
        return cod12;
    }

    public void setCod12(String cod12) {
        this.cod12 = cod12;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tJuntaPK != null ? tJuntaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TJunta)) {
            return false;
        }
        TJunta other = (TJunta) object;
        if ((this.tJuntaPK == null && other.tJuntaPK != null) || (this.tJuntaPK != null && !this.tJuntaPK.equals(other.tJuntaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.igm.cne.entidades.TJunta[ tJuntaPK=" + tJuntaPK + " ]";
    }
    
}
