/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import ec.gob.igm.cne.entidades.TTipoEstructura;
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
public class TTipoEstructuraJpaController implements Serializable {

    public TTipoEstructuraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TTipoEstructura TTipoEstructura) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(TTipoEstructura);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTTipoEstructura(TTipoEstructura.getIdEstructura()) != null) {
                throw new PreexistingEntityException("TTipoEstructura " + TTipoEstructura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TTipoEstructura TTipoEstructura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TTipoEstructura = em.merge(TTipoEstructura);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TTipoEstructura.getIdEstructura();
                if (findTTipoEstructura(id) == null) {
                    throw new NonexistentEntityException("The tTipoEstructura with id " + id + " no longer exists.");
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
            TTipoEstructura TTipoEstructura;
            try {
                TTipoEstructura = em.getReference(TTipoEstructura.class, id);
                TTipoEstructura.getIdEstructura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TTipoEstructura with id " + id + " no longer exists.", enfe);
            }
            em.remove(TTipoEstructura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TTipoEstructura> findTTipoEstructuraEntities() {
        return findTTipoEstructuraEntities(true, -1, -1);
    }

    public List<TTipoEstructura> findTTipoEstructuraEntities(int maxResults, int firstResult) {
        return findTTipoEstructuraEntities(false, maxResults, firstResult);
    }

    private List<TTipoEstructura> findTTipoEstructuraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TTipoEstructura.class));
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

    public TTipoEstructura findTTipoEstructura(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TTipoEstructura.class, id);
        } finally {
            em.close();
        }
    }

    public int getTTipoEstructuraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TTipoEstructura> rt = cq.from(TTipoEstructura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
