/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.documentos;

import ec.gob.igm.cne.entidades.TJunta;
import ec.gob.igm.cne.entidades.TJuntaPK;
import java.util.Map;

/**
 *
 * @author desarrollo
 */
public class DatosJunta {
    
    private String provincia;
    private String canton;
    private String parroquia;
    private String zona;
    private int codZona;
    private String sexo;
    private String circunscripcion;
    private String tipoParroquia;
    private String junta;
    private String barras;
    private String tag;
    private TJuntaPK juntaPK;
    private Map<String, String> codigos;
    private String estado;
    
    public DatosJunta(){        
    }        

    public Map<String, String> getCodigos() {
        return codigos;
    }

    public void setCodigos(Map<String, String> codigos) {
        this.codigos = codigos;
    }
    
    public DatosJunta(TJuntaPK juntaPK){
        this.juntaPK = juntaPK;
    }

    public DatosJunta(String provincia, String canton, String parroquia, String zona, String sexo, String circunscripcion, String tipoParroquia, String junta, int codZona, String barras, String tag, String estado, Map<String, String> codigos) {
        this.provincia = provincia;
        this.canton = canton;
        this.parroquia = parroquia;
        this.zona = zona;
        this.sexo = sexo;
        this.circunscripcion = circunscripcion;
        this.tipoParroquia = tipoParroquia;
        this.junta = junta;
        this.codZona = codZona;
        this.barras = barras;
        this.tag = tag;
        this.codigos = codigos;
        this.estado = estado; //ad por Joffre Alava
    }
  public DatosJunta(TJuntaPK juntaPK, String provincia, String canton, String parroquia, String zona, String sexo, String circunscripcion, String tipoParroquia, String junta, int codZona, String barras, String tag, String estado, Map<String, String> codigos){
        this.juntaPK = juntaPK;
        this.provincia = provincia;
        this.canton = canton;
        this.parroquia = parroquia;
        this.zona = zona;
        this.sexo = sexo;
        this.circunscripcion = circunscripcion;
        this.tipoParroquia = tipoParroquia;
        this.junta = junta;
        this.codZona = codZona;
        this.barras = barras;
        this.tag = tag;
        this.codigos = codigos;
        this.estado = estado; //ad por Joffre Alava
    }
    public TJuntaPK getJuntaPK() {
        return juntaPK;
    }

    public void setJuntaPK(TJuntaPK juntaPK) {
        this.juntaPK = juntaPK;
    }

    public String getBarras() {
        return barras;
    }

    public void setBarras(String barras) {
        this.barras = barras;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public int getCodZona() {
        return codZona;
    }

    public void setCodZona(int codZona) {
        this.codZona = codZona;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCircunscripcion() {
        return circunscripcion;
    }

    public void setCircunscripcion(String circunscripcion) {
        this.circunscripcion = circunscripcion;
    }

    public String getTipoParroquia() {
        return tipoParroquia;
    }

    public void setTipoParroquia(String tipoParroquia) {
        this.tipoParroquia = tipoParroquia;
    }

    public String getJunta() {
        return junta;
    }

    public void setJunta(String junta) {
        this.junta = junta;
    }
    //Add por Joffre
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    //Hasta aca
    @Override
    public String toString(){
//        return provincia+"-"+canton+"-"+circunscripcion+"-"+parroquia+"-"+tipoParroquia+"-"+codZona+"."+zona+"-"+junta+"-"+sexo;
        return "Junta:"+junta+"-"+sexo.substring(0, 1)+", Zona:"+codZona+", Parroquia:"+parroquia+", Cantón:"+canton+", Provincia:"+provincia+", Estado: "+estado;
    }
    
}
