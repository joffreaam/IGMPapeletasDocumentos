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
import ec.gob.igm.cne.entidades.TCanton;
import java.util.ArrayList;
import java.util.List;
import ec.gob.igm.cne.entidades.TProduccionDocumentos;
import ec.gob.igm.cne.entidades.TProvincia;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author desarrollo
 */
public class TProvinciaJpaController implements Serializable {

    public TProvinciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TProvincia TProvincia) throws PreexistingEntityException, Exception {
        if (TProvincia.getTCantonList() == null) {
            TProvincia.setTCantonList(new ArrayList<TCanton>());
        }
        if (TProvincia.getTProduccionDocumentosList() == null) {
            TProvincia.setTProduccionDocumentosList(new ArrayList<TProduccionDocumentos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<TCanton> attachedTCantonList = new ArrayList<TCanton>();
            for (TCanton TCantonListTCantonToAttach : TProvincia.getTCantonList()) {
                TCantonListTCantonToAttach = em.getReference(TCantonListTCantonToAttach.getClass(), TCantonListTCantonToAttach.getIdCanton());
                attachedTCantonList.add(TCantonListTCantonToAttach);
            }
            TProvincia.setTCantonList(attachedTCantonList);
            List<TProduccionDocumentos> attachedTProduccionDocumentosList = new ArrayList<TProduccionDocumentos>();
            for (TProduccionDocumentos TProduccionDocumentosListTProduccionDocumentosToAttach : TProvincia.getTProduccionDocumentosList()) {
                TProduccionDocumentosListTProduccionDocumentosToAttach = em.getReference(TProduccionDocumentosListTProduccionDocumentosToAttach.getClass(), TProduccionDocumentosListTProduccionDocumentosToAttach.getTProduccionDocumentosPK());
                attachedTProduccionDocumentosList.add(TProduccionDocumentosListTProduccionDocumentosToAttach);
            }
            TProvincia.setTProduccionDocumentosList(attachedTProduccionDocumentosList);
            em.persist(TProvincia);
            for (TCanton TCantonListTCanton : TProvincia.getTCantonList()) {
                TProvincia oldIdProvinciaOfTCantonListTCanton = TCantonListTCanton.getIdProvincia();
                TCantonListTCanton.setIdProvincia(TProvincia);
                TCantonListTCanton = em.merge(TCantonListTCanton);
                if (oldIdProvinciaOfTCantonListTCanton != null) {
                    oldIdProvinciaOfTCantonListTCanton.getTCantonList().remove(TCantonListTCanton);
                    oldIdProvinciaOfTCantonListTCanton = em.merge(oldIdProvinciaOfTCantonListTCanton);
                }
            }
            for (TProduccionDocumentos TProduccionDocumentosListTProduccionDocumentos : TProvincia.getTProduccionDocumentosList()) {
                TProvincia oldIdProvinciaOfTProduccionDocumentosListTProduccionDocumentos = TProduccionDocumentosListTProduccionDocumentos.getIdProvincia();
                TProduccionDocumentosListTProduccionDocumentos.setIdProvincia(TProvincia);
                TProduccionDocumentosListTProduccionDocumentos = em.merge(TProduccionDocumentosListTProduccionDocumentos);
                if (oldIdProvinciaOfTProduccionDocumentosListTProduccionDocumentos != null) {
                    oldIdProvinciaOfTProduccionDocumentosListTProduccionDocumentos.getTProduccionDocumentosList().remove(TProduccionDocumentosListTProduccionDocumentos);
                    oldIdProvinciaOfTProduccionDocumentosListTProduccionDocumentos = em.merge(oldIdProvinciaOfTProduccionDocumentosListTProduccionDocumentos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTProvincia(TProvincia.getIdProvincia()) != null) {
                throw new PreexistingEntityException("TProvincia " + TProvincia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TProvincia TProvincia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TProvincia persistentTProvincia = em.find(TProvincia.class, TProvincia.getIdProvincia());
            List<TCanton> TCantonListOld = persistentTProvincia.getTCantonList();
            List<TCanton> TCantonListNew = TProvincia.getTCantonList();
            List<TProduccionDocumentos> TProduccionDocumentosListOld = persistentTProvincia.getTProduccionDocumentosList();
            List<TProduccionDocumentos> TProduccionDocumentosListNew = TProvincia.getTProduccionDocumentosList();
            List<TCanton> attachedTCantonListNew = new ArrayList<TCanton>();
            for (TCanton TCantonListNewTCantonToAttach : TCantonListNew) {
                TCantonListNewTCantonToAttach = em.getReference(TCantonListNewTCantonToAttach.getClass(), TCantonListNewTCantonToAttach.getIdCanton());
                attachedTCantonListNew.add(TCantonListNewTCantonToAttach);
            }
            TCantonListNew = attachedTCantonListNew;
            TProvincia.setTCantonList(TCantonListNew);
            List<TProduccionDocumentos> attachedTProduccionDocumentosListNew = new ArrayList<TProduccionDocumentos>();
            for (TProduccionDocumentos TProduccionDocumentosListNewTProduccionDocumentosToAttach : TProduccionDocumentosListNew) {
                TProduccionDocumentosListNewTProduccionDocumentosToAttach = em.getReference(TProduccionDocumentosListNewTProduccionDocumentosToAttach.getClass(), TProduccionDocumentosListNewTProduccionDocumentosToAttach.getTProduccionDocumentosPK());
                attachedTProduccionDocumentosListNew.add(TProduccionDocumentosListNewTProduccionDocumentosToAttach);
            }
            TProduccionDocumentosListNew = attachedTProduccionDocumentosListNew;
            TProvincia.setTProduccionDocumentosList(TProduccionDocumentosListNew);
            TProvincia = em.merge(TProvincia);
            for (TCanton TCantonListOldTCanton : TCantonListOld) {
                if (!TCantonListNew.contains(TCantonListOldTCanton)) {
                    TCantonListOldTCanton.setIdProvincia(null);
                    TCantonListOldTCanton = em.merge(TCantonListOldTCanton);
                }
            }
            for (TCanton TCantonListNewTCanton : TCantonListNew) {
                if (!TCantonListOld.contains(TCantonListNewTCanton)) {
                    TProvincia oldIdProvinciaOfTCantonListNewTCanton = TCantonListNewTCanton.getIdProvincia();
                    TCantonListNewTCanton.setIdProvincia(TProvincia);
                    TCantonListNewTCanton = em.merge(TCantonListNewTCanton);
                    if (oldIdProvinciaOfTCantonListNewTCanton != null && !oldIdProvinciaOfTCantonListNewTCanton.equals(TProvincia)) {
                        oldIdProvinciaOfTCantonListNewTCanton.getTCantonList().remove(TCantonListNewTCanton);
                        oldIdProvinciaOfTCantonListNewTCanton = em.merge(oldIdProvinciaOfTCantonListNewTCanton);
                    }
                }
            }
            for (TProduccionDocumentos TProduccionDocumentosListOldTProduccionDocumentos : TProduccionDocumentosListOld) {
                if (!TProduccionDocumentosListNew.contains(TProduccionDocumentosListOldTProduccionDocumentos)) {
                    TProduccionDocumentosListOldTProduccionDocumentos.setIdProvincia(null);
                    TProduccionDocumentosListOldTProduccionDocumentos = em.merge(TProduccionDocumentosListOldTProduccionDocumentos);
                }
            }
            for (TProduccionDocumentos TProduccionDocumentosListNewTProduccionDocumentos : TProduccionDocumentosListNew) {
                if (!TProduccionDocumentosListOld.contains(TProduccionDocumentosListNewTProduccionDocumentos)) {
                    TProvincia oldIdProvinciaOfTProduccionDocumentosListNewTProduccionDocumentos = TProduccionDocumentosListNewTProduccionDocumentos.getIdProvincia();
                    TProduccionDocumentosListNewTProduccionDocumentos.setIdProvincia(TProvincia);
                    TProduccionDocumentosListNewTProduccionDocumentos = em.merge(TProduccionDocumentosListNewTProduccionDocumentos);
                    if (oldIdProvinciaOfTProduccionDocumentosListNewTProduccionDocumentos != null && !oldIdProvinciaOfTProduccionDocumentosListNewTProduccionDocumentos.equals(TProvincia)) {
                        oldIdProvinciaOfTProduccionDocumentosListNewTProduccionDocumentos.getTProduccionDocumentosList().remove(TProduccionDocumentosListNewTProduccionDocumentos);
                        oldIdProvinciaOfTProduccionDocumentosListNewTProduccionDocumentos = em.merge(oldIdProvinciaOfTProduccionDocumentosListNewTProduccionDocumentos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = TProvincia.getIdProvincia();
                if (findTProvincia(id) == null) {
                    throw new NonexistentEntityException("The tProvincia with id " + id + " no longer exists.");
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
            TProvincia TProvincia;
            try {
                TProvincia = em.getReference(TProvincia.class, id);
                TProvincia.getIdProvincia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TProvincia with id " + id + " no longer exists.", enfe);
            }
            List<TCanton> TCantonList = TProvincia.getTCantonList();
            for (TCanton TCantonListTCanton : TCantonList) {
                TCantonListTCanton.setIdProvincia(null);
                TCantonListTCanton = em.merge(TCantonListTCanton);
            }
            List<TProduccionDocumentos> TProduccionDocumentosList = TProvincia.getTProduccionDocumentosList();
            for (TProduccionDocumentos TProduccionDocumentosListTProduccionDocumentos : TProduccionDocumentosList) {
                TProduccionDocumentosListTProduccionDocumentos.setIdProvincia(null);
                TProduccionDocumentosListTProduccionDocumentos = em.merge(TProduccionDocumentosListTProduccionDocumentos);
            }
            em.remove(TProvincia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TProvincia> findTProvinciaEntities() {
        return findTProvinciaEntities(true, -1, -1);
    }

    public List<TProvincia> findTProvinciaEntities(int maxResults, int firstResult) {
        return findTProvinciaEntities(false, maxResults, firstResult);
    }

    private List<TProvincia> findTProvinciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TProvincia.class));
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

    public TProvincia findTProvincia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TProvincia.class, id);
        } finally {
            em.close();
        }
    }

    public int getTProvinciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TProvincia> rt = cq.from(TProvincia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
