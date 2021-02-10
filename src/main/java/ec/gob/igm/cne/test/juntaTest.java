/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.test;

import ec.gob.igm.cne.dao.TJuntaDAO;
import ec.gob.igm.cne.documentos.DatosJunta;

/**
 *
 * @author desarrollo
 */
public class juntaTest {
    public static void main(String[] args){
        short codProvincia = 4;
        short codCanton = 5;
        short codParroquia = 5415;
        short codZona = 61;
        short junta = 1;
        String codCircunscripcion = "0";
        String sexo = "M";        
        TJuntaDAO juntaDAO = new TJuntaDAO();        
        DatosJunta datosJunta = juntaDAO.obtenerDatosDeJunta(codProvincia, codCanton, codParroquia, codZona, junta, codCircunscripcion, sexo);
        System.out.println(datosJunta.toString());
    }
}
