/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import ec.gob.igm.cne.entidades.TOperadores;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author desarrollo
 */
public class TOperadoresJpaController implements Serializable {

    public TOperadoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TOperadores TOperadores) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(TOperadores);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTOperadores(TOperadores.getIdOperador()) != null) {
                throw new PreexistingEntityException("TOperadores " + TOperadores + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TOperadores TOperadores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TOperadores = em.merge(TOperadores);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TOperadores.getIdOperador();
                if (findTOperadores(id) == null) {
                    throw new NonexistentEntityException("The tOperadores with id " + id + " no longer exists.");
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
            TOperadores TOperadores;
            try {
                TOperadores = em.getReference(TOperadores.class, id);
                TOperadores.getIdOperador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TOperadores with id " + id + " no longer exists.", enfe);
            }
            em.remove(TOperadores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TOperadores> findTOperadoresEntities() {
        return findTOperadoresEntities(true, -1, -1);
    }

    public List<TOperadores> findTOperadoresEntities(int maxResults, int firstResult) {
        return findTOperadoresEntities(false, maxResults, firstResult);
    }

    private List<TOperadores> findTOperadoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TOperadores.class));
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

    public TOperadores findTOperadores(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TOperadores.class, id);
        } finally {
            em.close();
        }
    }

    public int getTOperadoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TOperadores> rt = cq.from(TOperadores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
