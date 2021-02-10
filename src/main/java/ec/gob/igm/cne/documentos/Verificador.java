/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.documentos;

import ec.gob.igm.cne.dao.TListadoDocumentosDAO;
import ec.gob.igm.cne.entidades.TDignidad;
import ec.gob.igm.cne.entidades.TJunta;
import ec.gob.igm.cne.entidades.TJuntaPK;
import java.util.List;

/**
 * Clase que implementa las verificaciones requeridas para Documentos
 * Electorales La verificación solo puede realizarse a partir del nombre de
 * documento especificado, mediante el cual se puede consultar en la base de
 * datos los componentes que conforman su código de barras y el orden y tamaño
 * de estos
 */
public class Verificador {

    /**
     * Verifica que el codigo de barras obtenido del documento (especificado en
     * strCodigo) efectivamente se corresponda con el tipo de documento
     * (especificado con nombreDoc). Las verificaciones que realiza son: -Valida
     * que la longitud del código de barras sea el correcto para el documento
     * indicado por nombreDoc. -Valida que el tipo de documento (incluido en el
     * código de barras) corresponda con el documento indicado por su nombre
     *
     * @param nombreDoc nombre del documento que se quiere validar
     * @param tamanoCodigo longitud que debería tener el código de barras del
     * documento
     * @param strCodigo código de barras obtenido del documento
     * @return una instancia de ResultadoVerificacion que contiene las
     * validaciones realizads y sus resultados
     */
    public static ResultadoVerificacion validacionParcialBarras(String nombreDoc, int tamanoCodigo, String strCodigo, CodigoBarras objCodigo) {
        /*1-Crear un objeto CodigoBarras a partir del nombre del documento
          2-Verificar que el atributo tipoDoc del objeto CodigoBarras se corresponda
           con el tipo de documento incluido en el strCodigo de barras
          3-Verificar que el tamaño del strCodigo de barras proporcionado sea el correcto
         */
//        TListadoDocumentosDAO tldDAO = new TListadoDocumentosDAO();
//        objCodigo = tldDAO.listarComponentesCodigo(nombreDoc);
        /*verificación del código de tipo de documento*/
        ResultadoVerificacion resultVeri = Verificador.validarTipoDocumento(objCodigo, strCodigo);
        /*verificación de la longitud del código de barras*/
        Boolean validacionTamano = (tamanoCodigo == strCodigo.length());
        resultVeri.putResultado("LONGITUD CADENA", validacionTamano);
        return resultVeri;
    }

    /**
     * Verifica que el código de barras leido del documento (especificado en
     * strCodigo) corresponda con el documento y la junta especificados. Las
     * verificaciones que realiza son: - Valida que el tamaño del código de
     * barras sea el correcto para el documento indicado por el nombre - Valida
     * que el tipo de documento (incluido en el strCodigo de barras) corresponda
     * con el documento indicado por su nombre - Valida que los datos de la
     * junta (incluidos en el strCodigo de barras) se correspondan con la junta
     * junta especificada en junta
     *
     * @param nombreDoc nombre del documento a validar
     * @param tamanoCodigo tamaño que debe tener el strCodigo de barras
     * @param junta objeto junta que se usa para validar la correspondencia del
     * strCodigo de barras strCodigo
     * @param strCodigo strCodigo de barras leido del documento
     * @return true: si las validaciones resultan satisfactorias, false: en caso
     * contrario
     */
    public static boolean validacionCompletaBarras(String nombreDoc, int tamanoCodigo, TJunta junta, String strCodigo) {
        return true;
    }

    /**
     * Valida que el tipo de documento (incluido en el codigo de barras
     * strCodigo) corresponda con el atributo tipoDoc del objeto CodigoBarras
     * especificado. Podria modificarse para que haga validaciones adicionales
     * como: +CODIGO DE PROCESO +NUMERO_ACTUAL_PAGINA +NUMERO TOTAL DE PAGINA
     *
     * @param objCodigo instancia de CodigoBarras que contiene el atributo
     * tipoDoc formado con datos obtenidos de la Base de Datos
     * @param strCodigo codigo de barras leido de un documento
     * @return una instancia de ResultadoVerificacion que contiene el resultado
     * de la validación Tipo de Documento
     */
    public static ResultadoVerificacion validarTipoDocumento(CodigoBarras objCodigo, String strCodigo) {
        int posicion = 0;
        String strTipoDoc = new String();

        ResultadoVerificacion resulVeri = new ResultadoVerificacion();
        String clave = "TIPO DE DOCUMENTO"; //debe ser igual a como consta en la Base de Datos
        /*
        Se obtiene el codigo que identifica al tipo de documento,
        a partir del codigo de barras leido del documento
         */
        for (ComponenteCodigoB comp : objCodigo.getComponentes()) {
            if (comp.getDescripcion().equalsIgnoreCase(clave)) {
                int tamano = comp.getLongitud();
                strTipoDoc = strCodigo.substring(posicion, posicion + tamano);
//                System.out.println("Código Tipo Documento:"+strTipoDoc);
            }
            posicion = posicion + comp.getLongitud();
        }
        Boolean valor = objCodigo.getTipoDoc().equals(strTipoDoc);
        resulVeri.putResultado(clave, valor);

        return resulVeri;
    }

