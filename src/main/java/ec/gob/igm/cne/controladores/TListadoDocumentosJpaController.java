/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.IllegalOrphanException;
import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import ec.gob.igm.cne.entidades.TListadoDocumentos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.gob.igm.cne.entidades.TOrdenDocumento;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author desarrollo
 */
public class TListadoDocumentosJpaController implements Serializable {

    public TListadoDocumentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TListadoDocumentos TListadoDocumentos) throws PreexistingEntityException, Exception {
        if (TListadoDocumentos.getTOrdenDocumentoList() == null) {
            TListadoDocumentos.setTOrdenDocumentoList(new ArrayList<TOrdenDocumento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<TOrdenDocumento> attachedTOrdenDocumentoList = new ArrayList<TOrdenDocumento>();
            for (TOrdenDocumento TOrdenDocumentoListTOrdenDocumentoToAttach : TListadoDocumentos.getTOrdenDocumentoList()) {
                TOrdenDocumentoListTOrdenDocumentoToAttach = em.getReference(TOrdenDocumentoListTOrdenDocumentoToAttach.getClass(), TOrdenDocumentoListTOrdenDocumentoToAttach.getTOrdenDocumentoPK());
                attachedTOrdenDocumentoList.add(TOrdenDocumentoListTOrdenDocumentoToAttach);
            }
            TListadoDocumentos.setTOrdenDocumentoList(attachedTOrdenDocumentoList);
            em.persist(TListadoDocumentos);
            for (TOrdenDocumento TOrdenDocumentoListTOrdenDocumento : TListadoDocumentos.getTOrdenDocumentoList()) {
                TListadoDocumentos oldTListadoDocumentosOfTOrdenDocumentoListTOrdenDocumento = TOrdenDocumentoListTOrdenDocumento.getTListadoDocumentos();
                TOrdenDocumentoListTOrdenDocumento.setTListadoDocumentos(TListadoDocumentos);
                TOrdenDocumentoListTOrdenDocumento = em.merge(TOrdenDocumentoListTOrdenDocumento);
                if (oldTListadoDocumentosOfTOrdenDocumentoListTOrdenDocumento != null) {
                    oldTListadoDocumentosOfTOrdenDocumentoListTOrdenDocumento.getTOrdenDocumentoList().remove(TOrdenDocumentoListTOrdenDocumento);
                    oldTListadoDocumentosOfTOrdenDocumentoListTOrdenDocumento = em.merge(oldTListadoDocumentosOfTOrdenDocumentoListTOrdenDocumento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTListadoDocumentos(TListadoDocumentos.getIdListado()) != null) {
                throw new PreexistingEntityException("TListadoDocumentos " + TListadoDocumentos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TListadoDocumentos TListadoDocumentos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TListadoDocumentos persistentTListadoDocumentos = em.find(TListadoDocumentos.class, TListadoDocumentos.getIdListado());
            List<TOrdenDocumento> TOrdenDocumentoListOld = persistentTListadoDocumentos.getTOrdenDocumentoList();
            List<TOrdenDocumento> TOrdenDocumentoListNew = TListadoDocumentos.getTOrdenDocumentoList();
            List<String> illegalOrphanMessages = null;
            for (TOrdenDocumento TOrdenDocumentoListOldTOrdenDocumento : TOrdenDocumentoListOld) {
                if (!TOrdenDocumentoListNew.contains(TOrdenDocumentoListOldTOrdenDocumento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TOrdenDocumento " + TOrdenDocumentoListOldTOrdenDocumento + " since its TListadoDocumentos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<TOrdenDocumento> attachedTOrdenDocumentoListNew = new ArrayList<TOrdenDocumento>();
            for (TOrdenDocumento TOrdenDocumentoListNewTOrdenDocumentoToAttach : TOrdenDocumentoListNew) {
                TOrdenDocumentoListNewTOrdenDocumentoToAttach = em.getReference(TOrdenDocumentoListNewTOrdenDocumentoToAttach.getClass(), TOrdenDocumentoListNewTOrdenDocumentoToAttach.getTOrdenDocumentoPK());
                attachedTOrdenDocumentoListNew.add(TOrdenDocumentoListNewTOrdenDocumentoToAttach);
            }
            TOrdenDocumentoListNew = attachedTOrdenDocumentoListNew;
            TListadoDocumentos.setTOrdenDocumentoList(TOrdenDocumentoListNew);
            TListadoDocumentos = em.merge(TListadoDocumentos);
            for (TOrdenDocumento TOrdenDocumentoListNewTOrdenDocumento : TOrdenDocumentoListNew) {
                if (!TOrdenDocumentoListOld.contains(TOrdenDocumentoListNewTOrdenDocumento)) {
                    TListadoDocumentos oldTListadoDocumentosOfTOrdenDocumentoListNewTOrdenDocumento = TOrdenDocumentoListNewTOrdenDocumento.getTListadoDocumentos();
                    TOrdenDocumentoListNewTOrdenDocumento.setTListadoDocumentos(TListadoDocumentos);
                    TOrdenDocumentoListNewTOrdenDocumento = em.merge(TOrdenDocumentoListNewTOrdenDocumento);
                    if (oldTListadoDocumentosOfTOrdenDocumentoListNewTOrdenDocumento != null && !oldTListadoDocumentosOfTOrdenDocumentoListNewTOrdenDocumento.equals(TListadoDocumentos)) {
                        oldTListadoDocumentosOfTOrdenDocumentoListNewTOrdenDocumento.getTOrdenDocumentoList().remove(TOrdenDocumentoListNewTOrdenDocumento);
                        oldTListadoDocumentosOfTOrdenDocumentoListNewTOrdenDocumento = em.merge(oldTListadoDocumentosOfTOrdenDocumentoListNewTOrdenDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TListadoDocumentos.getIdListado();
                if (findTListadoDocumentos(id) == null) {
                    throw new NonexistentEntityException("The tListadoDocumentos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TListadoDocumentos TListadoDocumentos;
            try {
                TListadoDocumentos = em.getReference(TListadoDocumentos.class, id);
                TListadoDocumentos.getIdListado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TListadoDocumentos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<TOrdenDocumento> TOrdenDocumentoListOrphanCheck = TListadoDocumentos.getTOrdenDocumentoList();
            for (TOrdenDocumento TOrdenDocumentoListOrphanCheckTOrdenDocumento : TOrdenDocumentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TListadoDocumentos (" + TListadoDocumentos + ") cannot be destroyed since the TOrdenDocumento " + TOrdenDocumentoListOrphanCheckTOrdenDocumento + " in its TOrdenDocumentoList field has a non-nullable TListadoDocumentos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(TListadoDocumentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TListadoDocumentos> findTListadoDocumentosEntities() {
        return findTListadoDocumentosEntities(true, -1, -1);
    }

    public List<TListadoDocumentos> findTListadoDocumentosEntities(int maxResults, int firstResult) {
        return findTListadoDocumentosEntities(false, maxResults, firstResult);
    }

    private List<TListadoDocumentos> findTListadoDocumentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TListadoDocumentos.class));
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

    public TListadoDocumentos findTListadoDocumentos(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TListadoDocumentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTListadoDocumentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TListadoDocumentos> rt = cq.from(TListadoDocumentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
