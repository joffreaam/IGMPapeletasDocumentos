/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.documentos;

/**
 * Representa cada componente que conforma el código de barras de un Documento
 * Electoral
 *
 */
public class ComponenteCodigoB {

    private String descripcion;
    private int longitud;
    private String valorDefecto;
    private int orden;

    public ComponenteCodigoB(String descripcion, int longitud, String valorDefecto, int orden) {
        this.descripcion = descripcion;
        this.longitud = longitud;
        this.valorDefecto = valorDefecto;
        this.orden = orden;
    }
    
    /**
     * Crea una copia de la instancia recibida. Si clona o no
     * el valor del atributo valorDefecto depende del valor de la bandera copiarValorDefecto
     * @param objBase objeto que se clonará
     * @param copiarValorDefecto especifica si se debe no copiar tambien el valor del atributo
     * valorDefecto
     */
    public ComponenteCodigoB(ComponenteCodigoB objBase, boolean copiarValorDefecto){
        this.descripcion = objBase.descripcion;
        this.longitud = objBase.longitud;
        this.orden = objBase.orden;
        if(copiarValorDefecto){
            this.valorDefecto = objBase.valorDefecto;
        }else{
            this.valorDefecto = null;
        }
    }
    
    public ComponenteCodigoB(String descripcion){
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion + ":" + valorDefecto;
    }

    /**
     * Compara dos instancias de ComponenteCodigoB en base a su atributo descripcion
     * @param o intancia de ComponenteCodigoB con la que se va a comparar
     * @return true: si las descripciones de ambas instancias es igual
     *         falso: si las descripciones no son igiales
     */
    @Override
    public boolean equals(Object o) {
        ComponenteCodigoB comp = (ComponenteCodigoB) o;
        if (comp.getDescripcion().equals(this.descripcion)) {
            return true;
        } else {
            return false;
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public String getValorDefecto() {
        return valorDefecto;
    }

    public void setValorDefecto(String valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
