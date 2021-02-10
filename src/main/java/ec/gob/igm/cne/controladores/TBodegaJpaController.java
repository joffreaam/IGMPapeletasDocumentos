/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import ec.gob.igm.cne.entidades.TBodega;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author desarrollo
 */
public class TBodegaJpaController implements Serializable {

    public TBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TBodega TBodega) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(TBodega);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTBodega(TBodega.getIdbodega()) != null) {
                throw new PreexistingEntityException("TBodega " + TBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TBodega TBodega) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TBodega = em.merge(TBodega);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TBodega.getIdbodega();
                if (findTBodega(id) == null) {
                    throw new NonexistentEntityException("The tBodega with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TBodega TBodega;
            try {
                TBodega = em.getReference(TBodega.class, id);
                TBodega.getIdbodega();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TBodega with id " + id + " no longer exists.", enfe);
            }
            em.remove(TBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TBodega> findTBodegaEntities() {
        return findTBodegaEntities(true, -1, -1);
    }

    public List<TBodega> findTBodegaEntities(int maxResults, int firstResult) {
        return findTBodegaEntities(false, maxResults, firstResult);
    }

    private List<TBodega> findTBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TBodega.class));
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

    public TBodega findTBodega(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getTBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TBodega> rt = cq.from(TBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
