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
import ec.gob.igm.cne.entidades.TCanton;
import ec.gob.igm.cne.entidades.TParroquia;
import ec.gob.igm.cne.entidades.TZona;
import java.util.ArrayList;
import java.util.List;
import ec.gob.igm.cne.entidades.TProduccionDisenio;
import ec.gob.igm.cne.entidades.TProduccionPapeletas;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desarrollo
 */
public class TParroquiaJpaController implements Serializable {

    public TParroquiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TParroquia TParroquia) throws PreexistingEntityException, Exception {
        if (TParroquia.getTZonaList() == null) {
            TParroquia.setTZonaList(new ArrayList<TZona>());
        }
        if (TParroquia.getTProduccionDisenioList() == null) {
            TParroquia.setTProduccionDisenioList(new ArrayList<TProduccionDisenio>());
        }
        if (TParroquia.getTProduccionPapeletasList() == null) {
            TParroquia.setTProduccionPapeletasList(new ArrayList<TProduccionPapeletas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TCanton idCanton = TParroquia.getIdCanton();
            if (idCanton != null) {
                idCanton = em.getReference(idCanton.getClass(), idCanton.getIdCanton());
                TParroquia.setIdCanton(idCanton);
            }
            List<TZona> attachedTZonaList = new ArrayList<TZona>();
            for (TZona TZonaListTZonaToAttach : TParroquia.getTZonaList()) {
                TZonaListTZonaToAttach = em.getReference(TZonaListTZonaToAttach.getClass(), TZonaListTZonaToAttach.getTZonaPK());
                attachedTZonaList.add(TZonaListTZonaToAttach);
            }
            TParroquia.setTZonaList(attachedTZonaList);
            List<TProduccionDisenio> attachedTProduccionDisenioList = new ArrayList<TProduccionDisenio>();
            for (TProduccionDisenio TProduccionDisenioListTProduccionDisenioToAttach : TParroquia.getTProduccionDisenioList()) {
                TProduccionDisenioListTProduccionDisenioToAttach = em.getReference(TProduccionDisenioListTProduccionDisenioToAttach.getClass(), TProduccionDisenioListTProduccionDisenioToAttach.getIdRegistro());
                attachedTProduccionDisenioList.add(TProduccionDisenioListTProduccionDisenioToAttach);
            }
            TParroquia.setTProduccionDisenioList(attachedTProduccionDisenioList);
            List<TProduccionPapeletas> attachedTProduccionPapeletasList = new ArrayList<TProduccionPapeletas>();
            for (TProduccionPapeletas TProduccionPapeletasListTProduccionPapeletasToAttach : TParroquia.getTProduccionPapeletasList()) {
                TProduccionPapeletasListTProduccionPapeletasToAttach = em.getReference(TProduccionPapeletasListTProduccionPapeletasToAttach.getClass(), TProduccionPapeletasListTProduccionPapeletasToAttach.getIdRegistro());
                attachedTProduccionPapeletasList.add(TProduccionPapeletasListTProduccionPapeletasToAttach);
            }
            TParroquia.setTProduccionPapeletasList(attachedTProduccionPapeletasList);
            em.persist(TParroquia);
            if (idCanton != null) {
                idCanton.getTParroquiaList().add(TParroquia);
                idCanton = em.merge(idCanton);
            }
            for (TZona TZonaListTZona : TParroquia.getTZonaList()) {
                TParroquia oldTParroquiaOfTZonaListTZona = TZonaListTZona.getTParroquia();
                TZonaListTZona.setTParroquia(TParroquia);
                TZonaListTZona = em.merge(TZonaListTZona);
                if (oldTParroquiaOfTZonaListTZona != null) {
                    oldTParroquiaOfTZonaListTZona.getTZonaList().remove(TZonaListTZona);
                    oldTParroquiaOfTZonaListTZona = em.merge(oldTParroquiaOfTZonaListTZona);
                }
            }
            for (TProduccionDisenio TProduccionDisenioListTProduccionDisenio : TParroquia.getTProduccionDisenioList()) {
                TParroquia oldIdParroquiaOfTProduccionDisenioListTProduccionDisenio = TProduccionDisenioListTProduccionDisenio.getIdParroquia();
                TProduccionDisenioListTProduccionDisenio.setIdParroquia(TParroquia);
                TProduccionDisenioListTProduccionDisenio = em.merge(TProduccionDisenioListTProduccionDisenio);
                if (oldIdParroquiaOfTProduccionDisenioListTProduccionDisenio != null) {
                    oldIdParroquiaOfTProduccionDisenioListTProduccionDisenio.getTProduccionDisenioList().remove(TProduccionDisenioListTProduccionDisenio);
                    oldIdParroquiaOfTProduccionDisenioListTProduccionDisenio = em.merge(oldIdParroquiaOfTProduccionDisenioListTProduccionDisenio);
                }
            }
            for (TProduccionPapeletas TProduccionPapeletasListTProduccionPapeletas : TParroquia.getTProduccionPapeletasList()) {
                TParroquia oldIdParroquiaOfTProduccionPapeletasListTProduccionPapeletas = TProduccionPapeletasListTProduccionPapeletas.getIdParroquia();
                TProduccionPapeletasListTProduccionPapeletas.setIdParroquia(TParroquia);
                TProduccionPapeletasListTProduccionPapeletas = em.merge(TProduccionPapeletasListTProduccionPapeletas);
                if (oldIdParroquiaOfTProduccionPapeletasListTProduccionPapeletas != null) {
                    oldIdParroquiaOfTProduccionPapeletasListTProduccionPapeletas.getTProduccionPapeletasList().remove(TProduccionPapeletasListTProduccionPapeletas);
                    oldIdParroquiaOfTProduccionPapeletasListTProduccionPapeletas = em.merge(oldIdParroquiaOfTProduccionPapeletasListTProduccionPapeletas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTParroquia(TParroquia.getIdParroquia()) != null) {
                throw new PreexistingEntityException("TParroquia " + TParroquia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TParroquia TParroquia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TParroquia persistentTParroquia = em.find(TParroquia.class, TParroquia.getIdParroquia());
            TCanton idCantonOld = persistentTParroquia.getIdCanton();
            TCanton idCantonNew = TParroquia.getIdCanton();
            List<TZona> TZonaListOld = persistentTParroquia.getTZonaList();
            List<TZona> TZonaListNew = TParroquia.getTZonaList();
            List<TProduccionDisenio> TProduccionDisenioListOld = persistentTParroquia.getTProduccionDisenioList();
            List<TProduccionDisenio> TProduccionDisenioListNew = TParroquia.getTProduccionDisenioList();
            List<TProduccionPapeletas> TProduccionPapeletasListOld = persistentTParroquia.getTProduccionPapeletasList();
            List<TProduccionPapeletas> TProduccionPapeletasListNew = TParroquia.getTProduccionPapeletasList();
            List<String> illegalOrphanMessages = null;
            for (TZona TZonaListOldTZona : TZonaListOld) {
                if (!TZonaListNew.contains(TZonaListOldTZona)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TZona " + TZonaListOldTZona + " since its TParroquia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCantonNew != null) {
                idCantonNew = em.getReference(idCantonNew.getClass(), idCantonNew.getIdCanton());
                TParroquia.setIdCanton(idCantonNew);
            }
            List<TZona> attachedTZonaListNew = new ArrayList<TZona>();
            for (TZona TZonaListNewTZonaToAttach : TZonaListNew) {
                TZonaListNewTZonaToAttach = em.getReference(TZonaListNewTZonaToAttach.getClass(), TZonaListNewTZonaToAttach.getTZonaPK());
                attachedTZonaListNew.add(TZonaListNewTZonaToAttach);
            }
            TZonaListNew = attachedTZonaListNew;
            TParroquia.setTZonaList(TZonaListNew);
            List<TProduccionDisenio> attachedTProduccionDisenioListNew = new ArrayList<TProduccionDisenio>();
            for (TProduccionDisenio TProduccionDisenioListNewTProduccionDisenioToAttach : TProduccionDisenioListNew) {
                TProduccionDisenioListNewTProduccionDisenioToAttach = em.getReference(TProduccionDisenioListNewTProduccionDisenioToAttach.getClass(), TProduccionDisenioListNewTProduccionDisenioToAttach.getIdRegistro());
                attachedTProduccionDisenioListNew.add(TProduccionDisenioListNewTProduccionDisenioToAttach);
            }
            TProduccionDisenioListNew = attachedTProduccionDisenioListNew;
            TParroquia.setTProduccionDisenioList(TProduccionDisenioListNew);
            List<TProduccionPapeletas> attachedTProduccionPapeletasListNew = new ArrayList<TProduccionPapeletas>();
            for (TProduccionPapeletas TProduccionPapeletasListNewTProduccionPapeletasToAttach : TProduccionPapeletasListNew) {
                TProduccionPapeletasListNewTProduccionPapeletasToAttach = em.getReference(TProduccionPapeletasListNewTProduccionPapeletasToAttach.getClass(), TProduccionPapeletasListNewTProduccionPapeletasToAttach.getIdRegistro());
                attachedTProduccionPapeletasListNew.add(TProduccionPapeletasListNewTProduccionPapeletasToAttach);
            }
            TProduccionPapeletasListNew = attachedTProduccionPapeletasListNew;
            TParroquia.setTProduccionPapeletasList(TProduccionPapeletasListNew);
            TParroquia = em.merge(TParroquia);
            if (idCantonOld != null && !idCantonOld.equals(idCantonNew)) {
                idCantonOld.getTParroquiaList().remove(TParroquia);
                idCantonOld = em.merge(idCantonOld);
            }
            if (idCantonNew != null && !idCantonNew.equals(idCantonOld)) {
                idCantonNew.getTParroquiaList().add(TParroquia);
                idCantonNew = em.merge(idCantonNew);
            }
            for (TZona TZonaListNewTZona : TZonaListNew) {
                if (!TZonaListOld.contains(TZonaListNewTZona)) {
                    TParroquia oldTParroquiaOfTZonaListNewTZona = TZonaListNewTZona.getTParroquia();
                    TZonaListNewTZona.setTParroquia(TParroquia);
                    TZonaListNewTZona = em.merge(TZonaListNewTZona);
                    if (oldTParroquiaOfTZonaListNewTZona != null && !oldTParroquiaOfTZonaListNewTZona.equals(TParroquia)) {
                        oldTParroquiaOfTZonaListNewTZona.getTZonaList().remove(TZonaListNewTZona);
                        oldTParroquiaOfTZonaListNewTZona = em.merge(oldTParroquiaOfTZonaListNewTZona);
                    }
                }
            }
            for (TProduccionDisenio TProduccionDisenioListOldTProduccionDisenio : TProduccionDisenioListOld) {
                if (!TProduccionDisenioListNew.contains(TProduccionDisenioListOldTProduccionDisenio)) {
                    TProduccionDisenioListOldTProduccionDisenio.setIdParroquia(null);
                    TProduccionDisenioListOldTProduccionDisenio = em.merge(TProduccionDisenioListOldTProduccionDisenio);
                }
            }
            for (TProduccionDisenio TProduccionDisenioListNewTProduccionDisenio : TProduccionDisenioListNew) {
                if (!TProduccionDisenioListOld.contains(TProduccionDisenioListNewTProduccionDisenio)) {
                    TParroquia oldIdParroquiaOfTProduccionDisenioListNewTProduccionDisenio = TProduccionDisenioListNewTProduccionDisenio.getIdParroquia();
                    TProduccionDisenioListNewTProduccionDisenio.setIdParroquia(TParroquia);
                    TProduccionDisenioListNewTProduccionDisenio = em.merge(TProduccionDisenioListNewTProduccionDisenio);
                    if (oldIdParroquiaOfTProduccionDisenioListNewTProduccionDisenio != null && !oldIdParroquiaOfTProduccionDisenioListNewTProduccionDisenio.equals(TParroquia)) {
                        oldIdParroquiaOfTProduccionDisenioListNewTProduccionDisenio.getTProduccionDisenioList().remove(TProduccionDisenioListNewTProduccionDisenio);
                        oldIdParroquiaOfTProduccionDisenioListNewTProduccionDisenio = em.merge(oldIdParroquiaOfTProduccionDisenioListNewTProduccionDisenio);
                    }
                }
            }
            for (TProduccionPapeletas TProduccionPapeletasListOldTProduccionPapeletas : TProduccionPapeletasListOld) {
                if (!TProduccionPapeletasListNew.contains(TProduccionPapeletasListOldTProduccionPapeletas)) {
                    TProduccionPapeletasListOldTProduccionPapeletas.setIdParroquia(null);
                    TProduccionPapeletasListOldTProduccionPapeletas = em.merge(TProduccionPapeletasListOldTProduccionPapeletas);
                }
            }
            for (TProduccionPapeletas TProduccionPapeletasListNewTProduccionPapeletas : TProduccionPapeletasListNew) {
                if (!TProduccionPapeletasListOld.contains(TProduccionPapeletasListNewTProduccionPapeletas)) {
                    TParroquia oldIdParroquiaOfTProduccionPapeletasListNewTProduccionPapeletas = TProduccionPapeletasListNewTProduccionPapeletas.getIdParroquia();
                    TProduccionPapeletasListNewTProduccionPapeletas.setIdParroquia(TParroquia);
                    TProduccionPapeletasListNewTProduccionPapeletas = em.merge(TProduccionPapeletasListNewTProduccionPapeletas);
                    if (oldIdParroquiaOfTProduccionPapeletasListNewTProduccionPapeletas != null && !oldIdParroquiaOfTProduccionPapeletasListNewTProduccionPapeletas.equals(TParroquia)) {
                        oldIdParroquiaOfTProduccionPapeletasListNewTProduccionPapeletas.getTProduccionPapeletasList().remove(TProduccionPapeletasListNewTProduccionPapeletas);
                        oldIdParroquiaOfTProduccionPapeletasListNewTProduccionPapeletas = em.merge(oldIdParroquiaOfTProduccionPapeletasListNewTProduccionPapeletas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = TParroquia.getIdParroquia();
                if (findTParroquia(id) == null) {
                    throw new NonexistentEntityException("The tParroquia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TParroquia TParroquia;
            try {
                TParroquia = em.getReference(TParroquia.class, id);
                TParroquia.getIdParroquia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TParroquia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<TZona> TZonaListOrphanCheck = TParroquia.getTZonaList();
            for (TZona TZonaListOrphanCheckTZona : TZonaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TParroquia (" + TParroquia + ") cannot be destroyed since the TZona " + TZonaListOrphanCheckTZona + " in its TZonaList field has a non-nullable TParroquia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TCanton idCanton = TParroquia.getIdCanton();
            if (idCanton != null) {
                idCanton.getTParroquiaList().remove(TParroquia);
                idCanton = em.merge(idCanton);
            }
            List<TProduccionDisenio> TProduccionDisenioList = TParroquia.getTProduccionDisenioList();
            for (TProduccionDisenio TProduccionDisenioListTProduccionDisenio : TProduccionDisenioList) {
                TProduccionDisenioListTProduccionDisenio.setIdParroquia(null);
                TProduccionDisenioListTProduccionDisenio = em.merge(TProduccionDisenioListTProduccionDisenio);
            }
            List<TProduccionPapeletas> TProduccionPapeletasList = TParroquia.getTProduccionPapeletasList();
            for (TProduccionPapeletas TProduccionPapeletasListTProduccionPapeletas : TProduccionPapeletasList) {
                TProduccionPapeletasListTProduccionPapeletas.setIdParroquia(null);
                TProduccionPapeletasListTProduccionPapeletas = em.merge(TProduccionPapeletasListTProduccionPapeletas);
            }
            em.remove(TParroquia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TParroquia> findTParroquiaEntities() {
        return findTParroquiaEntities(true, -1, -1);
    }

    public List<TParroquia> findTParroquiaEntities(int maxResults, int firstResult) {
        return findTParroquiaEntities(false, maxResults, firstResult);
    }

    private List<TParroquia> findTParroquiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TParroquia.class));
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

    public TParroquia findTParroquia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TParroquia.class, id);
        } finally {
            em.close();
        }
    }

    public int getTParroquiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TParroquia> rt = cq.from(TParroquia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
