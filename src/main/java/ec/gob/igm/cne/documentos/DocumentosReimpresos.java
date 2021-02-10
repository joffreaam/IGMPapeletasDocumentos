/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.documentos;

import ec.gob.igm.cne.entidades.TJuntaPK;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jkari
 */
public class DocumentosReimpresos {

    public static List<String> obtenerCodigosXJunta(TJuntaPK juntaPK) {
        //TODO: obtener lista de documentos reimpresos de la junta definida por juntaPK
//        Documento documento1 = new Documento(0, "", 0);
//        Documento documento2 = new Documento(0, "", 0);
//        List<Documento> lista = new ArrayList<>();
//        lista.add(documento1);
//        lista.add(documento2);
        List<String> listaCodigos = new ArrayList<>();
        listaCodigos.add("");
        listaCodigos.add("");
        listaCodigos.add("");
        return listaCodigos;
    }

    public static List<String> generarListaDocumentosReImpresos(TJuntaPK juntaPK){
        List<String> listaCodigos = obtenerCodigosXJunta(juntaPK);
        List<String> listaNombres = new ArrayList<>();
        for(String codigo:listaCodigos){
            String nombre = obtenerNombreDocumento(codigo);
            listaNombres.add(nombre);
        }
        return listaNombres;
    }

    public static String obtenerNombreDignidad(String idDignidad) {
        String nombreDignidad;
        switch (idDignidad) {
            case "01":
                nombreDignidad = "PRESIDENTA/E Y VICEPRESIDENTA/E";
                break;
            case "07":
                nombreDignidad = "ASAMBLEÍSTAS PROVINCIALES";
                break;
            case "09":
                nombreDignidad = "ASAMBLEÍSTAS NACIONALES";
                break;
            case "08":
                nombreDignidad = "PARLAMENTARIOS ANDINOS";
                break;
            default:
                throw new AssertionError();
        }
        return nombreDignidad;
    }

    public static String obtenerNombreTipoDocumento(String tipo) {
        String nombre = new String();
        switch (tipo) {
            case "01":
                nombre = "Listado de Materiales";
                //01 = Listado de Materiales Día del Sufragio (Voto Casa / PPL)
                break;
            case "02":
                nombre = "Listado de Materiales Día de Escrutinio";
                break;
            case "03":
                nombre = "Padrón Electoral";
                break;
            case "04":
                nombre = "Acta de Instalación T1";
                //04 = Actas de Instalación C1 (Voto Casa / PPL)
                //04 = Acta de Apertura
                break;
            case "05":
                nombre = "Acta de Instalación C1";
                //05 = Acta de Instalación P1 (Voto Casa / PPL / EXT)
                break;
            case "06":
                nombre = "Acta de Instalación Conocimiento Público";
                break;
            case "07":
                nombre = "Constancia de Visita";
                break;
            case "08":
                nombre = "Acta de Cierre";
                break;
            case "09":
                nombre = "Borrador de Escrutinio";
                break;
            case "10":
                nombre = "Acta de Escrutinio (T1, T2, T3, T4) Amarillo";
                break;
            case "11":
                nombre = "Acta de Escrutinio (P1) Rojo";
                break;
            case "12":
                nombre = "Acta de Escrutinio para Conocimiento Público y Resumen de Resultados (Colocar Pared/Cónsul)";
                break;
            case "13":
                nombre = "Acta de Escrutinio para Conocimiento Público y Resumen de Resultados (Segundo Ejemplar)";
                break;
            case "14":
                nombre = "Acta de Escrutinio para Conocimiento Público y Resumen de Resultados (Tercer Ejemplar)";
                break;
            case "16":
                nombre = "Formulario de Recibo Presidenta/e de la JRV al   Coordinador de Mesa – Cónsul";
                break;
            case "17":
                nombre = "Talonario de Certificados de Votación";
                break;
            case "18":
                nombre = "Listado de Electores";
                break;
            case "19":
                nombre = "Constancia de Envío al Elector";
                break;
            case "20":
                nombre = "Constancia de Envío a la Oficina Consular";
                break;
            case "21":
                nombre = "Certificado de Votación FF.AA. /Policía Nacional";
                break;
            case "22":
                nombre = "Certificado de Presentación";
                break;
            case "23":
                nombre = "Flujograma de Material Electoral";
                break;
            case "24":
                nombre = "Infografía de Bioseguridad y Votación para el Elector";
                break;
            default:
                throw new AssertionError();
        }
        return nombre;
    }

    public static String obtenerNombreDocumento(String codigoBarras) {
        int longitud = codigoBarras.length();
        String tipo;
        String dignidad = null;
        String nombre = new String();
        switch (longitud) {
            case 27: {
                //Documentos Generales
                tipo = codigoBarras.substring(3, 5);
                nombre = obtenerNombreTipoDocumento(tipo);
            }
            break;
            case 15: {
                //Borradores de Escrutinio
                tipo = "09";
                dignidad = codigoBarras.substring(5, 7);
                nombre = "Borrador de Escrutinio " + obtenerNombreDignidad(dignidad);
            }
            break;
            case 21: {
                //Actas de Escrutinio por dignidad
                tipo = codigoBarras.substring(5, 7);
                nombre = obtenerNombreTipoDocumento(tipo);
                String codigoJRV = codigoBarras.substring(0, 5);
                String codigoSeguridad = codigoBarras.substring(10, 16);
                //TODO: Se debe consultar la Dignidad (en la DB) en base a codigoJRV y/o el codigoSeguridad 
            }
            break;
            case 29: {
                //Acta de Conocimiento Público
                tipo = codigoBarras.substring(3, 5);
                dignidad = codigoBarras.substring(5, 7);
                nombre = obtenerNombreTipoDocumento(tipo) + " " + obtenerNombreDignidad(dignidad);
            }
            break;
            case 14: {
                //Documentos Genéricos
                tipo = codigoBarras.substring(3, 5);
                nombre = obtenerNombreTipoDocumento(tipo);
            }
            break;
            case 22: {
                //Fundas / Sobres de Documentos Electorales
                nombre = "Fundas / Sobres de Documentos Electorales";
                String tipoSibre = codigoBarras.substring(3, 4);
                if (tipoSibre.equals("1")) {
                    nombre = nombre + " día del Sufragio";
                } else if (tipoSibre.equals("2")) {
                    nombre = nombre + " día del Escrutinio";
                }
            }
            break;
            default:
                throw new AssertionError();
        }
        return nombre;
    }
}
