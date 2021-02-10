/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.documentos;

import ec.gob.igm.cne.dao.TJuntaDAO;
import ec.gob.igm.cne.entidades.TJuntaPK;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un código de barras de un Documento Electoral
 *
 */
public class CodigoBarras {

    private List<ComponenteCodigoB> componentes;
    private String tipoDoc;

    public CodigoBarras(List<ComponenteCodigoB> componentes, String tipoDoc) {
        this.componentes = componentes;
        this.tipoDoc = tipoDoc;
    }

    public CodigoBarras() {
        componentes = new ArrayList<>();
    }

    /**
     * Constructor que crea un codigo de barras basado en la plantilla recibida
     * como parametro. Es decir crea una instancia de CodigoBarras, que tiene la
     * misma estructura de campos que la instancia recibida como parámetro.
     *
     * @param plantilla obteto al partir del cual se creará una copia
     */
    public CodigoBarras(CodigoBarras plantilla) {
        this.componentes = new ArrayList<>();
        for (ComponenteCodigoB comp : plantilla.getComponentes()) {
            ComponenteCodigoB newComp = new ComponenteCodigoB(comp, false);
            componentes.add(newComp);
        }
        this.tipoDoc = plantilla.tipoDoc;
    }

    /**
     * Extrae los datos de la Junta Electoral contenidos en los componentes de un
     * CodigoBarras
     * @return una instancia de DatosJunta que contiene la imformación extraida de la Junta 
     */
    public DatosJunta obtenerDatosDeJunta() {
        TJuntaDAO juntaDAO = new TJuntaDAO();
        TJuntaPK juntaPK = new TJuntaPK();
        DatosJunta datosJunta;
        int idProvincia = 0, idCanton = 0, idParroquia = 0, idZona = 0, idJunta = 0;
        String circunscripcion = null, genero = null;
        for (ComponenteCodigoB comp : this.getComponentes()) {            
            if (comp.getDescripcion().equals("ID_PROVINCIA")) {
                idProvincia = Integer.valueOf(comp.getValorDefecto());
                juntaPK.setCodProvincia((short) idProvincia);
            }
            if (comp.getDescripcion().equals("ID_CANTON")) {
                idCanton = Integer.valueOf(comp.getValorDefecto());
                juntaPK.setCodCanton((short) idCanton);
            }
            if (comp.getDescripcion().equals("ID_PARROQUIA")) {
                idParroquia = Integer.valueOf(comp.getValorDefecto());
                juntaPK.setCodParroquia((short) idParroquia);
            }
            if (comp.getDescripcion().equals("ID_ZONA")) {
                idZona = Integer.valueOf(comp.getValorDefecto());
                juntaPK.setCodZona((short) idZona);
            }
            if (comp.getDescripcion().equals("ID_JUNTA")) {
                idJunta = Integer.valueOf(comp.getValorDefecto());
                juntaPK.setJunta((short) idJunta);
            }
            if (comp.getDescripcion().equals("ID_CIRCUNSCRIPCION_CANTON")) {
                circunscripcion = Integer.valueOf(comp.getValorDefecto()).toString();
                juntaPK.setCodCircunscripcion(circunscripcion);
            }
            if (comp.getDescripcion().equals("GENERO")) {
                genero = comp.getValorDefecto();
                juntaPK.setSexo(genero);
            }
            
        }

        datosJunta = juntaDAO.obtenerDatosDeJunta(idProvincia, idCanton, idParroquia, idZona, idJunta, circunscripcion, genero);
        datosJunta.setJuntaPK(juntaPK);
        return datosJunta;
    }

