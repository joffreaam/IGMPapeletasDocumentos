/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import ec.gob.igm.cne.entidades.TJunta;
import ec.gob.igm.cne.entidades.TJuntaPK;
import java.io.Serializable;
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
public class TJuntaJpaController implements Serializable {

    public TJuntaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TJunta TJunta) throws PreexistingEntityException, Exception {
        if (TJunta.getTJuntaPK() == null) {
            TJunta.setTJuntaPK(new TJuntaPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(TJunta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTJunta(TJunta.getTJuntaPK()) != null) {
                throw new PreexistingEntityException("TJunta " + TJunta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TJunta TJunta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TJunta = em.merge(TJunta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TJuntaPK id = TJunta.getTJuntaPK();
                if (findTJunta(id) == null) {
                    throw new NonexistentEntityException("The tJunta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TJuntaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TJunta TJunta;
            try {
                TJunta = em.getReference(TJunta.class, id);
                TJunta.getTJuntaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TJunta with id " + id + " no longer exists.", enfe);
            }
            em.remove(TJunta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TJunta> findTJuntaEntities() {
        return findTJuntaEntities(true, -1, -1);
    }

    public List<TJunta> findTJuntaEntities(int maxResults, int firstResult) {
        return findTJuntaEntities(false, maxResults, firstResult);
    }

    private List<TJunta> findTJuntaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TJunta.class));
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

    public TJunta findTJunta(TJuntaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TJunta.class, id);
        } finally {
            em.close();
        }
    }

    public int getTJuntaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TJunta> rt = cq.from(TJunta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
