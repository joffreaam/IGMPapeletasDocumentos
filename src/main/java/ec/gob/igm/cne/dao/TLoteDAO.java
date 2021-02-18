/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.dao;

//import ec.gob.igm.cne.documentos.LoteDocumentos;       
import ec.gob.igm.cne.dao.CrudDAO;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;

/**
 *
 * @author VERA_MAYRA
 */
public class TLoteDAO extends CrudDAO {
   
    /**
     * Carga el id del lote
     * @param idProvincia
     * @param estado
     * @param usuarioCreacion
     * @return 
     */
    public int cargarLote(int idProvincia,String estado,String usuarioCreacion) {
        try{    
            Object lote;
            int idlote;
            Query query = super.em.createQuery("SELECT min(t.tLotePK.idLote) FROM TLote t where t.idProvincia.idProvincia=?1 and t.estado=?2 and t.usuarioCreacion=?3 and t.tLotePK.tipo='DOCUMENTO'");
            query.setParameter(1, idProvincia);
            query.setParameter(2, estado);
            query.setParameter(3, usuarioCreacion);
            //lote=;
            idlote=query.getResultList().isEmpty()?0:(int)query.getSingleResult();  
            return idlote;
            }catch(Exception ex){
                 Logger.getLogger(TLoteDAO.class.getName()).log(Level.SEVERE, null, ex);
                 return 0;
            }
    }
    
    /**
     * Carga el lote de zonas de voto en casa y ppls
     * @param idProvincia
     * @param estado
     * @param usuarioCreacion
     * @return 
     */
    public int cargarLoteZona(int idProvincia,String estado,String usuarioCreacion) {
        try{    
            Object lote;
            int idlote;
            Query query = super.em.createQuery("SELECT t.tLotePK.idLote FROM TLote t where t.idProvincia.idProvincia=?1 and t.estado=?2 and t.usuarioCreacion=?3 and t.tLotePK.tipo='PAPELETA'");
            query.setParameter(1, idProvincia);
            query.setParameter(2, estado);
            query.setParameter(3, usuarioCreacion);
            //lote=query.getSingleResult();
            idlote=query.getResultList().isEmpty()?0:(int)query.getSingleResult(); 
            return idlote;
            }catch(Exception ex){
                 Logger.getLogger(TLoteDAO.class.getName()).log(Level.SEVERE, null, ex);
                 return 0;
            }
    }
    
    /**
     * Carga el lote máximo más uno
     * @param idProvincia
     * @param estado
     * @param usuarioCreacion
     * @return 
     */
    public int cargarMaximoLote() {
        int resultado;
        try{  
            
            Query query = super.em.createQuery("SELECT MAX(t.tLotePK.idLote)+1 FROM TLote t where t.tLotePK.tipo='DOCUMENTO'");
             resultado=query.getResultList().isEmpty()?0:(int)query.getSingleResult(); 
            //return (int) query.getSingleResult();
            }catch(Exception ex){
                 Logger.getLogger(TLoteDAO.class.getName()).log(Level.SEVERE, null, ex);
                 return 0;
            }
        return resultado;
    }
    
    /**
     * Ingresa los lotes por zona de voto en casa y ppl
     * @param idLote
     * @param idProvincia
     * @param estado
     * @param fechaCreacion
     * @param usuario
     * @param tipo
     * @param idBodega 
     */ 
    public void ingresarLoteZona(int idLote,int idProvincia,String estado,Date fechaCreacion,String usuario,String tipo,BigInteger idBodega){
        try{
        em.getTransaction().begin();
        Query query = em.createNativeQuery("INSERT ID_LOTE,ID_PROVINCIA,ESTADO,FECHA_CREACION,USUARIO_CREACION,TIPO,ID_BODEGA INTO T_REIMPRESION "
                + "VALUES (?1,?2,?3,?4,?5,?6,?7)");
            query.setParameter(1, idLote);
            query.setParameter(2, idProvincia);
            query.setParameter(3, estado);
            query.setParameter(4, fechaCreacion);
            query.setParameter(5, usuario);
            query.setParameter(6, tipo);
            query.setParameter(7, idBodega);
            query.executeUpdate();
            em.getTransaction().commit();
             } catch (Exception ex) {
            Logger.getLogger(TLoteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
    }
    
    
    
}
