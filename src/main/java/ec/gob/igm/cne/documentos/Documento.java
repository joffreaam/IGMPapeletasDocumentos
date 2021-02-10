/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.documentos;

/**
 *
 * @author desarrollo
 */
public class Documento {



    private int id;
    private int orden;
    private String nombre;
    private int longCodigo;
    private CodigoBarras codigo;
    private String codigoBarrasStr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoBarrasStr() {
        return codigoBarrasStr;
    }

    public void setCodigoBarrasStr(String codigoBarrasStr) {
        this.codigoBarrasStr = codigoBarrasStr;
    }

    public CodigoBarras getCodigo() {
        return codigo;
    }

    public void setCodigo(CodigoBarras codigo) {
        this.codigo = codigo;
    }
    
    public Documento(int orden, String nombre, int longCodigo, int id){
        this.orden = orden;
        this.nombre = nombre;
        this.longCodigo = longCodigo;
        this.id = id;

    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLongCodigo() {
        return longCodigo;
    }

    public void setLongCodigo(int longCodigo) {
        this.longCodigo = longCodigo;
    }
    
    @Override
    public String toString(){
        return orden+"-"+nombre+"-"+longCodigo;
    }
}
