/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import ec.gob.igm.cne.entidades.TEstructuraTipodocVsCampo;
import ec.gob.igm.cne.entidades.TEstructuraTipodocVsCampoPK;
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
public class TEstructuraTipodocVsCampoJpaController implements Serializable {

    public TEstructuraTipodocVsCampoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TEstructuraTipodocVsCampo TEstructuraTipodocVsCampo) throws PreexistingEntityException, Exception {
        if (TEstructuraTipodocVsCampo.getTEstructuraTipodocVsCampoPK() == null) {
            TEstructuraTipodocVsCampo.setTEstructuraTipodocVsCampoPK(new TEstructuraTipodocVsCampoPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(TEstructuraTipodocVsCampo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTEstructuraTipodocVsCampo(TEstructuraTipodocVsCampo.getTEstructuraTipodocVsCampoPK()) != null) {
                throw new PreexistingEntityException("TEstructuraTipodocVsCampo " + TEstructuraTipodocVsCampo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TEstructuraTipodocVsCampo TEstructuraTipodocVsCampo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TEstructuraTipodocVsCampo = em.merge(TEstructuraTipodocVsCampo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TEstructuraTipodocVsCampoPK id = TEstructuraTipodocVsCampo.getTEstructuraTipodocVsCampoPK();
                if (findTEstructuraTipodocVsCampo(id) == null) {
                    throw new NonexistentEntityException("The tEstructuraTipodocVsCampo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TEstructuraTipodocVsCampoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TEstructuraTipodocVsCampo TEstructuraTipodocVsCampo;
            try {
                TEstructuraTipodocVsCampo = em.getReference(TEstructuraTipodocVsCampo.class, id);
                TEstructuraTipodocVsCampo.getTEstructuraTipodocVsCampoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TEstructuraTipodocVsCampo with id " + id + " no longer exists.", enfe);
            }
            em.remove(TEstructuraTipodocVsCampo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TEstructuraTipodocVsCampo> findTEstructuraTipodocVsCampoEntities() {
        return findTEstructuraTipodocVsCampoEntities(true, -1, -1);
    }

    public List<TEstructuraTipodocVsCampo> findTEstructuraTipodocVsCampoEntities(int maxResults, int firstResult) {
        return findTEstructuraTipodocVsCampoEntities(false, maxResults, firstResult);
    }

    private List<TEstructuraTipodocVsCampo> findTEstructuraTipodocVsCampoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TEstructuraTipodocVsCampo.class));
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

    public TEstructuraTipodocVsCampo findTEstructuraTipodocVsCampo(TEstructuraTipodocVsCampoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TEstructuraTipodocVsCampo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTEstructuraTipodocVsCampoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TEstructuraTipodocVsCampo> rt = cq.from(TEstructuraTipodocVsCampo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
