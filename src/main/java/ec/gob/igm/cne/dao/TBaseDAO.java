/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;

/**
 *
 * @author VERA_MAYRA
 */
public class TBaseDAO extends CrudDAO {
    



/**
     * Actualiza el estado de las etiquetas impresas
     * @param estado
     * @param fechaEmision
     * @param usuario
     * @param barras 
     */
    public void actualizarEtiqueta(String estado,Date fechaEmision,String usuario,String barras,String tipo){
       try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("UPDATE T_BASE SET ESTADO=?1"
                    + ",FECHA_EMISION=?2,USUARIO_EMITE=?3 WHERE TIPO=?5 AND BARRAS=?4");
            
            query.setParameter(1, estado);
            query.setParameter(2, fechaEmision);
            query.setParameter(3, usuario);
            query.setParameter(4, barras);
            query.setParameter(5, tipo);
            query.executeUpdate();
            em.getTransaction().commit();
            
        } catch (Exception ex) {
           Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);  
        } 
    }    
    
    
    
    /**
     * 
     * Carga las dignidades según el usuario logueado
     * @param usuario
     * @return 
     * Add por Joffre Alava
     */
    public List<Object[]> cargarTipoDocumento() {
        try {
            Query query = em.createNativeQuery("SELECT ID_KIT, ID_KIT || ' - ' || DESCRIPCION AS DESCRIPCION,ID_ESTRUCTURA_INICIO IDESTRUCTURA FROM T_TIPO_DOCUMENTO ORDER BY ID_KIT");
            
            return query.getResultList();

        } catch (Exception ex) {
          Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<Object[]>();
        }
    }

    
    public List<Object[]> cargarDignidades() {
        try {
//            Query query = em.createNativeQuery("SELECT B.ID_DIGNIDAD CODIGO, B.ID_DIGNIDAD ||' - '|| B.DIGNIDAD AS DIGNIDAD FROM T_USUARIO A INNER JOIN " 
//                                              +"T_DIGNIDAD_X_USUARIO B ON A.ID_USUARIO=B.ID_USUARIO WHERE USUARIO=?1");
            
            Query query = em.createNativeQuery("SELECT COD_DIGNIDAD, COD_DIGNIDAD ||' - '|| DIGNIDAD AS DIGNIDAD FROM T_DIGNIDAD ORDER BY COD_DIGNIDAD");
            //query.setParameter(1, usuario);
            return query.getResultList();

        } catch (Exception ex) {
          Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<Object[]>();
        }
    }
    
    