    /**
     * Establese los valores de los campos, que identifican una junta, en el
     * codigo de barras del documento.
     *
     * @param juntaPK instancia que contiene la información que identifica a un
     * junta
     */
    public void setDatosJunta(DatosJunta datosJunta) {
        if (componentes.contains(new ComponenteCodigoB("ID_PROVINCIA"))) {
            int index = componentes.indexOf(new ComponenteCodigoB("ID_PROVINCIA"));              
            
            
            componentes.get(index).setValorDefecto(String.valueOf(datosJunta.getJuntaPK().getCodProvincia()));
        }
        if (componentes.contains(new ComponenteCodigoB("ID_CANTON"))) {
            int index = componentes.indexOf(new ComponenteCodigoB("ID_CANTON"));
            componentes.get(index).setValorDefecto(String.valueOf(datosJunta.getJuntaPK().getCodCanton()));
        }
        if (componentes.contains(new ComponenteCodigoB("ID_PARROQUIA"))) {
            int index = componentes.indexOf(new ComponenteCodigoB("ID_PARROQUIA"));
            componentes.get(index).setValorDefecto(String.valueOf(datosJunta.getJuntaPK().getCodParroquia()));
        }
        if (componentes.contains(new ComponenteCodigoB("ID_CIRCUNSCRIPCION_CANTON"))) {
            int index = componentes.indexOf(new ComponenteCodigoB("ID_CIRCUNSCRIPCION_CANTON"));
            componentes.get(index).setValorDefecto(datosJunta.getJuntaPK().getCodCircunscripcion());
        }
        if (componentes.contains(new ComponenteCodigoB("ID_ZONA"))) {
            int index = componentes.indexOf(new ComponenteCodigoB("ID_ZONA"));
            componentes.get(index).setValorDefecto(String.valueOf(datosJunta.getJuntaPK().getCodZona()));
        }
        if (componentes.contains(new ComponenteCodigoB("ID_JUNTA"))) {
            int index = componentes.indexOf(new ComponenteCodigoB("ID_JUNTA"));
            componentes.get(index).setValorDefecto(String.valueOf(datosJunta.getJuntaPK().getJunta()));
        }
        if (componentes.contains(new ComponenteCodigoB("CODIGO DE JRV"))) {
            int index = componentes.indexOf(new ComponenteCodigoB("CODIGO DE JRV"));
            componentes.get(index).setValorDefecto(datosJunta.getCodigos().get("COD_ACTA_JUNTA"));
        }
        if (componentes.contains(new ComponenteCodigoB("GENERO"))) {
            int index = componentes.indexOf(new ComponenteCodigoB("GENERO"));
            componentes.get(index).setValorDefecto(datosJunta.getJuntaPK().getSexo());
        }
        
        
    }

    /**
     * Esteblese los campos: número actual de página y número total de páginas
     * del código de barras de un documento
     *
     * @param paginaActual Numero de pagina a la que corresponde el codigo de barras
     * @param totalPaginas Total de paginas del documento
     * @param cabeceraPie Indicador de si el codigo de barras esta en la cabecera o el pie de página (aplicable a Actas de Escrutinio)
     */
    public void setNumerosPagina(String paginaActual, String totalPaginas, String cabeceraPie) {
        if (componentes.contains(new ComponenteCodigoB("NUMERO_ACTUAL_PAGINA"))) {
            int index = componentes.indexOf(new ComponenteCodigoB("NUMERO_ACTUAL_PAGINA"));
            componentes.get(index).setValorDefecto(paginaActual);
        }
        if (componentes.contains(new ComponenteCodigoB("NUMERO TOTAL DE PAGINA"))) {
            int index = componentes.indexOf(new ComponenteCodigoB("NUMERO TOTAL DE PAGINA"));
            componentes.get(index).setValorDefecto(totalPaginas);
        }
        if (componentes.contains(new ComponenteCodigoB("INDICADOR DE CABECERA O PIE DE PAGINA"))) {
            int index = componentes.indexOf(new ComponenteCodigoB("INDICADOR DE CABECERA O PIE DE PAGINA"));
            componentes.get(index).setValorDefecto(cabeceraPie);
        }
    }

    public List<ComponenteCodigoB> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<ComponenteCodigoB> componentes) {
        this.componentes = componentes;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

}
