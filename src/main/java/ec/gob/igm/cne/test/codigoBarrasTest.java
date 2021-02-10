/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.test;

import ec.gob.igm.cne.dao.TListadoDocumentosDAO;
import ec.gob.igm.cne.documentos.CodigoBarras;
import ec.gob.igm.cne.documentos.ResultadoVerificacion;
import ec.gob.igm.cne.documentos.Verificador;
import ec.gob.igm.cne.entidades.TJuntaPK;

/**
 *
 * @author desarrollo
 */
public class codigoBarrasTest {
    public static void main(String[] args){
        short codProvincia = 4;
        short codCanton = 5;
        short codParroquia = 5415;
        short codZona = 61;
        short junta = 1;
        String codCircunscripcion = "0";
        String sexo = "M";
        TJuntaPK juntaPK = new TJuntaPK(codProvincia, codCanton, codParroquia, codZona, sexo, junta, codCircunscripcion);
//        
//        String nombreDoc = "Listado de Materiales (Urbano/Rural)";
//        TListadoDocumentosDAO listadoDocumentosDAO = new TListadoDocumentosDAO();
//        CodigoBarras codigoBarras = listadoDocumentosDAO.listarComponentesCodigo(nombreDoc);
//        codigoBarras.setDatosJunta(juntaPK);
//        System.out.println(codigoBarras.getComponentes().toString());
        
//        ResultadoVerificacion result = Verificador.validacionParcialBarras("Listado de Materiales (Urbano/Rural)", 27, "115010000000000000000000000");
        String nombreDoc = "Listado de Materiales (Urbano/Rural)";
        String codigoBarras = "1150104005005415610001M0102";
        String paginaActual = "01";
        String totalPaginas = "02";
//        ResultadoVerificacion rv = Verificador.validarCodigoBarras(nombreDoc, codigoBarras, juntaPK, paginaActual, totalPaginas);
        
//        CodigoBarras objCodigoBarras = new CodigoBarras();
        TListadoDocumentosDAO tldDAO = new TListadoDocumentosDAO();
//        CodigoBarras objCodigoBarras = tldDAO.listarComponentesCodigo(nombreDoc);
//        ResultadoVerificacion rv = Verificador.validacionParcialBarras(nombreDoc, 27, codigoBarras, objCodigoBarras);
//        rv.calcularResultadoGlobal();
//        System.out.println(rv.toString());
//        objCodigoBarras = Verificador.obtenerJunta(nombreDoc, codigoBarras, objCodigoBarras);
//
////        rv.calcularResultadoGlobal();
//        System.out.println(rv.toString());
//        System.out.println(objCodigoBarras.getComponentes());
    }
}
