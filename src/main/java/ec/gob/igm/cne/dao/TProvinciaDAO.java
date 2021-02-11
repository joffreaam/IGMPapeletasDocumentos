/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.dao;

import ec.gob.igm.cne.controladores.TProvinciaJpaController;
import ec.gob.igm.cne.entidades.TProvincia;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author jkari
 */
public class TProvinciaDAO {
    private TProvinciaJpaController jpaController;
    private static final String NOMBRE_T_LOTE = "T_LOTE";
    
    public TProvinciaDAO(){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("CNEELECCIONES");
        
        jpaController = new TProvinciaJpaController(emf);
    }
    
    public List<TProvincia> listarTodas(){
        return jpaController.findTProvinciaEntities();
    }
    
    public List<TProvincia> listarProvinciasXEstadoYTipoDeLote(String estado, String tipo) {
        String query = "select pro.id_provincia, pro.provincia "
                + "from t_provincia pro, "+NOMBRE_T_LOTE+" lot "
                + "where PRO.ID_PROVINCIA = lot.id_provincia "
                + "and lot.estado = ? "
                + "and lot.tipo = ? "
                + "group by pro.id_provincia, pro.provincia "
                + "order by pro.id_provincia";
        Query nativeQuery = jpaController.getEntityManager().createNativeQuery(query);
        nativeQuery.setParameter(1, estado);
        nativeQuery.setParameter(2, tipo);
        List<Object[]> results = nativeQuery.getResultList();
        return results
                .stream()
                .map(result -> new TProvincia(((BigDecimal) result[0]).intValue(), (String) result[1]))
                .collect(Collectors.toList());
    }

    public List<BigDecimal> listarLotesXEstadoTipoYProvincia(int idProvincia, String tipo, String estado) {
        String query = "SELECT ID_LOTE FROM " + NOMBRE_T_LOTE
                + " WHERE ESTADO = ?1 "
                + " AND TIPO = ?2 "
                + " AND ID_PROVINCIA = ?3 "
                + " ORDER BY ID_LOTE";
        Query nativeQuery = jpaController.getEntityManager().createNativeQuery(query);
        nativeQuery.setParameter(1, estado);
        nativeQuery.setParameter(2, tipo);
        nativeQuery.setParameter(3, idProvincia);
        List<BigDecimal> results = nativeQuery.getResultList();
        return results;
    }
    
       public List<BigDecimal> listarLotesXEstadoTipoYProvinciaXDignidad(int idProvincia, String tipo, String estado,String idBodega) {
        String query = "select id_lote from "+NOMBRE_T_LOTE
                + " where estado = ? "
                + "and tipo = ? "
                + "and id_provincia = ? "
                + "and id_bodega = ? "
                + "order by id_lote";
        Query nativeQuery = jpaController.getEntityManager().createNativeQuery(query);
        nativeQuery.setParameter(1, estado);
        nativeQuery.setParameter(2, tipo);
        nativeQuery.setParameter(3, idProvincia);
        nativeQuery.setParameter(4, idBodega);
        List<BigDecimal> results = nativeQuery.getResultList();
        return results;
    }
    //add Joffre A
    public List<BigDecimal> listarLotesXEstadoTipoYProvincia(int idProvincia, String tipo, String estado,String estado2) {
        String query = "select id_lote from "+NOMBRE_T_LOTE                
                //+ " where estado = ? "
                      + " where  (estado = ?1 "
               + "or estado= ?4"  
                + " ) and tipo = ?2 "
                + "and id_provincia = ?3 "
                + "order by id_lote";
        Query nativeQuery = jpaController.getEntityManager().createNativeQuery(query);
        nativeQuery.setParameter(1, estado);
        nativeQuery.setParameter(2, tipo);
        nativeQuery.setParameter(3, idProvincia);
        nativeQuery.setParameter(4, estado2);
        
        List<BigDecimal> results = nativeQuery.getResultList();
        return results;
    }
}
