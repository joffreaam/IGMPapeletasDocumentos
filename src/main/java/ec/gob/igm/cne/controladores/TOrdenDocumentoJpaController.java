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
import ec.gob.igm.cne.entidades.TListadoDocumentos;
import ec.gob.igm.cne.entidades.TOrdenDocumento;
import ec.gob.igm.cne.entidades.TOrdenDocumentoPK;
import ec.gob.igm.cne.entidades.TTipoDocumento;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desarrollo
 */
public class TOrdenDocumentoJpaController implements Serializable {

    public TOrdenDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TOrdenDocumento TOrdenDocumento) throws PreexistingEntityException, Exception {
        if (TOrdenDocumento.getTOrdenDocumentoPK() == null) {
            TOrdenDocumento.setTOrdenDocumentoPK(new TOrdenDocumentoPK());
        }
        /*Casting de BigDecimal a BigInteger, ya que el IDE no lo hizo automaticamente en la creación
        */
        TOrdenDocumento.getTOrdenDocumentoPK().setIdKit(TOrdenDocumento.getTTipoDocumento().getIdKit().toBigInteger());
        TOrdenDocumento.getTOrdenDocumentoPK().setIdListado(TOrdenDocumento.getTListadoDocumentos().getIdListado().toBigInteger());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TListadoDocumentos TListadoDocumentos = TOrdenDocumento.getTListadoDocumentos();
            if (TListadoDocumentos != null) {
                TListadoDocumentos = em.getReference(TListadoDocumentos.getClass(), TListadoDocumentos.getIdListado());
                TOrdenDocumento.setTListadoDocumentos(TListadoDocumentos);
            }
            TTipoDocumento TTipoDocumento = TOrdenDocumento.getTTipoDocumento();
            if (TTipoDocumento != null) {
                TTipoDocumento = em.getReference(TTipoDocumento.getClass(), TTipoDocumento.getIdKit());
                TOrdenDocumento.setTTipoDocumento(TTipoDocumento);
            }
            em.persist(TOrdenDocumento);
            if (TListadoDocumentos != null) {
                TListadoDocumentos.getTOrdenDocumentoList().add(TOrdenDocumento);
                TListadoDocumentos = em.merge(TListadoDocumentos);
            }
            if (TTipoDocumento != null) {
                TTipoDocumento.getTOrdenDocumentoList().add(TOrdenDocumento);
                TTipoDocumento = em.merge(TTipoDocumento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTOrdenDocumento(TOrdenDocumento.getTOrdenDocumentoPK()) != null) {
                throw new PreexistingEntityException("TOrdenDocumento " + TOrdenDocumento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TOrdenDocumento TOrdenDocumento) throws NonexistentEntityException, Exception {
        /*Casting de BigDecimal a BigInteger, ya que el IDE no lo hizo automaticamente en la creación        
        */
        TOrdenDocumento.getTOrdenDocumentoPK().setIdKit(TOrdenDocumento.getTTipoDocumento().getIdKit().toBigInteger());
        TOrdenDocumento.getTOrdenDocumentoPK().setIdListado(TOrdenDocumento.getTListadoDocumentos().getIdListado().toBigInteger());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TOrdenDocumento persistentTOrdenDocumento = em.find(TOrdenDocumento.class, TOrdenDocumento.getTOrdenDocumentoPK());
            TListadoDocumentos TListadoDocumentosOld = persistentTOrdenDocumento.getTListadoDocumentos();
            TListadoDocumentos TListadoDocumentosNew = TOrdenDocumento.getTListadoDocumentos();
            TTipoDocumento TTipoDocumentoOld = persistentTOrdenDocumento.getTTipoDocumento();
            TTipoDocumento TTipoDocumentoNew = TOrdenDocumento.getTTipoDocumento();
            if (TListadoDocumentosNew != null) {
                TListadoDocumentosNew = em.getReference(TListadoDocumentosNew.getClass(), TListadoDocumentosNew.getIdListado());
                TOrdenDocumento.setTListadoDocumentos(TListadoDocumentosNew);
            }
            if (TTipoDocumentoNew != null) {
                TTipoDocumentoNew = em.getReference(TTipoDocumentoNew.getClass(), TTipoDocumentoNew.getIdKit());
                TOrdenDocumento.setTTipoDocumento(TTipoDocumentoNew);
            }
            TOrdenDocumento = em.merge(TOrdenDocumento);
            if (TListadoDocumentosOld != null && !TListadoDocumentosOld.equals(TListadoDocumentosNew)) {
                TListadoDocumentosOld.getTOrdenDocumentoList().remove(TOrdenDocumento);
                TListadoDocumentosOld = em.merge(TListadoDocumentosOld);
            }
            if (TListadoDocumentosNew != null && !TListadoDocumentosNew.equals(TListadoDocumentosOld)) {
                TListadoDocumentosNew.getTOrdenDocumentoList().add(TOrdenDocumento);
                TListadoDocumentosNew = em.merge(TListadoDocumentosNew);
            }
            if (TTipoDocumentoOld != null && !TTipoDocumentoOld.equals(TTipoDocumentoNew)) {
                TTipoDocumentoOld.getTOrdenDocumentoList().remove(TOrdenDocumento);
                TTipoDocumentoOld = em.merge(TTipoDocumentoOld);
            }
            if (TTipoDocumentoNew != null && !TTipoDocumentoNew.equals(TTipoDocumentoOld)) {
                TTipoDocumentoNew.getTOrdenDocumentoList().add(TOrdenDocumento);
                TTipoDocumentoNew = em.merge(TTipoDocumentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TOrdenDocumentoPK id = TOrdenDocumento.getTOrdenDocumentoPK();
                if (findTOrdenDocumento(id) == null) {
                    throw new NonexistentEntityException("The tOrdenDocumento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TOrdenDocumentoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TOrdenDocumento TOrdenDocumento;
            try {
                TOrdenDocumento = em.getReference(TOrdenDocumento.class, id);
                TOrdenDocumento.getTOrdenDocumentoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TOrdenDocumento with id " + id + " no longer exists.", enfe);
            }
            TListadoDocumentos TListadoDocumentos = TOrdenDocumento.getTListadoDocumentos();
            if (TListadoDocumentos != null) {
                TListadoDocumentos.getTOrdenDocumentoList().remove(TOrdenDocumento);
                TListadoDocumentos = em.merge(TListadoDocumentos);
            }
            TTipoDocumento TTipoDocumento = TOrdenDocumento.getTTipoDocumento();
            if (TTipoDocumento != null) {
                TTipoDocumento.getTOrdenDocumentoList().remove(TOrdenDocumento);
                TTipoDocumento = em.merge(TTipoDocumento);
            }
            em.remove(TOrdenDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TOrdenDocumento> findTOrdenDocumentoEntities() {
        return findTOrdenDocumentoEntities(true, -1, -1);
    }

    public List<TOrdenDocumento> findTOrdenDocumentoEntities(int maxResults, int firstResult) {
        return findTOrdenDocumentoEntities(false, maxResults, firstResult);
    }

    private List<TOrdenDocumento> findTOrdenDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TOrdenDocumento.class));
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

    public TOrdenDocumento findTOrdenDocumento(TOrdenDocumentoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TOrdenDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTOrdenDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TOrdenDocumento> rt = cq.from(TOrdenDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
