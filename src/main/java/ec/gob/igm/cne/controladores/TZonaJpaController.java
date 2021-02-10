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
import ec.gob.igm.cne.entidades.TParroquia;
import ec.gob.igm.cne.entidades.TZona;
import ec.gob.igm.cne.entidades.TZonaPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desarrollo
 */
public class TZonaJpaController implements Serializable {

    public TZonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TZona TZona) throws PreexistingEntityException, Exception {
        if (TZona.getTZonaPK() == null) {
            TZona.setTZonaPK(new TZonaPK());
        }
        TZona.getTZonaPK().setIdParroquia(TZona.getTParroquia().getIdParroquia());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TParroquia TParroquia = TZona.getTParroquia();
            if (TParroquia != null) {
                TParroquia = em.getReference(TParroquia.getClass(), TParroquia.getIdParroquia());
                TZona.setTParroquia(TParroquia);
            }
            em.persist(TZona);
            if (TParroquia != null) {
                TParroquia.getTZonaList().add(TZona);
                TParroquia = em.merge(TParroquia);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTZona(TZona.getTZonaPK()) != null) {
                throw new PreexistingEntityException("TZona " + TZona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TZona TZona) throws NonexistentEntityException, Exception {
        TZona.getTZonaPK().setIdParroquia(TZona.getTParroquia().getIdParroquia());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TZona persistentTZona = em.find(TZona.class, TZona.getTZonaPK());
            TParroquia TParroquiaOld = persistentTZona.getTParroquia();
            TParroquia TParroquiaNew = TZona.getTParroquia();
            if (TParroquiaNew != null) {
                TParroquiaNew = em.getReference(TParroquiaNew.getClass(), TParroquiaNew.getIdParroquia());
                TZona.setTParroquia(TParroquiaNew);
            }
            TZona = em.merge(TZona);
            if (TParroquiaOld != null && !TParroquiaOld.equals(TParroquiaNew)) {
                TParroquiaOld.getTZonaList().remove(TZona);
                TParroquiaOld = em.merge(TParroquiaOld);
            }
            if (TParroquiaNew != null && !TParroquiaNew.equals(TParroquiaOld)) {
                TParroquiaNew.getTZonaList().add(TZona);
                TParroquiaNew = em.merge(TParroquiaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TZonaPK id = TZona.getTZonaPK();
                if (findTZona(id) == null) {
                    throw new NonexistentEntityException("The tZona with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TZonaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TZona TZona;
            try {
                TZona = em.getReference(TZona.class, id);
                TZona.getTZonaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TZona with id " + id + " no longer exists.", enfe);
            }
            TParroquia TParroquia = TZona.getTParroquia();
            if (TParroquia != null) {
                TParroquia.getTZonaList().remove(TZona);
                TParroquia = em.merge(TParroquia);
            }
            em.remove(TZona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TZona> findTZonaEntities() {
        return findTZonaEntities(true, -1, -1);
    }

    public List<TZona> findTZonaEntities(int maxResults, int firstResult) {
        return findTZonaEntities(false, maxResults, firstResult);
    }

    private List<TZona> findTZonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TZona.class));
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

    public TZona findTZona(TZonaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TZona.class, id);
        } finally {
            em.close();
        }
    }

    public int getTZonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TZona> rt = cq.from(TZona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
