/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.dao;

import ec.gob.igm.cne.controladores.TListadoDocumentosJpaController;
import ec.gob.igm.cne.documentos.CodigoBarras;
import ec.gob.igm.cne.documentos.ComponenteCodigoB;
import ec.gob.igm.cne.documentos.Documento;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author desarrollo
 */
public class TListadoDocumentosDAO {

    private TListadoDocumentosJpaController tljc;
    private String mensaje;
    
    public TListadoDocumentosDAO(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CNEELECCIONES");
        
        tljc = new TListadoDocumentosJpaController(emf);
    }

    /**
     * Consulta en la DB la lista de documentos que corresponden a un tipo de
     * proceso dado, junto con el orden que ocupa cada documento y la longitud
     * que debe tener el código de barras
     *
     * @param tipo se refiere al tipo de kit, que puede ser: Voto General, Voto
     * en casa, Voto PPL, Exterior, etc.
     * @return el listado de documentos correspondientes a un tipo de kit
     */
    public List<Documento> listarDocumentosXtipo(Integer idKit) {
        String query = "select TL.Orden_En_Kit, LD.DESCRIPCION, TE.LONGITUD, LD.ID_LISTADO "
                + "from T_Tipodocumento_vs_Listado TL, T_LISTADO_DOCUMENTOS LD, T_Tipo_Documento TD, T_Tipo_Estructura TE "
                + "where TL.ID_LISTADO = LD.ID_LISTADO "
                + "and TL.ID_KIT = TD.ID_KIT "
                + "and TL.ID_ESTRUCTURA = TE.ID_ESTRUCTURA "
                + "and TD.ID_KIT = ? "
                + "order by TL.Orden_En_Kit";
        Query nativeQuery = tljc.getEntityManager().createNativeQuery(query);
        nativeQuery.setParameter(1, idKit);
        List<Object[]> results = nativeQuery.getResultList();
        return results
                .stream()
                .map(result -> new Documento(((BigDecimal) result[0]).intValue(), (String) result[1], ((BigDecimal) result[2]).intValue(), ((BigDecimal) result[3]).intValue()))
                .collect(Collectors.toList());
    }

    /**
     * Consulta la lista de los campos que forman el código de barras de un Documento y el 
     * código de tipo de documento.
     * Devuelve una instancia de CodigoBarras que a su vez contiene una lista de objetos de tipo ComponenteCodigoB,
     * en la que cada elemento corresponde a un campo presente en el código de barras del documento.
     * Además dentro de la lista de componentes crea uno llamado 'TIPO DE DOCUMENTO' cuyo valor 
     * por defecto es el código de tipo de documento almacenado en la Base de Datos.
     * 
     * @param documento nombre que identifica al tipo documento 
     * @return instancia de CodigoBarras con la lista de los campos o componentes que forma el código de barras
     */
    public CodigoBarras listarComponentesCodigo(String documento, int idKit) {
        String query = "select distinct L.CODIGO_TIPODOC, C.DESCRIPCION, C.LONGITUD, C.VALOR_DEFAULT, EC.ORDEN "
                + "from T_ESTRUCTURA_TIPODOC_VS_CAMPO EC, T_ESTRUCTURA_CAMPO C, T_LISTADO_DOCUMENTOS L, T_Tipodocumento_vs_Listado TL "
                + "where TL.ID_LISTADO = L.ID_LISTADO "
                + "and EC.ID_CAMPO = C.ID_CAMPO "
                + "and EC.ID_ESTRUCTURA = TL.ID_ESTRUCTURA "
                + "and L.DESCRIPCION= ? "
                + "and TL.ID_KIT = ? "
                + "order by EC.ORDEN";
        Query nativeQuery = tljc.getEntityManager().createNativeQuery(query);
        nativeQuery.setParameter(1, documento);
        nativeQuery.setParameter(2, idKit);
        List<Object[]> results = nativeQuery.getResultList();
        List<ComponenteCodigoB> listComp = new ArrayList<>();
        ComponenteCodigoB listCopm;
        for (Object[] obj : results) {
            if (((String) obj[1]).equals("TIPO DE DOCUMENTO")) {
                listCopm = new ComponenteCodigoB((String) obj[1], ((BigDecimal) obj[2]).intValue(), (String) obj[0], ((BigDecimal) obj[4]).intValue());
            } else {
                listCopm = new ComponenteCodigoB((String) obj[1], ((BigDecimal) obj[2]).intValue(), (String) obj[3], ((BigDecimal) obj[4]).intValue());
            }
            listComp.add(listCopm);
        }
        String codDoc = (String) results.get(0)[0];
        CodigoBarras codigoB = new CodigoBarras(listComp, codDoc);
        return codigoB;
    }
    
    public CodigoBarras listarComponentesCodigo(String documento) {
        String query = "select distinct L.CODIGO_TIPODOC, C.DESCRIPCION, C.LONGITUD, C.VALOR_DEFAULT, EC.ORDEN "
                + "from T_ESTRUCTURA_TIPODOC_VS_CAMPO EC, T_ESTRUCTURA_CAMPO C, T_LISTADO_DOCUMENTOS L, T_Tipodocumento_vs_Listado TL "
                + "where TL.ID_LISTADO = L.ID_LISTADO "
                + "and EC.ID_CAMPO = C.ID_CAMPO "
                + "and EC.ID_ESTRUCTURA = TL.ID_ESTRUCTURA "
                + "and L.DESCRIPCION= ?1 "
                + "order by EC.ORDEN";
        Query nativeQuery = tljc.getEntityManager().createNativeQuery(query);
        nativeQuery.setParameter(1, documento);
        List<Object[]> results = nativeQuery.getResultList();
        List<ComponenteCodigoB> listComp = new ArrayList<>();
        ComponenteCodigoB listCopm;
        for (Object[] obj : results) {
            if (((String) obj[1]).equals("TIPO DE DOCUMENTO")) {
                listCopm = new ComponenteCodigoB((String) obj[1], ((BigDecimal) obj[2]).intValue(), (String) obj[0], ((BigDecimal) obj[4]).intValue());
            } else {
                listCopm = new ComponenteCodigoB((String) obj[1], ((BigDecimal) obj[2]).intValue(), (String) obj[3], ((BigDecimal) obj[4]).intValue());
            }
            listComp.add(listCopm);
        }
        String codDoc = (String) results.get(0)[0];
        CodigoBarras codigoB = new CodigoBarras(listComp, codDoc);
        return codigoB;
    }
}
