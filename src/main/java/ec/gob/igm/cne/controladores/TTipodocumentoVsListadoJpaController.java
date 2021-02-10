/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import ec.gob.igm.cne.entidades.TTipodocumentoVsListado;
import ec.gob.igm.cne.entidades.TTipodocumentoVsListadoPK;
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
public class TTipodocumentoVsListadoJpaController implements Serializable {

    public TTipodocumentoVsListadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ec.gob.igm_PapeletasDocumentos_jar_1.0-SNAPSHOTPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TTipodocumentoVsListado TTipodocumentoVsListado) throws PreexistingEntityException, Exception {
        if (TTipodocumentoVsListado.getTTipodocumentoVsListadoPK() == null) {
            TTipodocumentoVsListado.setTTipodocumentoVsListadoPK(new TTipodocumentoVsListadoPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(TTipodocumentoVsListado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTTipodocumentoVsListado(TTipodocumentoVsListado.getTTipodocumentoVsListadoPK()) != null) {
                throw new PreexistingEntityException("TTipodocumentoVsListado " + TTipodocumentoVsListado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TTipodocumentoVsListado TTipodocumentoVsListado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TTipodocumentoVsListado = em.merge(TTipodocumentoVsListado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TTipodocumentoVsListadoPK id = TTipodocumentoVsListado.getTTipodocumentoVsListadoPK();
                if (findTTipodocumentoVsListado(id) == null) {
                    throw new NonexistentEntityException("The tTipodocumentoVsListado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TTipodocumentoVsListadoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TTipodocumentoVsListado TTipodocumentoVsListado;
            try {
                TTipodocumentoVsListado = em.getReference(TTipodocumentoVsListado.class, id);
                TTipodocumentoVsListado.getTTipodocumentoVsListadoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TTipodocumentoVsListado with id " + id + " no longer exists.", enfe);
            }
            em.remove(TTipodocumentoVsListado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TTipodocumentoVsListado> findTTipodocumentoVsListadoEntities() {
        return findTTipodocumentoVsListadoEntities(true, -1, -1);
    }

    public List<TTipodocumentoVsListado> findTTipodocumentoVsListadoEntities(int maxResults, int firstResult) {
        return findTTipodocumentoVsListadoEntities(false, maxResults, firstResult);
    }

    private List<TTipodocumentoVsListado> findTTipodocumentoVsListadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TTipodocumentoVsListado.class));
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

    public TTipodocumentoVsListado findTTipodocumentoVsListado(TTipodocumentoVsListadoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TTipodocumentoVsListado.class, id);
        } finally {
            em.close();
        }
    }

    public int getTTipodocumentoVsListadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TTipodocumentoVsListado> rt = cq.from(TTipodocumentoVsListado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