    /**
     * Verifica en su totalidad el codigo de barras de un documento electoral.
     * Para la validación se requieren: +El nombre del Documento (como consta en
     * la Base de Datos) +Los datos correspondiente a la junta electoral. +Y el
     * número de la pagía actual y el total de páginas que tiene el documento
     *
     * @param nombreDoc nombre del documento a validar
     * @param codigoBarras codigo de barras obtenido del documento
     * @param datosJunta datos de la junta a la que pertenece el documento
     * @param dignidades lista de todas las dignidades, para usarse al validar
     * documentos dependientes de la dignidad
     * @param numPagina número actual de página
     * @param totalPaginas número total de páginas
     * @param cabeceraPie 1 Cabecera - 0 Pie
     * @param tipoParroquia Urbana o Rural
     * @return
     */
    public static ResultadoVerificacion validarCodigoBarras(String nombreDoc, String codigoBarras, DatosJunta datosJunta, List<TDignidad> dignidades, String numPagina, String totalPaginas, String cabeceraPie, String tipoParroquia, int idKit) {
        /*
        Con los datos conocidos se crea una instancia de CodigoBarras que contiene
        los valores CORRECTOS para los campos, es decir los valores que el codigo de barras DEBERÍA TENER.
         */
        TListadoDocumentosDAO tldDAO = new TListadoDocumentosDAO();
        CodigoBarras objCodigoBarras = tldDAO.listarComponentesCodigo(nombreDoc, idKit);
        objCodigoBarras.setDatosJunta(datosJunta);
        objCodigoBarras.setNumerosPagina(numPagina, totalPaginas, cabeceraPie);

        /*
        Para cada campo que conforma el código de barras, se compara el valor
        real (o correcto) con el valor que consta en el código de barras leido.
        Los resultados todas las verificaciones realizadas se almacenan en un
        objeto de tipo ResultadoVerificacion.
         */
        ResultadoVerificacion resultados = new ResultadoVerificacion();
        String subStr;
        int posicion = 0;
        String codDignidad = null;
        int longitudCodBarras = 0; //longitud correcta del código de barras
        for (ComponenteCodigoB comp : objCodigoBarras.getComponentes()) {
            int tamano = comp.getLongitud();
            longitudCodBarras = longitudCodBarras + tamano;
            subStr = codigoBarras.substring(posicion, posicion + tamano);
            String descripcion = comp.getDescripcion();

            /*
            HARD CODE!!!
            Para los documentos dependientes de la dignidad.
             */
            if (descripcion.equals("ID_DIGNIDAD")) {
                if (nombreDoc.contains("President")) {
                    //String codDignidad = obtenerCodigoDeDignidad(dignidades, "PRESIDENTA/E Y VICEPRESIDENTA/E");
                    codDignidad = obtenerCodigoDeDignidad(dignidades, "PRESIDENTA/E Y VICEPRESIDENTA/E");
                    comp.setValorDefecto(codDignidad);
                }
                if (nombreDoc.contains("Nacional")) {
                    //String codDignidad = obtenerCodigoDeDignidad(dignidades, "ASAMBLEÍSTAS NACIONALES");
                    codDignidad = obtenerCodigoDeDignidad(dignidades, "ASAMBLEÍSTAS NACIONALES");
                    comp.setValorDefecto(codDignidad);
                }
                if (nombreDoc.contains("Provincial")) {
                    codDignidad = obtenerCodigoDeDignidad(dignidades, "ASAMBLEÍSTAS PROVINCIALES");
//                    String codDignidad = obtenerCodigoDeDignidad(dignidades, "ASAMBLEÍSTAS PROVINCIALES");
                    comp.setValorDefecto(codDignidad);
                }
                if (nombreDoc.contains("Andino")) {
                    codDignidad = obtenerCodigoDeDignidad(dignidades, "PARLAMENTARIOS ANDINOS");
//                    String codDignidad = obtenerCodigoDeDignidad(dignidades, "PARLAMENTARIOS ANDINOS");
                    comp.setValorDefecto(codDignidad);
                }
                if (nombreDoc.contains("Exterior")) {
                    codDignidad = obtenerCodigoDeDignidad(dignidades, "ASAMBLEÍSTAS PROVINCIALES");
//                    String codDignidad = obtenerCodigoDeDignidad(dignidades, "PARLAMENTARIOS ANDINOS");
                    comp.setValorDefecto(codDignidad);
                }
                if (nombreDoc.contains("Alcalde")) {
                    codDignidad = obtenerCodigoDeDignidad(dignidades, "ALCALDESA / ALCALDE");
//                    String codDignidad = obtenerCodigoDeDignidad(dignidades, "ALCALDESA / ALCALDE");
                    comp.setValorDefecto(codDignidad);
                }
                if (nombreDoc.contains("Prefecto")) {
                    codDignidad = obtenerCodigoDeDignidad(dignidades, "PREFECTA / PREFECTO");
//                    String codDignidad = obtenerCodigoDeDignidad(dignidades, "PREFECTA / PREFECTO");
                    comp.setValorDefecto(codDignidad);
                }
                if (nombreDoc.contains("Concejales")) {
                    if (tipoParroquia.equals("U")) {
                        codDignidad = obtenerCodigoDeDignidad(dignidades, "CONCEJALES URBANOS");
//                        String codDignidad = obtenerCodigoDeDignidad(dignidades, "CONCEJALES URBANOS");
                        comp.setValorDefecto(codDignidad);
                    }
                    if (tipoParroquia.equals("R")) {
                        codDignidad = obtenerCodigoDeDignidad(dignidades, "CONCEJALES RURALES");
//                        String codDignidad = obtenerCodigoDeDignidad(dignidades, "CONCEJALES RURALES");
                        comp.setValorDefecto(codDignidad);
                    }
                }
                if (nombreDoc.contains("Junta Parroquial")) {
                    codDignidad = obtenerCodigoDeDignidad(dignidades, "VOCALES DE JUNTA PARROQUIAL RURAL");
//                    String codDignidad = obtenerCodigoDeDignidad(dignidades, "VOCALES DE JUNTA PARROQUIAL RURAL");
                    comp.setValorDefecto(codDignidad);
                }
                if (nombreDoc.contains("Hombres")) {
//                    String codDignidad = obtenerCodigoDeDignidad(dignidades, "CONSEJO DE PARTICIPACION CIUDADANA Y CONTROL SOCIAL HOMBRES");
                    codDignidad = obtenerCodigoDeDignidad(dignidades, "CONSEJO DE PARTICIPACION CIUDADANA Y CONTROL SOCIAL HOMBRES");
                    comp.setValorDefecto(codDignidad);
                }
                if (nombreDoc.contains("Mujeres")) {
//                    String codDignidad = obtenerCodigoDeDignidad(dignidades, "CONSEJO DE PARTICIPACION CIUDADANA Y CONTROL SOCIAL MUJERES");
                    codDignidad = obtenerCodigoDeDignidad(dignidades, "CONSEJO DE PARTICIPACION CIUDADANA Y CONTROL SOCIAL MUJERES");
                    comp.setValorDefecto(codDignidad);
                }
//                if (nombreDoc.contains("Exterior")) {
////                    String codDignidad = obtenerCodigoDeDignidad(dignidades, "CONSEJO DE PARTICIPACION CIUDADANA Y CONTROL SOCIAL ETNIAS Y EXTERIOR");
//                    codDignidad = obtenerCodigoDeDignidad(dignidades, "CONSEJO DE PARTICIPACION CIUDADANA Y CONTROL SOCIAL ETNIAS Y EXTERIOR");
//                    comp.setValorDefecto(codDignidad);
//                }
            }
            if (descripcion.equals("ID_CIRCUNSCRIPCION_CANTON")) {
                if (objCodigoBarras.getTipoDoc().equals("09")) {
                    //codDignidad = obtenerCodigoDeDignidad(dignidades, "PRESIDENTA/E Y VICEPRESIDENTA/E");

                    if (!(nombreDoc.contains("Provinciales") || nombreDoc.contains("Exterior"))) {

                        comp.setValorDefecto("00");
                    }
                }
            }
            //HARD CODE
            // Incluido para borradores de escrutinio diferentes a asambleistas provinciales.
            if (descripcion.equals("ID_PROVINCIA")) {
                if (objCodigoBarras.getTipoDoc().equals("09")) {
                    //codDignidad = obtenerCodigoDeDignidad(dignidades, "PRESIDENTA/E Y VICEPRESIDENTA/E");
                    if (!(nombreDoc.contains("Provinciales") || nombreDoc.contains("Exterior"))) {
                        comp.setValorDefecto("00");
                    }
                }
            }

            if (objCodigoBarras.getTipoDoc().equals("10") || objCodigoBarras.getTipoDoc().equals("11")) {
                if (comp.getDescripcion().equals("CODIGO DE JRV")) {
                    comp.setValorDefecto(datosJunta.getCodigos().get("COD_ACTA_JUNTA"));
                }
                if (comp.getDescripcion().equals("CODIGO DE SEGURIDAD")) {
                    if (nombreDoc.contains("President")) {
                        comp.setValorDefecto(datosJunta.getCodigos().get("COD1"));
                    }
                    // Add por Joffre Alava - || nombreDoc.contains("Exterior") - valida Exterior
                    if (nombreDoc.contains("Provincial") || nombreDoc.contains("Exterior")) {
                        comp.setValorDefecto(datosJunta.getCodigos().get("COD7"));
                    }
                    if (nombreDoc.contains("Nacional")) {
                        comp.setValorDefecto(datosJunta.getCodigos().get("COD9"));
                    }
                    if (nombreDoc.contains("Andino")) {
                        comp.setValorDefecto(datosJunta.getCodigos().get("COD8"));
                    }
                }
            }

            if (objCodigoBarras.getTipoDoc().equals("23")) {
                if (comp.getDescripcion().equals("TIPO VOTACION")) {
                    comp.setValorDefecto(String.valueOf(idKit));
                }
            } else {
                if (comp.getDescripcion().equals("TIPO VOTACION")) {
                    comp.setValorDefecto(String.valueOf(0));
                }
            }

            //Para los campos establecidos como NULL, se omite la validacion
            if (comp.getValorDefecto() != null) {
                String valorCorrecto = String.format("%1$" + tamano + "s", comp.getValorDefecto()).replace(' ', '0');
                Boolean valor = valorCorrecto.equals(subStr);
                resultados.putResultado(descripcion, valor);
                System.out.println(comp.getDescripcion() + ":" + subStr + "\treal:" + valorCorrecto);
                posicion = posicion + comp.getLongitud();
            }
        }
        if (longitudCodBarras == codigoBarras.length()) {
            resultados.putResultado("LONGITUD_CADENA", true);
        } else {
            resultados.putResultado("LONGITUD_CADENA", false);
        }
        return resultados;
    }