public List<Object[]> cargarProvinciasXEstadoYTipoDeLote(String estado, String tipo, String estado2) {
        try {
//            Query query = em.createNativeQuery("SELECT B.ID_DIGNIDAD CODIGO, B.ID_DIGNIDAD ||' - '|| B.DIGNIDAD AS DIGNIDAD FROM T_USUARIO A INNER JOIN " 
//                                              +"T_DIGNIDAD_X_USUARIO B ON A.ID_USUARIO=B.ID_USUARIO WHERE USUARIO=?1");           
            String sql = "select pro.id_provincia, pro.id_provincia ||' - '|| pro.provincia "
                + "from t_provincia pro, T_LOTE lot "
                + "where PRO.ID_PROVINCIA = lot.id_provincia "
                + "and (lot.estado = ?1 "
               + "or lot.estado= ?3 "     
                + ") and lot.tipo = ?2 "
                + "group by pro.id_provincia, pro.provincia "
                + "order by pro.id_provincia";
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, estado);
            query.setParameter(2, tipo);
            query.setParameter(3, estado2);
            
            return query.getResultList();

        } catch (Exception ex) {
          Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<Object[]>();
        }
    }
    
    
      
    public void ingresarReimpresion(int idProvincia,int idCanton,int idParroquia,int idZona,int idJunta,String idSexo, String autorizacion,Date fecha,String usuario,String tipo,String razon,int idDignidad,String tagReimpreso){
        try{
        em.getTransaction().begin();
        Query query = em.createNativeQuery("INSERT INTO T_REIMPRESION "
                + "VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13)");
            query.setParameter(1, idProvincia);
            query.setParameter(2, idCanton);
            query.setParameter(3, idParroquia);
            query.setParameter(4, idZona);
            query.setParameter(5, idJunta);
            query.setParameter(6, idSexo);
            query.setParameter(7, autorizacion);
            query.setParameter(8, fecha);
            query.setParameter(9, usuario);
            query.setParameter(10, tipo);
            query.setParameter(11, razon);
            query.setParameter(12, idDignidad);
            query.setParameter(13, tagReimpreso);
            query.executeUpdate();
            em.getTransaction().commit();
             } catch (Exception ex) {
            Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }             
    }
    
      
    public void actualizarReimpresion(int idProvincia,int idCanton,int idParroquia,int idZona,int idJunta,int idSexo, String autorizacion,Date fecha,String usuario,String tipo,String razon,int idDignidad,String tagReimpreso){
        try{
        em.getTransaction().begin();
        Query query = em.createNativeQuery("INSERT INTO T_REIMPRESION "
                + "VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13)");
            query.setParameter(1, idProvincia);
            query.setParameter(2, idCanton);
            query.setParameter(3, idParroquia);
            query.setParameter(4, idZona);
            query.setParameter(5, idJunta);
            query.setParameter(6, idSexo);
            query.setParameter(7, autorizacion);
            query.setParameter(8, fecha);
            query.setParameter(9, usuario);
            query.setParameter(10, tipo);
            query.setParameter(11, razon);
            query.setParameter(12, idDignidad);
            query.setParameter(13, tagReimpreso);
            query.executeUpdate();
            em.getTransaction().commit();
             } catch (Exception ex) {
            Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }             
    }
    // add Joffre Alava
    public List<Object[]> cargarRazon() {
        try {
            Query query = em.createNativeQuery("SELECT * "
                                              +"FROM T_REIMPRESION_CAUSA WHERE ESTADO='A'"
                                              );
            return query.getResultList();

        } catch (Exception ex) {
            Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<Object[]>();
        }
    }
    
                                                        
    public List<Object[]> getListadoDocumentosReImpresos(int idProvincia,int idCanton,int idParroquia,int idZona, String Sexo, int idJunta, String Circunscripcion ) {
        try {
            Query query = em.createNativeQuery("SELECT COUNT(ID_LISTADO), ID_LISTADO as id FROM T_DOCUMENTO_REIMPRESOS " 
                                + " WHERE COD_PROVINCIA = ?1 AND COD_CANTON = ?2 AND COD_PARROQUIA = ?3 "
                                + " AND COD_ZONA = ?4 AND SEXO = ?5 AND JUNTA = ?6 AND COD_CIRCUNSCRIPCION = ?7 GROUP BY ID_LISTADO" ); //AND ID_LISTADO = ?8
            query.setParameter(1, idProvincia);
            query.setParameter(2, idCanton);
            query.setParameter(3, idParroquia);
            query.setParameter(4, idZona);
            query.setParameter(5, Sexo);
            query.setParameter(6, idJunta);
            query.setParameter(7, Circunscripcion);
        /*      query.setParameter(1, 1);
            query.setParameter(2, 556);
            query.setParameter(3, 6875);
            query.setParameter(4, 6);
            query.setParameter(5, 'M');
            query.setParameter(6, 1);
            query.setParameter(7, 0);*/
            
            
            
//            query.setParameter(1, idListado);
            
            return query.getResultList();

        } catch (Exception ex) {
            Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<Object[]>();
        }
    }
    
    
    ///****** aca

    /**
     * Carga las provincias
     * @param intidignidad
     * @param estado
     * @return 
     */
    public List<Object[]> cargarProvincia(String estado) {
        try {
                 
                    Query query = em.createNativeQuery("SELECT DISTINCT(A.ID_PROVINCIA) CODIGO, A.ID_PROVINCIA ||' - '|| A.PROVINCIA AS PROVINCIA, B.PRELACION FROM T_BASE A, T_PROVINCIA B WHERE A.ID_PROVINCIA=B.ID_PROVINCIA AND A.ESTADO=?1 AND A.TIPO='DOCUMENTO' ORDER BY B.PRELACION");
                    //query.setParameter(1, intidignidad);
                    query.setParameter(1, estado);
                    return query.getResultList();   
              
               
        } catch (Exception ex) {
            Logger.getLogger(TBaseDAORES.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<Object[]>();
        }
    }
    
       
    public List<Object[]> cargarDatosLote(int intidLote,int intidProvincia,String estado) {
        try {                                        
            Query query = em.createNativeQuery("SELECT ID_JUNTA,PROVINCIA,CANTON,PARROQUIA,ID_ZONA,ZONA,SEXO,TAG,DIGNIDAD,NOM_CIRCUNSCRIPCION "
                                              +"FROM T_BASE WHERE ESTADO=?1 AND ID_LOTE=?2 AND ID_PROVINCIA=?3 AND TIPO='DOCUMENTO' "
                                              +" ORDER BY ID_PROVINCIA DESC ,ID_CANTON DESC,COD_CIRCUNSCRIPCION DESC,ID_PARROQUIA DESC,ID_ZONA DESC,SEXO DESC,ID_JUNTA DESC"
                                              );
            
            query.setParameter(1, estado);
            query.setParameter(2, intidLote);
            query.setParameter(3, intidProvincia);
            return query.getResultList();

        } catch (Exception ex) {
            Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<Object[]>();
        }
    }
   
    
    /**
     * Carga los datos de la etiqueta
     * @param tag
     * @return 
     */
    public List<Object[]> cargarDatosTag(String tag) {
        try {
            Query query = em.createNativeQuery("SELECT ID_PROVINCIA,PROVINCIA,ID_CANTON, CANTON,ID_PARROQUIA, PARROQUIA, "
                                              +"ID_ZONA,ID_JUNTA,TIPO,TAG,ID_LOTE,SEXO,NOM_CIRCUNSCRIPCION,DIGNIDAD,ID_DIGNIDAD "
                                              +"FROM T_BASE WHERE TAG=?1"
                                              );
            
            query.setParameter(1, tag);
            return query.getResultList();

        } catch (Exception ex) {
            Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<Object[]>();
        }
    }
    
       
/**
     * Muestra los totales de la junta
     * @param idParroquia
     * @param idZona
     * @param idJunta
     * @return 
     */
    public BigDecimal generarTotalJunta(int idParroquia,int idZona,int idJunta) {
        try {
            Query query = em.createNativeQuery("SELECT COUNT(*) FROM T_BASE WHERE ID_PARROQUIA=?1 "
                                              +"AND ID_ZONA=?2 AND ID_JUNTA=?3 AND TIPO='DOCUMENTO' "
                                              );
            
            query.setParameter(1, idParroquia);
            query.setParameter(2, idZona);
            query.setParameter(3, idJunta);
            return (BigDecimal) query.getSingleResult();

        } catch (Exception ex) {
           Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
                 return null;
        }
    }
    
        
     /**
     * Muestra los totales de las juntas de las etiquetas no empacadas
     * @param idParroquia
     * @param idZona
     * @param idJunta
     * @return 
     */
    public BigDecimal generarTotalJuntaNoEmpacados(int idParroquia,int idZona,int idJunta) {
        try {
            Query query = em.createNativeQuery("SELECT COUNT(*) FROM T_BASE WHERE ID_PARROQUIA=?1 "
                                              +"AND ID_ZONA=?2 AND ID_JUNTA=?3 AND TIPO='PAPELETA'"
                                              +"AND ID_LOTE IS NOT NULL "
                                              );
            
            query.setParameter(1, idParroquia);
            query.setParameter(2, idZona);
            query.setParameter(3, idJunta);
            return (BigDecimal) query.getSingleResult();

        } catch (Exception ex) {
           Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
                 //return new BigDecimal(BigDecimal.ZERO);
        }
        return null;
    }
   
      /**
     * Muestra los totales de las juntas de las etiquetas no empacadas
     * @param tag
     * @return 
     */
    public BigDecimal generarTotalJuntaNoEmpacadosTag(String tag) {
        try {
            Query query = em.createNativeQuery("SELECT COUNT(*) FROM T_BASE WHERE TIPO='DOCUMENTO'"
                                              +" AND TAG=?1 AND ID_LOTE IS NOT NULL"
                                              );
            
            query.setParameter(1, tag);
            return (BigDecimal) query.getSingleResult();

        } catch (Exception ex) {
           Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
                 return null;
        }
    }
    
        /**
     * Actualiza el registro correspondiente al tag leído
     * @param estado
     * @param idLote
     * @param fechaPega
     * @param usuarioPega
     * @param tag 
     */
    public void actualizarEstadoTag(String estado,int idLote,Date fechaPega,String usuarioPega,String tag){
        try{
        em.getTransaction().begin();
        Query query = em.createNativeQuery("UPDATE T_BASE SET ESTADO=?1,ID_LOTE=?2,FECHA_PEGA=?3"
                    + ",USUARIO_PEGA=?4 WHERE TAG=?5 AND ID_LOTE IS NULL");
            
            query.setParameter(1, estado);
            query.setParameter(2, idLote);
            query.setParameter(3, fechaPega);
            query.setParameter(4, usuarioPega);
            query.setParameter(5, tag);
            query.executeUpdate();
            em.getTransaction().commit();
             } catch (Exception ex) {
            Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
    }

        
    /**
     * Muestra el listado de etiquetas leídas ordenados por fecha
     * @param intidLote
     * @param intidProvincia
     * @param estado
     * @return 
     */
    public List<Object[]> cargarDatosLoteFechaPega(int intidLote,int intidProvincia,String estado) {
        try {
            Query query = em.createNativeQuery("SELECT ID_JUNTA,PROVINCIA,CANTON,PARROQUIA,ID_ZONA,ZONA AS AGENCIA,SEXO,TAG,DIGNIDAD,NOM_CIRCUNSCRIPCION "
                                              +"FROM T_BASE WHERE ESTADO=?1 AND ID_LOTE=?2 AND ID_PROVINCIA=?3 AND TIPO='DOCUMENTO' ORDER BY FECHA_PEGA DESC "
                                              );
            
            query.setParameter(1, estado);
            query.setParameter(2, intidLote);
            query.setParameter(3, intidProvincia);
            return query.getResultList();

        } catch (Exception ex) {
         Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<Object[]>();
        }
    }

        /**
     * Actualiza el estado a generada cuando se elimina un registro de la tabla de lotes
     * @param estado
     * @param tipo
     * @param tag 
     */
    public void actualizarEstadoGeneradaDocumento(String estado,String tipo,String tag){
        try{
        int resultado;
        em.getTransaction().begin();
        Query query =em.createNativeQuery("UPDATE T_BASE SET ID_LOTE=NULL,ESTADO=?1 WHERE TAG=?3 AND TIPO=?2");
        query.setParameter(1, estado);
        query.setParameter(2, tipo);
        query.setParameter(3, tag);
          resultado=query.executeUpdate();
            
            em.getTransaction().commit();
             } catch (Exception ex) {
            Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
       
    }
    
        /**
     * Cierra el lote
     * @param estado
     * @param idLote
     * @param fechaCierre
     * @param usuarioCierre 
     */
    public void actualizarEstadoTagCerrado(String estado,int idLote,Date fechaCierre,String usuarioCierre,String tipo){
        try{
            int resultado;
        em.getTransaction().begin();
        Query query = em.createNativeQuery("UPDATE T_LOTE SET ESTADO=?1,FECHA_CIERRE=?3"
                    + ",USUARIO_CIERRE=?4 WHERE ID_LOTE=?2 and TIPO=?5");
            
            query.setParameter(1, estado);
            query.setParameter(2, idLote);
            query.setParameter(3, fechaCierre);
            query.setParameter(4, usuarioCierre);
            query.setParameter(5, tipo);
            resultado=query.executeUpdate();
            
            em.getTransaction().commit();
             } catch (Exception ex) {
            Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
    }
    
         /**
     * Carga los datos de los tags que ya fueron leídos por lote
     * @param intidLote
     * @param tipo
     * @return 
     */
    public List<Object[]> cargarDatosLoteEmpacados(int intidLote,String tipo) {
        try {
            Query query = em.createNativeQuery("SELECT ID_JUNTA,PROVINCIA,CANTON,PARROQUIA,ID_ZONA,ZONA AS AGENCIA,SEXO,TAG,DIGNIDAD,NOM_CIRCUNSCRIPCION "
                                              +"FROM T_BASE WHERE ID_LOTE=?1 AND TIPO=?2 ORDER BY FECHA_PEGA DESC "
                                              );
            
            query.setParameter(1, intidLote);
            query.setParameter(2, tipo);
            return query.getResultList();

        } catch (Exception ex) {
         Logger.getLogger(TBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<Object[]>();
        }
    }
    
    
}
