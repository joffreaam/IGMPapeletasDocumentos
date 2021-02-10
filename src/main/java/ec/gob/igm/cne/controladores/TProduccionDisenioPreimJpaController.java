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
import ec.gob.igm.cne.entidades.TDocumentosPreimpresos;
import ec.gob.igm.cne.entidades.TProduccionDisenioPreim;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desarrollo
 */
public class TProduccionDisenioPreimJpaController implements Serializable {

    public TProduccionDisenioPreimJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TProduccionDisenioPreim TProduccionDisenioPreim) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDocumentosPreimpresos idTipoDocumento = TProduccionDisenioPreim.getIdTipoDocumento();
            if (idTipoDocumento != null) {
                idTipoDocumento = em.getReference(idTipoDocumento.getClass(), idTipoDocumento.getId());
                TProduccionDisenioPreim.setIdTipoDocumento(idTipoDocumento);
            }
            em.persist(TProduccionDisenioPreim);
            if (idTipoDocumento != null) {
                idTipoDocumento.getTProduccionDisenioPreimList().add(TProduccionDisenioPreim);
                idTipoDocumento = em.merge(idTipoDocumento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTProduccionDisenioPreim(TProduccionDisenioPreim.getId()) != null) {
                throw new PreexistingEntityException("TProduccionDisenioPreim " + TProduccionDisenioPreim + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TProduccionDisenioPreim TProduccionDisenioPreim) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TProduccionDisenioPreim persistentTProduccionDisenioPreim = em.find(TProduccionDisenioPreim.class, TProduccionDisenioPreim.getId());
            TDocumentosPreimpresos idTipoDocumentoOld = persistentTProduccionDisenioPreim.getIdTipoDocumento();
            TDocumentosPreimpresos idTipoDocumentoNew = TProduccionDisenioPreim.getIdTipoDocumento();
            if (idTipoDocumentoNew != null) {
                idTipoDocumentoNew = em.getReference(idTipoDocumentoNew.getClass(), idTipoDocumentoNew.getId());
                TProduccionDisenioPreim.setIdTipoDocumento(idTipoDocumentoNew);
            }
            TProduccionDisenioPreim = em.merge(TProduccionDisenioPreim);
            if (idTipoDocumentoOld != null && !idTipoDocumentoOld.equals(idTipoDocumentoNew)) {
                idTipoDocumentoOld.getTProduccionDisenioPreimList().remove(TProduccionDisenioPreim);
                idTipoDocumentoOld = em.merge(idTipoDocumentoOld);
            }
            if (idTipoDocumentoNew != null && !idTipoDocumentoNew.equals(idTipoDocumentoOld)) {
                idTipoDocumentoNew.getTProduccionDisenioPreimList().add(TProduccionDisenioPreim);
                idTipoDocumentoNew = em.merge(idTipoDocumentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = TProduccionDisenioPreim.getId();
                if (findTProduccionDisenioPreim(id) == null) {
                    throw new NonexistentEntityException("The tProduccionDisenioPreim with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TProduccionDisenioPreim TProduccionDisenioPreim;
            try {
                TProduccionDisenioPreim = em.getReference(TProduccionDisenioPreim.class, id);
                TProduccionDisenioPreim.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TProduccionDisenioPreim with id " + id + " no longer exists.", enfe);
            }
            TDocumentosPreimpresos idTipoDocumento = TProduccionDisenioPreim.getIdTipoDocumento();
            if (idTipoDocumento != null) {
                idTipoDocumento.getTProduccionDisenioPreimList().remove(TProduccionDisenioPreim);
                idTipoDocumento = em.merge(idTipoDocumento);
            }
            em.remove(TProduccionDisenioPreim);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TProduccionDisenioPreim> findTProduccionDisenioPreimEntities() {
        return findTProduccionDisenioPreimEntities(true, -1, -1);
    }

    public List<TProduccionDisenioPreim> findTProduccionDisenioPreimEntities(int maxResults, int firstResult) {
        return findTProduccionDisenioPreimEntities(false, maxResults, firstResult);
    }

    private List<TProduccionDisenioPreim> findTProduccionDisenioPreimEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TProduccionDisenioPreim.class));
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

    public TProduccionDisenioPreim findTProduccionDisenioPreim(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TProduccionDisenioPreim.class, id);
        } finally {
            em.close();
        }
    }

    public int getTProduccionDisenioPreimCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TProduccionDisenioPreim> rt = cq.from(TProduccionDisenioPreim.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
