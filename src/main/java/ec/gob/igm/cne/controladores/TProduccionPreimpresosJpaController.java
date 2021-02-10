/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.gob.igm.cne.entidades.TDocumentosPreimpresos;
import ec.gob.igm.cne.entidades.TProduccionPreimpresos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desarrollo
 */
public class TProduccionPreimpresosJpaController implements Serializable {

    public TProduccionPreimpresosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TProduccionPreimpresos TProduccionPreimpresos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDocumentosPreimpresos idTipoDocumento = TProduccionPreimpresos.getIdTipoDocumento();
            if (idTipoDocumento != null) {
                idTipoDocumento = em.getReference(idTipoDocumento.getClass(), idTipoDocumento.getId());
                TProduccionPreimpresos.setIdTipoDocumento(idTipoDocumento);
            }
            em.persist(TProduccionPreimpresos);
            if (idTipoDocumento != null) {
                idTipoDocumento.getTProduccionPreimpresosList().add(TProduccionPreimpresos);
                idTipoDocumento = em.merge(idTipoDocumento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTProduccionPreimpresos(TProduccionPreimpresos.getId()) != null) {
                throw new PreexistingEntityException("TProduccionPreimpresos " + TProduccionPreimpresos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TProduccionPreimpresos TProduccionPreimpresos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TProduccionPreimpresos persistentTProduccionPreimpresos = em.find(TProduccionPreimpresos.class, TProduccionPreimpresos.getId());
            TDocumentosPreimpresos idTipoDocumentoOld = persistentTProduccionPreimpresos.getIdTipoDocumento();
            TDocumentosPreimpresos idTipoDocumentoNew = TProduccionPreimpresos.getIdTipoDocumento();
            if (idTipoDocumentoNew != null) {
                idTipoDocumentoNew = em.getReference(idTipoDocumentoNew.getClass(), idTipoDocumentoNew.getId());
                TProduccionPreimpresos.setIdTipoDocumento(idTipoDocumentoNew);
            }
            TProduccionPreimpresos = em.merge(TProduccionPreimpresos);
            if (idTipoDocumentoOld != null && !idTipoDocumentoOld.equals(idTipoDocumentoNew)) {
                idTipoDocumentoOld.getTProduccionPreimpresosList().remove(TProduccionPreimpresos);
                idTipoDocumentoOld = em.merge(idTipoDocumentoOld);
            }
            if (idTipoDocumentoNew != null && !idTipoDocumentoNew.equals(idTipoDocumentoOld)) {
                idTipoDocumentoNew.getTProduccionPreimpresosList().add(TProduccionPreimpresos);
                idTipoDocumentoNew = em.merge(idTipoDocumentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = TProduccionPreimpresos.getId();
                if (findTProduccionPreimpresos(id) == null) {
                    throw new NonexistentEntityException("The tProduccionPreimpresos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TProduccionPreimpresos TProduccionPreimpresos;
            try {
                TProduccionPreimpresos = em.getReference(TProduccionPreimpresos.class, id);
                TProduccionPreimpresos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TProduccionPreimpresos with id " + id + " no longer exists.", enfe);
            }
            TDocumentosPreimpresos idTipoDocumento = TProduccionPreimpresos.getIdTipoDocumento();
            if (idTipoDocumento != null) {
                idTipoDocumento.getTProduccionPreimpresosList().remove(TProduccionPreimpresos);
                idTipoDocumento = em.merge(idTipoDocumento);
            }
            em.remove(TProduccionPreimpresos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TProduccionPreimpresos> findTProduccionPreimpresosEntities() {
        return findTProduccionPreimpresosEntities(true, -1, -1);
    }

    public List<TProduccionPreimpresos> findTProduccionPreimpresosEntities(int maxResults, int firstResult) {
        return findTProduccionPreimpresosEntities(false, maxResults, firstResult);
    }

    private List<TProduccionPreimpresos> findTProduccionPreimpresosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TProduccionPreimpresos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TProduccionPreimpresos findTProduccionPreimpresos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TProduccionPreimpresos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTProduccionPreimpresosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TProduccionPreimpresos> rt = cq.from(TProduccionPreimpresos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
