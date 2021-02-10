/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import ec.gob.igm.cne.entidades.TDocumentosPreimpresos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.gob.igm.cne.entidades.TProduccionPreimpresos;
import java.util.ArrayList;
import java.util.List;
import ec.gob.igm.cne.entidades.TProduccionDisenioPreim;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desarrollo
 */
public class TDocumentosPreimpresosJpaController implements Serializable {

    public TDocumentosPreimpresosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TDocumentosPreimpresos TDocumentosPreimpresos) throws PreexistingEntityException, Exception {
        if (TDocumentosPreimpresos.getTProduccionPreimpresosList() == null) {
            TDocumentosPreimpresos.setTProduccionPreimpresosList(new ArrayList<TProduccionPreimpresos>());
        }
        if (TDocumentosPreimpresos.getTProduccionDisenioPreimList() == null) {
            TDocumentosPreimpresos.setTProduccionDisenioPreimList(new ArrayList<TProduccionDisenioPreim>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<TProduccionPreimpresos> attachedTProduccionPreimpresosList = new ArrayList<TProduccionPreimpresos>();
            for (TProduccionPreimpresos TProduccionPreimpresosListTProduccionPreimpresosToAttach : TDocumentosPreimpresos.getTProduccionPreimpresosList()) {
                TProduccionPreimpresosListTProduccionPreimpresosToAttach = em.getReference(TProduccionPreimpresosListTProduccionPreimpresosToAttach.getClass(), TProduccionPreimpresosListTProduccionPreimpresosToAttach.getId());
                attachedTProduccionPreimpresosList.add(TProduccionPreimpresosListTProduccionPreimpresosToAttach);
            }
            TDocumentosPreimpresos.setTProduccionPreimpresosList(attachedTProduccionPreimpresosList);
            List<TProduccionDisenioPreim> attachedTProduccionDisenioPreimList = new ArrayList<TProduccionDisenioPreim>();
            for (TProduccionDisenioPreim TProduccionDisenioPreimListTProduccionDisenioPreimToAttach : TDocumentosPreimpresos.getTProduccionDisenioPreimList()) {
                TProduccionDisenioPreimListTProduccionDisenioPreimToAttach = em.getReference(TProduccionDisenioPreimListTProduccionDisenioPreimToAttach.getClass(), TProduccionDisenioPreimListTProduccionDisenioPreimToAttach.getId());
                attachedTProduccionDisenioPreimList.add(TProduccionDisenioPreimListTProduccionDisenioPreimToAttach);
            }
            TDocumentosPreimpresos.setTProduccionDisenioPreimList(attachedTProduccionDisenioPreimList);
            em.persist(TDocumentosPreimpresos);
            for (TProduccionPreimpresos TProduccionPreimpresosListTProduccionPreimpresos : TDocumentosPreimpresos.getTProduccionPreimpresosList()) {
                TDocumentosPreimpresos oldIdTipoDocumentoOfTProduccionPreimpresosListTProduccionPreimpresos = TProduccionPreimpresosListTProduccionPreimpresos.getIdTipoDocumento();
                TProduccionPreimpresosListTProduccionPreimpresos.setIdTipoDocumento(TDocumentosPreimpresos);
                TProduccionPreimpresosListTProduccionPreimpresos = em.merge(TProduccionPreimpresosListTProduccionPreimpresos);
                if (oldIdTipoDocumentoOfTProduccionPreimpresosListTProduccionPreimpresos != null) {
                    oldIdTipoDocumentoOfTProduccionPreimpresosListTProduccionPreimpresos.getTProduccionPreimpresosList().remove(TProduccionPreimpresosListTProduccionPreimpresos);
                    oldIdTipoDocumentoOfTProduccionPreimpresosListTProduccionPreimpresos = em.merge(oldIdTipoDocumentoOfTProduccionPreimpresosListTProduccionPreimpresos);
                }
            }
            for (TProduccionDisenioPreim TProduccionDisenioPreimListTProduccionDisenioPreim : TDocumentosPreimpresos.getTProduccionDisenioPreimList()) {
                TDocumentosPreimpresos oldIdTipoDocumentoOfTProduccionDisenioPreimListTProduccionDisenioPreim = TProduccionDisenioPreimListTProduccionDisenioPreim.getIdTipoDocumento();
                TProduccionDisenioPreimListTProduccionDisenioPreim.setIdTipoDocumento(TDocumentosPreimpresos);
                TProduccionDisenioPreimListTProduccionDisenioPreim = em.merge(TProduccionDisenioPreimListTProduccionDisenioPreim);
                if (oldIdTipoDocumentoOfTProduccionDisenioPreimListTProduccionDisenioPreim != null) {
                    oldIdTipoDocumentoOfTProduccionDisenioPreimListTProduccionDisenioPreim.getTProduccionDisenioPreimList().remove(TProduccionDisenioPreimListTProduccionDisenioPreim);
                    oldIdTipoDocumentoOfTProduccionDisenioPreimListTProduccionDisenioPreim = em.merge(oldIdTipoDocumentoOfTProduccionDisenioPreimListTProduccionDisenioPreim);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTDocumentosPreimpresos(TDocumentosPreimpresos.getId()) != null) {
                throw new PreexistingEntityException("TDocumentosPreimpresos " + TDocumentosPreimpresos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TDocumentosPreimpresos TDocumentosPreimpresos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDocumentosPreimpresos persistentTDocumentosPreimpresos = em.find(TDocumentosPreimpresos.class, TDocumentosPreimpresos.getId());
            List<TProduccionPreimpresos> TProduccionPreimpresosListOld = persistentTDocumentosPreimpresos.getTProduccionPreimpresosList();
            List<TProduccionPreimpresos> TProduccionPreimpresosListNew = TDocumentosPreimpresos.getTProduccionPreimpresosList();
            List<TProduccionDisenioPreim> TProduccionDisenioPreimListOld = persistentTDocumentosPreimpresos.getTProduccionDisenioPreimList();
            List<TProduccionDisenioPreim> TProduccionDisenioPreimListNew = TDocumentosPreimpresos.getTProduccionDisenioPreimList();
            List<TProduccionPreimpresos> attachedTProduccionPreimpresosListNew = new ArrayList<TProduccionPreimpresos>();
            for (TProduccionPreimpresos TProduccionPreimpresosListNewTProduccionPreimpresosToAttach : TProduccionPreimpresosListNew) {
                TProduccionPreimpresosListNewTProduccionPreimpresosToAttach = em.getReference(TProduccionPreimpresosListNewTProduccionPreimpresosToAttach.getClass(), TProduccionPreimpresosListNewTProduccionPreimpresosToAttach.getId());
                attachedTProduccionPreimpresosListNew.add(TProduccionPreimpresosListNewTProduccionPreimpresosToAttach);
            }
            TProduccionPreimpresosListNew = attachedTProduccionPreimpresosListNew;
            TDocumentosPreimpresos.setTProduccionPreimpresosList(TProduccionPreimpresosListNew);
            List<TProduccionDisenioPreim> attachedTProduccionDisenioPreimListNew = new ArrayList<TProduccionDisenioPreim>();
            for (TProduccionDisenioPreim TProduccionDisenioPreimListNewTProduccionDisenioPreimToAttach : TProduccionDisenioPreimListNew) {
                TProduccionDisenioPreimListNewTProduccionDisenioPreimToAttach = em.getReference(TProduccionDisenioPreimListNewTProduccionDisenioPreimToAttach.getClass(), TProduccionDisenioPreimListNewTProduccionDisenioPreimToAttach.getId());
                attachedTProduccionDisenioPreimListNew.add(TProduccionDisenioPreimListNewTProduccionDisenioPreimToAttach);
            }
            TProduccionDisenioPreimListNew = attachedTProduccionDisenioPreimListNew;
            TDocumentosPreimpresos.setTProduccionDisenioPreimList(TProduccionDisenioPreimListNew);
            TDocumentosPreimpresos = em.merge(TDocumentosPreimpresos);
            for (TProduccionPreimpresos TProduccionPreimpresosListOldTProduccionPreimpresos : TProduccionPreimpresosListOld) {
                if (!TProduccionPreimpresosListNew.contains(TProduccionPreimpresosListOldTProduccionPreimpresos)) {
                    TProduccionPreimpresosListOldTProduccionPreimpresos.setIdTipoDocumento(null);
                    TProduccionPreimpresosListOldTProduccionPreimpresos = em.merge(TProduccionPreimpresosListOldTProduccionPreimpresos);
                }
            }
            for (TProduccionPreimpresos TProduccionPreimpresosListNewTProduccionPreimpresos : TProduccionPreimpresosListNew) {
                if (!TProduccionPreimpresosListOld.contains(TProduccionPreimpresosListNewTProduccionPreimpresos)) {
                    TDocumentosPreimpresos oldIdTipoDocumentoOfTProduccionPreimpresosListNewTProduccionPreimpresos = TProduccionPreimpresosListNewTProduccionPreimpresos.getIdTipoDocumento();
                    TProduccionPreimpresosListNewTProduccionPreimpresos.setIdTipoDocumento(TDocumentosPreimpresos);
                    TProduccionPreimpresosListNewTProduccionPreimpresos = em.merge(TProduccionPreimpresosListNewTProduccionPreimpresos);
                    if (oldIdTipoDocumentoOfTProduccionPreimpresosListNewTProduccionPreimpresos != null && !oldIdTipoDocumentoOfTProduccionPreimpresosListNewTProduccionPreimpresos.equals(TDocumentosPreimpresos)) {
                        oldIdTipoDocumentoOfTProduccionPreimpresosListNewTProduccionPreimpresos.getTProduccionPreimpresosList().remove(TProduccionPreimpresosListNewTProduccionPreimpresos);
                        oldIdTipoDocumentoOfTProduccionPreimpresosListNewTProduccionPreimpresos = em.merge(oldIdTipoDocumentoOfTProduccionPreimpresosListNewTProduccionPreimpresos);
                    }
                }
            }
            for (TProduccionDisenioPreim TProduccionDisenioPreimListOldTProduccionDisenioPreim : TProduccionDisenioPreimListOld) {
                if (!TProduccionDisenioPreimListNew.contains(TProduccionDisenioPreimListOldTProduccionDisenioPreim)) {
                    TProduccionDisenioPreimListOldTProduccionDisenioPreim.setIdTipoDocumento(null);
                    TProduccionDisenioPreimListOldTProduccionDisenioPreim = em.merge(TProduccionDisenioPreimListOldTProduccionDisenioPreim);
                }
            }
            for (TProduccionDisenioPreim TProduccionDisenioPreimListNewTProduccionDisenioPreim : TProduccionDisenioPreimListNew) {
                if (!TProduccionDisenioPreimListOld.contains(TProduccionDisenioPreimListNewTProduccionDisenioPreim)) {
                    TDocumentosPreimpresos oldIdTipoDocumentoOfTProduccionDisenioPreimListNewTProduccionDisenioPreim = TProduccionDisenioPreimListNewTProduccionDisenioPreim.getIdTipoDocumento();
                    TProduccionDisenioPreimListNewTProduccionDisenioPreim.setIdTipoDocumento(TDocumentosPreimpresos);
                    TProduccionDisenioPreimListNewTProduccionDisenioPreim = em.merge(TProduccionDisenioPreimListNewTProduccionDisenioPreim);
                    if (oldIdTipoDocumentoOfTProduccionDisenioPreimListNewTProduccionDisenioPreim != null && !oldIdTipoDocumentoOfTProduccionDisenioPreimListNewTProduccionDisenioPreim.equals(TDocumentosPreimpresos)) {
                        oldIdTipoDocumentoOfTProduccionDisenioPreimListNewTProduccionDisenioPreim.getTProduccionDisenioPreimList().remove(TProduccionDisenioPreimListNewTProduccionDisenioPreim);
                        oldIdTipoDocumentoOfTProduccionDisenioPreimListNewTProduccionDisenioPreim = em.merge(oldIdTipoDocumentoOfTProduccionDisenioPreimListNewTProduccionDisenioPreim);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = TDocumentosPreimpresos.getId();
                if (findTDocumentosPreimpresos(id) == null) {
                    throw new NonexistentEntityException("The tDocumentosPreimpresos with id " + id + " no longer exists.");
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
            TDocumentosPreimpresos TDocumentosPreimpresos;
            try {
                TDocumentosPreimpresos = em.getReference(TDocumentosPreimpresos.class, id);
                TDocumentosPreimpresos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TDocumentosPreimpresos with id " + id + " no longer exists.", enfe);
            }
            List<TProduccionPreimpresos> TProduccionPreimpresosList = TDocumentosPreimpresos.getTProduccionPreimpresosList();
            for (TProduccionPreimpresos TProduccionPreimpresosListTProduccionPreimpresos : TProduccionPreimpresosList) {
                TProduccionPreimpresosListTProduccionPreimpresos.setIdTipoDocumento(null);
                TProduccionPreimpresosListTProduccionPreimpresos = em.merge(TProduccionPreimpresosListTProduccionPreimpresos);
            }
            List<TProduccionDisenioPreim> TProduccionDisenioPreimList = TDocumentosPreimpresos.getTProduccionDisenioPreimList();
            for (TProduccionDisenioPreim TProduccionDisenioPreimListTProduccionDisenioPreim : TProduccionDisenioPreimList) {
                TProduccionDisenioPreimListTProduccionDisenioPreim.setIdTipoDocumento(null);
                TProduccionDisenioPreimListTProduccionDisenioPreim = em.merge(TProduccionDisenioPreimListTProduccionDisenioPreim);
            }
            em.remove(TDocumentosPreimpresos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TDocumentosPreimpresos> findTDocumentosPreimpresosEntities() {
        return findTDocumentosPreimpresosEntities(true, -1, -1);
    }

    public List<TDocumentosPreimpresos> findTDocumentosPreimpresosEntities(int maxResults, int firstResult) {
        return findTDocumentosPreimpresosEntities(false, maxResults, firstResult);
    }

    private List<TDocumentosPreimpresos> findTDocumentosPreimpresosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TDocumentosPreimpresos.class));
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

    public TDocumentosPreimpresos findTDocumentosPreimpresos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TDocumentosPreimpresos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTDocumentosPreimpresosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TDocumentosPreimpresos> rt = cq.from(TDocumentosPreimpresos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
