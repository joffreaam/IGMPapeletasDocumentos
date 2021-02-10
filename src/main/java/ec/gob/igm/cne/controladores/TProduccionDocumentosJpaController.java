/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import ec.gob.igm.cne.entidades.TProduccionDocumentos;
import ec.gob.igm.cne.entidades.TProduccionDocumentosPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.gob.igm.cne.entidades.TProvincia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desarrollo
 */
public class TProduccionDocumentosJpaController implements Serializable {

    public TProduccionDocumentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TProduccionDocumentos TProduccionDocumentos) throws PreexistingEntityException, Exception {
        if (TProduccionDocumentos.getTProduccionDocumentosPK() == null) {
            TProduccionDocumentos.setTProduccionDocumentosPK(new TProduccionDocumentosPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TProvincia idProvincia = TProduccionDocumentos.getIdProvincia();
            if (idProvincia != null) {
                idProvincia = em.getReference(idProvincia.getClass(), idProvincia.getIdProvincia());
                TProduccionDocumentos.setIdProvincia(idProvincia);
            }
            em.persist(TProduccionDocumentos);
            if (idProvincia != null) {
                idProvincia.getTProduccionDocumentosList().add(TProduccionDocumentos);
                idProvincia = em.merge(idProvincia);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTProduccionDocumentos(TProduccionDocumentos.getTProduccionDocumentosPK()) != null) {
                throw new PreexistingEntityException("TProduccionDocumentos " + TProduccionDocumentos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TProduccionDocumentos TProduccionDocumentos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TProduccionDocumentos persistentTProduccionDocumentos = em.find(TProduccionDocumentos.class, TProduccionDocumentos.getTProduccionDocumentosPK());
            TProvincia idProvinciaOld = persistentTProduccionDocumentos.getIdProvincia();
            TProvincia idProvinciaNew = TProduccionDocumentos.getIdProvincia();
            if (idProvinciaNew != null) {
                idProvinciaNew = em.getReference(idProvinciaNew.getClass(), idProvinciaNew.getIdProvincia());
                TProduccionDocumentos.setIdProvincia(idProvinciaNew);
            }
            TProduccionDocumentos = em.merge(TProduccionDocumentos);
            if (idProvinciaOld != null && !idProvinciaOld.equals(idProvinciaNew)) {
                idProvinciaOld.getTProduccionDocumentosList().remove(TProduccionDocumentos);
                idProvinciaOld = em.merge(idProvinciaOld);
            }
            if (idProvinciaNew != null && !idProvinciaNew.equals(idProvinciaOld)) {
                idProvinciaNew.getTProduccionDocumentosList().add(TProduccionDocumentos);
                idProvinciaNew = em.merge(idProvinciaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TProduccionDocumentosPK id = TProduccionDocumentos.getTProduccionDocumentosPK();
                if (findTProduccionDocumentos(id) == null) {
                    throw new NonexistentEntityException("The tProduccionDocumentos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TProduccionDocumentosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TProduccionDocumentos TProduccionDocumentos;
            try {
                TProduccionDocumentos = em.getReference(TProduccionDocumentos.class, id);
                TProduccionDocumentos.getTProduccionDocumentosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TProduccionDocumentos with id " + id + " no longer exists.", enfe);
            }
            TProvincia idProvincia = TProduccionDocumentos.getIdProvincia();
            if (idProvincia != null) {
                idProvincia.getTProduccionDocumentosList().remove(TProduccionDocumentos);
                idProvincia = em.merge(idProvincia);
            }
            em.remove(TProduccionDocumentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TProduccionDocumentos> findTProduccionDocumentosEntities() {
        return findTProduccionDocumentosEntities(true, -1, -1);
    }

    public List<TProduccionDocumentos> findTProduccionDocumentosEntities(int maxResults, int firstResult) {
        return findTProduccionDocumentosEntities(false, maxResults, firstResult);
    }

    private List<TProduccionDocumentos> findTProduccionDocumentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TProduccionDocumentos.class));
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

    public TProduccionDocumentos findTProduccionDocumentos(TProduccionDocumentosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TProduccionDocumentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTProduccionDocumentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TProduccionDocumentos> rt = cq.from(TProduccionDocumentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
