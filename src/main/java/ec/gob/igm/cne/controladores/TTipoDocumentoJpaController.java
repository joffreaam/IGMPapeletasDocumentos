/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.IllegalOrphanException;
import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.gob.igm.cne.entidades.TOrdenDocumento;
import ec.gob.igm.cne.entidades.TTipoDocumento;
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
public class TTipoDocumentoJpaController implements Serializable {

    public TTipoDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TTipoDocumento TTipoDocumento) throws PreexistingEntityException, Exception {
        if (TTipoDocumento.getTOrdenDocumentoList() == null) {
            TTipoDocumento.setTOrdenDocumentoList(new ArrayList<TOrdenDocumento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<TOrdenDocumento> attachedTOrdenDocumentoList = new ArrayList<TOrdenDocumento>();
            for (TOrdenDocumento TOrdenDocumentoListTOrdenDocumentoToAttach : TTipoDocumento.getTOrdenDocumentoList()) {
                TOrdenDocumentoListTOrdenDocumentoToAttach = em.getReference(TOrdenDocumentoListTOrdenDocumentoToAttach.getClass(), TOrdenDocumentoListTOrdenDocumentoToAttach.getTOrdenDocumentoPK());
                attachedTOrdenDocumentoList.add(TOrdenDocumentoListTOrdenDocumentoToAttach);
            }
            TTipoDocumento.setTOrdenDocumentoList(attachedTOrdenDocumentoList);
            em.persist(TTipoDocumento);
            for (TOrdenDocumento TOrdenDocumentoListTOrdenDocumento : TTipoDocumento.getTOrdenDocumentoList()) {
                TTipoDocumento oldTTipoDocumentoOfTOrdenDocumentoListTOrdenDocumento = TOrdenDocumentoListTOrdenDocumento.getTTipoDocumento();
                TOrdenDocumentoListTOrdenDocumento.setTTipoDocumento(TTipoDocumento);
                TOrdenDocumentoListTOrdenDocumento = em.merge(TOrdenDocumentoListTOrdenDocumento);
                if (oldTTipoDocumentoOfTOrdenDocumentoListTOrdenDocumento != null) {
                    oldTTipoDocumentoOfTOrdenDocumentoListTOrdenDocumento.getTOrdenDocumentoList().remove(TOrdenDocumentoListTOrdenDocumento);
                    oldTTipoDocumentoOfTOrdenDocumentoListTOrdenDocumento = em.merge(oldTTipoDocumentoOfTOrdenDocumentoListTOrdenDocumento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTTipoDocumento(TTipoDocumento.getIdKit()) != null) {
                throw new PreexistingEntityException("TTipoDocumento " + TTipoDocumento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TTipoDocumento TTipoDocumento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TTipoDocumento persistentTTipoDocumento = em.find(TTipoDocumento.class, TTipoDocumento.getIdKit());
            List<TOrdenDocumento> TOrdenDocumentoListOld = persistentTTipoDocumento.getTOrdenDocumentoList();
            List<TOrdenDocumento> TOrdenDocumentoListNew = TTipoDocumento.getTOrdenDocumentoList();
            List<String> illegalOrphanMessages = null;
            for (TOrdenDocumento TOrdenDocumentoListOldTOrdenDocumento : TOrdenDocumentoListOld) {
                if (!TOrdenDocumentoListNew.contains(TOrdenDocumentoListOldTOrdenDocumento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TOrdenDocumento " + TOrdenDocumentoListOldTOrdenDocumento + " since its TTipoDocumento field is not nullable.");
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
            TTipoDocumento.setTOrdenDocumentoList(TOrdenDocumentoListNew);
            TTipoDocumento = em.merge(TTipoDocumento);
            for (TOrdenDocumento TOrdenDocumentoListNewTOrdenDocumento : TOrdenDocumentoListNew) {
                if (!TOrdenDocumentoListOld.contains(TOrdenDocumentoListNewTOrdenDocumento)) {
                    TTipoDocumento oldTTipoDocumentoOfTOrdenDocumentoListNewTOrdenDocumento = TOrdenDocumentoListNewTOrdenDocumento.getTTipoDocumento();
                    TOrdenDocumentoListNewTOrdenDocumento.setTTipoDocumento(TTipoDocumento);
                    TOrdenDocumentoListNewTOrdenDocumento = em.merge(TOrdenDocumentoListNewTOrdenDocumento);
                    if (oldTTipoDocumentoOfTOrdenDocumentoListNewTOrdenDocumento != null && !oldTTipoDocumentoOfTOrdenDocumentoListNewTOrdenDocumento.equals(TTipoDocumento)) {
                        oldTTipoDocumentoOfTOrdenDocumentoListNewTOrdenDocumento.getTOrdenDocumentoList().remove(TOrdenDocumentoListNewTOrdenDocumento);
                        oldTTipoDocumentoOfTOrdenDocumentoListNewTOrdenDocumento = em.merge(oldTTipoDocumentoOfTOrdenDocumentoListNewTOrdenDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TTipoDocumento.getIdKit();
                if (findTTipoDocumento(id) == null) {
                    throw new NonexistentEntityException("The tTipoDocumento with id " + id + " no longer exists.");
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
            TTipoDocumento TTipoDocumento;
            try {
                TTipoDocumento = em.getReference(TTipoDocumento.class, id);
                TTipoDocumento.getIdKit();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TTipoDocumento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<TOrdenDocumento> TOrdenDocumentoListOrphanCheck = TTipoDocumento.getTOrdenDocumentoList();
            for (TOrdenDocumento TOrdenDocumentoListOrphanCheckTOrdenDocumento : TOrdenDocumentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TTipoDocumento (" + TTipoDocumento + ") cannot be destroyed since the TOrdenDocumento " + TOrdenDocumentoListOrphanCheckTOrdenDocumento + " in its TOrdenDocumentoList field has a non-nullable TTipoDocumento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(TTipoDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TTipoDocumento> findTTipoDocumentoEntities() {
        return findTTipoDocumentoEntities(true, -1, -1);
    }

    public List<TTipoDocumento> findTTipoDocumentoEntities(int maxResults, int firstResult) {
        return findTTipoDocumentoEntities(false, maxResults, firstResult);
    }

    private List<TTipoDocumento> findTTipoDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TTipoDocumento.class));
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

    public TTipoDocumento findTTipoDocumento(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TTipoDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTTipoDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TTipoDocumento> rt = cq.from(TTipoDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
