/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import ec.gob.igm.cne.entidades.TPrensas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.gob.igm.cne.entidades.TProduccionDisenio;
import java.util.ArrayList;
import java.util.List;
import ec.gob.igm.cne.entidades.TProduccionPapeletas;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desarrollo
 */
public class TPrensasJpaController implements Serializable {

    public TPrensasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TPrensas TPrensas) throws PreexistingEntityException, Exception {
        if (TPrensas.getTProduccionDisenioList() == null) {
            TPrensas.setTProduccionDisenioList(new ArrayList<TProduccionDisenio>());
        }
        if (TPrensas.getTProduccionPapeletasList() == null) {
            TPrensas.setTProduccionPapeletasList(new ArrayList<TProduccionPapeletas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<TProduccionDisenio> attachedTProduccionDisenioList = new ArrayList<TProduccionDisenio>();
            for (TProduccionDisenio TProduccionDisenioListTProduccionDisenioToAttach : TPrensas.getTProduccionDisenioList()) {
                TProduccionDisenioListTProduccionDisenioToAttach = em.getReference(TProduccionDisenioListTProduccionDisenioToAttach.getClass(), TProduccionDisenioListTProduccionDisenioToAttach.getIdRegistro());
                attachedTProduccionDisenioList.add(TProduccionDisenioListTProduccionDisenioToAttach);
            }
            TPrensas.setTProduccionDisenioList(attachedTProduccionDisenioList);
            List<TProduccionPapeletas> attachedTProduccionPapeletasList = new ArrayList<TProduccionPapeletas>();
            for (TProduccionPapeletas TProduccionPapeletasListTProduccionPapeletasToAttach : TPrensas.getTProduccionPapeletasList()) {
                TProduccionPapeletasListTProduccionPapeletasToAttach = em.getReference(TProduccionPapeletasListTProduccionPapeletasToAttach.getClass(), TProduccionPapeletasListTProduccionPapeletasToAttach.getIdRegistro());
                attachedTProduccionPapeletasList.add(TProduccionPapeletasListTProduccionPapeletasToAttach);
            }
            TPrensas.setTProduccionPapeletasList(attachedTProduccionPapeletasList);
            em.persist(TPrensas);
            for (TProduccionDisenio TProduccionDisenioListTProduccionDisenio : TPrensas.getTProduccionDisenioList()) {
                TPrensas oldIdPrensaOfTProduccionDisenioListTProduccionDisenio = TProduccionDisenioListTProduccionDisenio.getIdPrensa();
                TProduccionDisenioListTProduccionDisenio.setIdPrensa(TPrensas);
                TProduccionDisenioListTProduccionDisenio = em.merge(TProduccionDisenioListTProduccionDisenio);
                if (oldIdPrensaOfTProduccionDisenioListTProduccionDisenio != null) {
                    oldIdPrensaOfTProduccionDisenioListTProduccionDisenio.getTProduccionDisenioList().remove(TProduccionDisenioListTProduccionDisenio);
                    oldIdPrensaOfTProduccionDisenioListTProduccionDisenio = em.merge(oldIdPrensaOfTProduccionDisenioListTProduccionDisenio);
                }
            }
            for (TProduccionPapeletas TProduccionPapeletasListTProduccionPapeletas : TPrensas.getTProduccionPapeletasList()) {
                TPrensas oldIdPrensaOfTProduccionPapeletasListTProduccionPapeletas = TProduccionPapeletasListTProduccionPapeletas.getIdPrensa();
                TProduccionPapeletasListTProduccionPapeletas.setIdPrensa(TPrensas);
                TProduccionPapeletasListTProduccionPapeletas = em.merge(TProduccionPapeletasListTProduccionPapeletas);
                if (oldIdPrensaOfTProduccionPapeletasListTProduccionPapeletas != null) {
                    oldIdPrensaOfTProduccionPapeletasListTProduccionPapeletas.getTProduccionPapeletasList().remove(TProduccionPapeletasListTProduccionPapeletas);
                    oldIdPrensaOfTProduccionPapeletasListTProduccionPapeletas = em.merge(oldIdPrensaOfTProduccionPapeletasListTProduccionPapeletas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTPrensas(TPrensas.getIdPrensa()) != null) {
                throw new PreexistingEntityException("TPrensas " + TPrensas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TPrensas TPrensas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TPrensas persistentTPrensas = em.find(TPrensas.class, TPrensas.getIdPrensa());
            List<TProduccionDisenio> TProduccionDisenioListOld = persistentTPrensas.getTProduccionDisenioList();
            List<TProduccionDisenio> TProduccionDisenioListNew = TPrensas.getTProduccionDisenioList();
            List<TProduccionPapeletas> TProduccionPapeletasListOld = persistentTPrensas.getTProduccionPapeletasList();
            List<TProduccionPapeletas> TProduccionPapeletasListNew = TPrensas.getTProduccionPapeletasList();
            List<TProduccionDisenio> attachedTProduccionDisenioListNew = new ArrayList<TProduccionDisenio>();
            for (TProduccionDisenio TProduccionDisenioListNewTProduccionDisenioToAttach : TProduccionDisenioListNew) {
                TProduccionDisenioListNewTProduccionDisenioToAttach = em.getReference(TProduccionDisenioListNewTProduccionDisenioToAttach.getClass(), TProduccionDisenioListNewTProduccionDisenioToAttach.getIdRegistro());
                attachedTProduccionDisenioListNew.add(TProduccionDisenioListNewTProduccionDisenioToAttach);
            }
            TProduccionDisenioListNew = attachedTProduccionDisenioListNew;
            TPrensas.setTProduccionDisenioList(TProduccionDisenioListNew);
            List<TProduccionPapeletas> attachedTProduccionPapeletasListNew = new ArrayList<TProduccionPapeletas>();
            for (TProduccionPapeletas TProduccionPapeletasListNewTProduccionPapeletasToAttach : TProduccionPapeletasListNew) {
                TProduccionPapeletasListNewTProduccionPapeletasToAttach = em.getReference(TProduccionPapeletasListNewTProduccionPapeletasToAttach.getClass(), TProduccionPapeletasListNewTProduccionPapeletasToAttach.getIdRegistro());
                attachedTProduccionPapeletasListNew.add(TProduccionPapeletasListNewTProduccionPapeletasToAttach);
            }
            TProduccionPapeletasListNew = attachedTProduccionPapeletasListNew;
            TPrensas.setTProduccionPapeletasList(TProduccionPapeletasListNew);
            TPrensas = em.merge(TPrensas);
            for (TProduccionDisenio TProduccionDisenioListOldTProduccionDisenio : TProduccionDisenioListOld) {
                if (!TProduccionDisenioListNew.contains(TProduccionDisenioListOldTProduccionDisenio)) {
                    TProduccionDisenioListOldTProduccionDisenio.setIdPrensa(null);
                    TProduccionDisenioListOldTProduccionDisenio = em.merge(TProduccionDisenioListOldTProduccionDisenio);
                }
            }
            for (TProduccionDisenio TProduccionDisenioListNewTProduccionDisenio : TProduccionDisenioListNew) {
                if (!TProduccionDisenioListOld.contains(TProduccionDisenioListNewTProduccionDisenio)) {
                    TPrensas oldIdPrensaOfTProduccionDisenioListNewTProduccionDisenio = TProduccionDisenioListNewTProduccionDisenio.getIdPrensa();
                    TProduccionDisenioListNewTProduccionDisenio.setIdPrensa(TPrensas);
                    TProduccionDisenioListNewTProduccionDisenio = em.merge(TProduccionDisenioListNewTProduccionDisenio);
                    if (oldIdPrensaOfTProduccionDisenioListNewTProduccionDisenio != null && !oldIdPrensaOfTProduccionDisenioListNewTProduccionDisenio.equals(TPrensas)) {
                        oldIdPrensaOfTProduccionDisenioListNewTProduccionDisenio.getTProduccionDisenioList().remove(TProduccionDisenioListNewTProduccionDisenio);
                        oldIdPrensaOfTProduccionDisenioListNewTProduccionDisenio = em.merge(oldIdPrensaOfTProduccionDisenioListNewTProduccionDisenio);
                    }
                }
            }
            for (TProduccionPapeletas TProduccionPapeletasListOldTProduccionPapeletas : TProduccionPapeletasListOld) {
                if (!TProduccionPapeletasListNew.contains(TProduccionPapeletasListOldTProduccionPapeletas)) {
                    TProduccionPapeletasListOldTProduccionPapeletas.setIdPrensa(null);
                    TProduccionPapeletasListOldTProduccionPapeletas = em.merge(TProduccionPapeletasListOldTProduccionPapeletas);
                }
            }
            for (TProduccionPapeletas TProduccionPapeletasListNewTProduccionPapeletas : TProduccionPapeletasListNew) {
                if (!TProduccionPapeletasListOld.contains(TProduccionPapeletasListNewTProduccionPapeletas)) {
                    TPrensas oldIdPrensaOfTProduccionPapeletasListNewTProduccionPapeletas = TProduccionPapeletasListNewTProduccionPapeletas.getIdPrensa();
                    TProduccionPapeletasListNewTProduccionPapeletas.setIdPrensa(TPrensas);
                    TProduccionPapeletasListNewTProduccionPapeletas = em.merge(TProduccionPapeletasListNewTProduccionPapeletas);
                    if (oldIdPrensaOfTProduccionPapeletasListNewTProduccionPapeletas != null && !oldIdPrensaOfTProduccionPapeletasListNewTProduccionPapeletas.equals(TPrensas)) {
                        oldIdPrensaOfTProduccionPapeletasListNewTProduccionPapeletas.getTProduccionPapeletasList().remove(TProduccionPapeletasListNewTProduccionPapeletas);
                        oldIdPrensaOfTProduccionPapeletasListNewTProduccionPapeletas = em.merge(oldIdPrensaOfTProduccionPapeletasListNewTProduccionPapeletas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = TPrensas.getIdPrensa();
                if (findTPrensas(id) == null) {
                    throw new NonexistentEntityException("The tPrensas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TPrensas TPrensas;
            try {
                TPrensas = em.getReference(TPrensas.class, id);
                TPrensas.getIdPrensa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TPrensas with id " + id + " no longer exists.", enfe);
            }
            List<TProduccionDisenio> TProduccionDisenioList = TPrensas.getTProduccionDisenioList();
            for (TProduccionDisenio TProduccionDisenioListTProduccionDisenio : TProduccionDisenioList) {
                TProduccionDisenioListTProduccionDisenio.setIdPrensa(null);
                TProduccionDisenioListTProduccionDisenio = em.merge(TProduccionDisenioListTProduccionDisenio);
            }
            List<TProduccionPapeletas> TProduccionPapeletasList = TPrensas.getTProduccionPapeletasList();
            for (TProduccionPapeletas TProduccionPapeletasListTProduccionPapeletas : TProduccionPapeletasList) {
                TProduccionPapeletasListTProduccionPapeletas.setIdPrensa(null);
                TProduccionPapeletasListTProduccionPapeletas = em.merge(TProduccionPapeletasListTProduccionPapeletas);
            }
            em.remove(TPrensas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TPrensas> findTPrensasEntities() {
        return findTPrensasEntities(true, -1, -1);
    }

    public List<TPrensas> findTPrensasEntities(int maxResults, int firstResult) {
        return findTPrensasEntities(false, maxResults, firstResult);
    }

    private List<TPrensas> findTPrensasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TPrensas.class));
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

    public TPrensas findTPrensas(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TPrensas.class, id);
        } finally {
            em.close();
        }
    }

    public int getTPrensasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TPrensas> rt = cq.from(TPrensas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
