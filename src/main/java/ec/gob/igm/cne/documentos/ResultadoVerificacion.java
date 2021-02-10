/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.documentos;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase para contener los resultados parciales de todas las validaciones hechas al código
 * de barras de un Documento, así como también el resultado global de la validación
 * 
 */
public class ResultadoVerificacion {
    private boolean resultadoGlobal;
    
    /*
    Representa la lista de validaciones hechas al código de barras de un Documento.
    La clave es el nombre de la verificación y el valor es el resultado de la misma
    */
    Map<String,Boolean> listaResultados;

    public ResultadoVerificacion() {
        listaResultados = new HashMap<>();
    }
    
    /**
     * Añade el resultado de una evaluación a la lista de evaluciones existente
     * @param test nombre de la verificación realizada
     * @param resultado resultado booleano de la misma verificación realizada
     */
    public void putResultado(String test, boolean resultado){
        listaResultados.put(test, resultado);
    }
    
    /**
     * Calcula el resultado global de todas la validaciones contenidas
     * en la listaResultados, comparandolos con el operador AND
     * @return true: si todos los resultados en la lista de evaluaciones son true
     *         false: en caso contrario
     */
    public boolean calcularResultadoGlobal(){
        resultadoGlobal = true;
        for (Map.Entry<String, Boolean> entry : listaResultados.entrySet()) {
            resultadoGlobal = resultadoGlobal && entry.getValue();
        }
        return resultadoGlobal;
    }
    
    /**
     * Devuelve una cadena de texto con todos los resultados cuya verificación
     * fue desfavorable
     * @return cadena de texto con todos los resultados de valor False 
     */
    public String getErrores(){
        String str = new String();
        for (Map.Entry<String, Boolean> entry : listaResultados.entrySet()) {
            if(!entry.getValue()){
//                str = str + entry.getKey()+": error\n";
                str = str+"X "+entry.getKey()+"\n";
            }            
        }
        return str;
    }
    
    @Override
    public String toString(){
        String str = "GLOBAL: "+(resultadoGlobal?"Ok":"Error")+"\n";
        for (Map.Entry<String, Boolean> entry : listaResultados.entrySet()) {
            str = str + entry.getKey()+": "+(entry.getValue()?"Ok":"Error")+"\n";
        }
        return str;
    }

    public boolean getResultadoGlobal() {
        return resultadoGlobal;
    }

    public void setResultadoGlobal(boolean resultadoGlobal) {
        this.resultadoGlobal = resultadoGlobal;
    }

    public Map<String, Boolean> getListaResultados() {
        return listaResultados;
    }

    public void setListaResultados(Map<String, Boolean> listaResultados) {
        this.listaResultados = listaResultados;
    }
    
}