    /**
     * Busca en la lista de dignidades una dignidad cuyo nombre se corresponda
     * con nombreDignidad, y retorna el codigo de la dignidad encontrada, si no
     * encuentra una dignidad retorna null
     *
     * @param dignidades
     * @param nombreDignidad
     * @return codigo de la dignidad encontrada, si no hay coincidencias retorna
     * null
     */
    public static String obtenerCodigoDeDignidad(List<TDignidad> dignidades, String nombreDignidad) {
        TDignidad newDignidad = new TDignidad();
        newDignidad.setDignidad(nombreDignidad);
        int index = dignidades.indexOf(newDignidad);
        if (index >= 0) {
            return String.valueOf(dignidades.get(index).getCodDignidad());
        } else {
            return null;
        }
    }

    /**
     * Obtiene los datos de Junta a partir del código de barras leido de un
     * documento
     *
     * @param nombreDoc nombre del documento del que se ha leido el código de
     * barras
     * @param codigoBarras código de barras leido del documento
     * @return datos de la junta obtenidos del código de barras, y encapsulados
     * en una objeto CodigoBarras
     */
    public static CodigoBarras obtenerJunta(String nombreDoc, String codigoBarras, CodigoBarras objCodigoBarras) {
        String subStr;
        int posicion = 0;
        int longitudCodBarras = 0; //longitud correcta del código de barras
        for (ComponenteCodigoB comp : objCodigoBarras.getComponentes()) {
            int tamano = comp.getLongitud();
            longitudCodBarras = longitudCodBarras + tamano;
            subStr = codigoBarras.substring(posicion, posicion + tamano);
            if (comp.getDescripcion().equals("ID_PROVINCIA")) {
                comp.setValorDefecto(subStr);
            }
            if (comp.getDescripcion().equals("ID_CANTON")) {
                comp.setValorDefecto(subStr);
            }
            if (comp.getDescripcion().equals("ID_PARROQUIA")) {
                comp.setValorDefecto(subStr);
            }
            if (comp.getDescripcion().equals("ID_CIRCUNSCRIPCION_CANTON")) {
                comp.setValorDefecto(subStr);
            }
            if (comp.getDescripcion().equals("ID_ZONA")) {
                comp.setValorDefecto(subStr);
            }
            if (comp.getDescripcion().equals("ID_JUNTA")) {
                comp.setValorDefecto(subStr);
            }
            if (comp.getDescripcion().equals("GENERO")) {
                comp.setValorDefecto(subStr);
            }

            posicion = posicion + comp.getLongitud();
        }
        return objCodigoBarras;
    }
}
