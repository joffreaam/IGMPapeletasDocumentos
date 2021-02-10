/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import ec.gob.igm.cne.entidades.TCircunscripcionCanton;
import ec.gob.igm.cne.entidades.TCircunscripcionCantonPK;
import java.io.Serializable;
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
public class TCircunscripcionCantonJpaController implements Serializable {

    public TCircunscripcionCantonJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TCircunscripcionCanton TCircunscripcionCanton) throws PreexistingEntityException, Exception {
        if (TCircunscripcionCanton.getTCircunscripcionCantonPK() == null) {
            TCircunscripcionCanton.setTCircunscripcionCantonPK(new TCircunscripcionCantonPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(TCircunscripcionCanton);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTCircunscripcionCanton(TCircunscripcionCanton.getTCircunscripcionCantonPK()) != null) {
                throw new PreexistingEntityException("TCircunscripcionCanton " + TCircunscripcionCanton + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TCircunscripcionCanton TCircunscripcionCanton) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TCircunscripcionCanton = em.merge(TCircunscripcionCanton);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TCircunscripcionCantonPK id = TCircunscripcionCanton.getTCircunscripcionCantonPK();
                if (findTCircunscripcionCanton(id) == null) {
                    throw new NonexistentEntityException("The tCircunscripcionCanton with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TCircunscripcionCantonPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TCircunscripcionCanton TCircunscripcionCanton;
            try {
                TCircunscripcionCanton = em.getReference(TCircunscripcionCanton.class, id);
                TCircunscripcionCanton.getTCircunscripcionCantonPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TCircunscripcionCanton with id " + id + " no longer exists.", enfe);
            }
            em.remove(TCircunscripcionCanton);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TCircunscripcionCanton> findTCircunscripcionCantonEntities() {
        return findTCircunscripcionCantonEntities(true, -1, -1);
    }

    public List<TCircunscripcionCanton> findTCircunscripcionCantonEntities(int maxResults, int firstResult) {
        return findTCircunscripcionCantonEntities(false, maxResults, firstResult);
    }

    private List<TCircunscripcionCanton> findTCircunscripcionCantonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TCircunscripcionCanton.class));
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

    public TCircunscripcionCanton findTCircunscripcionCanton(TCircunscripcionCantonPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TCircunscripcionCanton.class, id);
        } finally {
            em.close();
        }
    }

    public int getTCircunscripcionCantonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TCircunscripcionCanton> rt = cq.from(TCircunscripcionCanton.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
