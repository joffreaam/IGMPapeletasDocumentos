/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import ec.gob.igm.cne.entidades.TEstructuraCampo;
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
public class TEstructuraCampoJpaController implements Serializable {

    public TEstructuraCampoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TEstructuraCampo TEstructuraCampo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(TEstructuraCampo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTEstructuraCampo(TEstructuraCampo.getIdCampo()) != null) {
                throw new PreexistingEntityException("TEstructuraCampo " + TEstructuraCampo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TEstructuraCampo TEstructuraCampo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TEstructuraCampo = em.merge(TEstructuraCampo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TEstructuraCampo.getIdCampo();
                if (findTEstructuraCampo(id) == null) {
                    throw new NonexistentEntityException("The tEstructuraCampo with id " + id + " no longer exists.");
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
            TEstructuraCampo TEstructuraCampo;
            try {
                TEstructuraCampo = em.getReference(TEstructuraCampo.class, id);
                TEstructuraCampo.getIdCampo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TEstructuraCampo with id " + id + " no longer exists.", enfe);
            }
            em.remove(TEstructuraCampo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TEstructuraCampo> findTEstructuraCampoEntities() {
        return findTEstructuraCampoEntities(true, -1, -1);
    }

    public List<TEstructuraCampo> findTEstructuraCampoEntities(int maxResults, int firstResult) {
        return findTEstructuraCampoEntities(false, maxResults, firstResult);
    }

    private List<TEstructuraCampo> findTEstructuraCampoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TEstructuraCampo.class));
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

    public TEstructuraCampo findTEstructuraCampo(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TEstructuraCampo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTEstructuraCampoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TEstructuraCampo> rt = cq.from(TEstructuraCampo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
