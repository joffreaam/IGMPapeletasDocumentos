/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author VERA_MAYRA
 */
public class CrudDAO {
    
    /** La variable em. */
   //@PersistenceContext(unitName = "com.mycompany_papeletasTag_jar_1.0-SNAPSHOTPU")
   //protected EntityManager em;
   
  private EntityManagerFactory emf = Persistence.createEntityManagerFactory("CNEELECCIONES");
      

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    protected EntityManager em=this.getEntityManager();

    /**
     * Ingresa la información de una entidad.
     * @param entidad Entidad
     * @throws Exception Error lanzado si no se puede ejecutar la acción
     */
    public void insertar(Object entidad) throws Exception {
        try {
            em.getTransaction().begin();
        em.persist(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    /* (non-Javadoc)
     * @see com.gov.ec.ptvop.dao.CrudDAOLocal#actualizar(java.lang.Object)
     */
    public Object actualizar(Object entidad) {
        try {
            entidad = em.merge(entidad);
//            em.refresh(entidad);
        } catch (Exception e) {
           
        }
        return entidad;

    }

    /* (non-Javadoc)
     * @see com.gov.ec.ptvop.dao.CrudDAOLocal#eliminar(java.lang.Object)
     */
    public void eliminar(Object entidad) throws Exception {
        try {
            this.em.remove(em.merge(entidad));
            em.flush();
        } catch (Exception e) {
            
            throw new Exception(e.getMessage(), e);
        }
    }

    /**
     * Realiza la búsqueda de entidades por su clave primaria.
     * @param entidad Entidad a buscar
     * @param clave Clave primaria
     * @return Entidad
     */
    public Object buscar(Object entidad, Object clave) {
        try {
            return em.find(entidad.getClass(), clave);
        } catch (Exception e) {
            
            return null;
        }
    }
}
