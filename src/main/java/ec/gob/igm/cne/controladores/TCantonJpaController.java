/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import ec.gob.igm.cne.entidades.TCanton;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.gob.igm.cne.entidades.TProvincia;
import ec.gob.igm.cne.entidades.TParroquia;
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
public class TCantonJpaController implements Serializable {

    public TCantonJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TCanton TCanton) throws PreexistingEntityException, Exception {
        if (TCanton.getTParroquiaList() == null) {
            TCanton.setTParroquiaList(new ArrayList<TParroquia>());
        }
        if (TCanton.getTProduccionDisenioList() == null) {
            TCanton.setTProduccionDisenioList(new ArrayList<TProduccionDisenio>());
        }
        if (TCanton.getTProduccionPapeletasList() == null) {
            TCanton.setTProduccionPapeletasList(new ArrayList<TProduccionPapeletas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TProvincia idProvincia = TCanton.getIdProvincia();
            if (idProvincia != null) {
                idProvincia = em.getReference(idProvincia.getClass(), idProvincia.getIdProvincia());
                TCanton.setIdProvincia(idProvincia);
            }
            List<TParroquia> attachedTParroquiaList = new ArrayList<TParroquia>();
            for (TParroquia TParroquiaListTParroquiaToAttach : TCanton.getTParroquiaList()) {
                TParroquiaListTParroquiaToAttach = em.getReference(TParroquiaListTParroquiaToAttach.getClass(), TParroquiaListTParroquiaToAttach.getIdParroquia());
                attachedTParroquiaList.add(TParroquiaListTParroquiaToAttach);
            }
            TCanton.setTParroquiaList(attachedTParroquiaList);
            List<TProduccionDisenio> attachedTProduccionDisenioList = new ArrayList<TProduccionDisenio>();
            for (TProduccionDisenio TProduccionDisenioListTProduccionDisenioToAttach : TCanton.getTProduccionDisenioList()) {
                TProduccionDisenioListTProduccionDisenioToAttach = em.getReference(TProduccionDisenioListTProduccionDisenioToAttach.getClass(), TProduccionDisenioListTProduccionDisenioToAttach.getIdRegistro());
                attachedTProduccionDisenioList.add(TProduccionDisenioListTProduccionDisenioToAttach);
            }
            TCanton.setTProduccionDisenioList(attachedTProduccionDisenioList);
            List<TProduccionPapeletas> attachedTProduccionPapeletasList = new ArrayList<TProduccionPapeletas>();
            for (TProduccionPapeletas TProduccionPapeletasListTProduccionPapeletasToAttach : TCanton.getTProduccionPapeletasList()) {
                TProduccionPapeletasListTProduccionPapeletasToAttach = em.getReference(TProduccionPapeletasListTProduccionPapeletasToAttach.getClass(), TProduccionPapeletasListTProduccionPapeletasToAttach.getIdRegistro());
                attachedTProduccionPapeletasList.add(TProduccionPapeletasListTProduccionPapeletasToAttach);
            }
            TCanton.setTProduccionPapeletasList(attachedTProduccionPapeletasList);
            em.persist(TCanton);
            if (idProvincia != null) {
                idProvincia.getTCantonList().add(TCanton);
                idProvincia = em.merge(idProvincia);
            }
            for (TParroquia TParroquiaListTParroquia : TCanton.getTParroquiaList()) {
                TCanton oldIdCantonOfTParroquiaListTParroquia = TParroquiaListTParroquia.getIdCanton();
                TParroquiaListTParroquia.setIdCanton(TCanton);
                TParroquiaListTParroquia = em.merge(TParroquiaListTParroquia);
                if (oldIdCantonOfTParroquiaListTParroquia != null) {
                    oldIdCantonOfTParroquiaListTParroquia.getTParroquiaList().remove(TParroquiaListTParroquia);
                    oldIdCantonOfTParroquiaListTParroquia = em.merge(oldIdCantonOfTParroquiaListTParroquia);
                }
            }
            for (TProduccionDisenio TProduccionDisenioListTProduccionDisenio : TCanton.getTProduccionDisenioList()) {
                TCanton oldIdCantonOfTProduccionDisenioListTProduccionDisenio = TProduccionDisenioListTProduccionDisenio.getIdCanton();
                TProduccionDisenioListTProduccionDisenio.setIdCanton(TCanton);
                TProduccionDisenioListTProduccionDisenio = em.merge(TProduccionDisenioListTProduccionDisenio);
                if (oldIdCantonOfTProduccionDisenioListTProduccionDisenio != null) {
                    oldIdCantonOfTProduccionDisenioListTProduccionDisenio.getTProduccionDisenioList().remove(TProduccionDisenioListTProduccionDisenio);
                    oldIdCantonOfTProduccionDisenioListTProduccionDisenio = em.merge(oldIdCantonOfTProduccionDisenioListTProduccionDisenio);
                }
            }
            for (TProduccionPapeletas TProduccionPapeletasListTProduccionPapeletas : TCanton.getTProduccionPapeletasList()) {
                TCanton oldIdCantonOfTProduccionPapeletasListTProduccionPapeletas = TProduccionPapeletasListTProduccionPapeletas.getIdCanton();
                TProduccionPapeletasListTProduccionPapeletas.setIdCanton(TCanton);
                TProduccionPapeletasListTProduccionPapeletas = em.merge(TProduccionPapeletasListTProduccionPapeletas);
                if (oldIdCantonOfTProduccionPapeletasListTProduccionPapeletas != null) {
                    oldIdCantonOfTProduccionPapeletasListTProduccionPapeletas.getTProduccionPapeletasList().remove(TProduccionPapeletasListTProduccionPapeletas);
                    oldIdCantonOfTProduccionPapeletasListTProduccionPapeletas = em.merge(oldIdCantonOfTProduccionPapeletasListTProduccionPapeletas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTCanton(TCanton.getIdCanton()) != null) {
                throw new PreexistingEntityException("TCanton " + TCanton + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TCanton TCanton) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TCanton persistentTCanton = em.find(TCanton.class, TCanton.getIdCanton());
            TProvincia idProvinciaOld = persistentTCanton.getIdProvincia();
            TProvincia idProvinciaNew = TCanton.getIdProvincia();
            List<TParroquia> TParroquiaListOld = persistentTCanton.getTParroquiaList();
            List<TParroquia> TParroquiaListNew = TCanton.getTParroquiaList();
            List<TProduccionDisenio> TProduccionDisenioListOld = persistentTCanton.getTProduccionDisenioList();
            List<TProduccionDisenio> TProduccionDisenioListNew = TCanton.getTProduccionDisenioList();
            List<TProduccionPapeletas> TProduccionPapeletasListOld = persistentTCanton.getTProduccionPapeletasList();
            List<TProduccionPapeletas> TProduccionPapeletasListNew = TCanton.getTProduccionPapeletasList();
            if (idProvinciaNew != null) {
                idProvinciaNew = em.getReference(idProvinciaNew.getClass(), idProvinciaNew.getIdProvincia());
                TCanton.setIdProvincia(idProvinciaNew);
            }
            List<TParroquia> attachedTParroquiaListNew = new ArrayList<TParroquia>();
            for (TParroquia TParroquiaListNewTParroquiaToAttach : TParroquiaListNew) {
                TParroquiaListNewTParroquiaToAttach = em.getReference(TParroquiaListNewTParroquiaToAttach.getClass(), TParroquiaListNewTParroquiaToAttach.getIdParroquia());
                attachedTParroquiaListNew.add(TParroquiaListNewTParroquiaToAttach);
            }
            TParroquiaListNew = attachedTParroquiaListNew;
            TCanton.setTParroquiaList(TParroquiaListNew);
            List<TProduccionDisenio> attachedTProduccionDisenioListNew = new ArrayList<TProduccionDisenio>();
            for (TProduccionDisenio TProduccionDisenioListNewTProduccionDisenioToAttach : TProduccionDisenioListNew) {
                TProduccionDisenioListNewTProduccionDisenioToAttach = em.getReference(TProduccionDisenioListNewTProduccionDisenioToAttach.getClass(), TProduccionDisenioListNewTProduccionDisenioToAttach.getIdRegistro());
                attachedTProduccionDisenioListNew.add(TProduccionDisenioListNewTProduccionDisenioToAttach);
            }
            TProduccionDisenioListNew = attachedTProduccionDisenioListNew;
            TCanton.setTProduccionDisenioList(TProduccionDisenioListNew);
            List<TProduccionPapeletas> attachedTProduccionPapeletasListNew = new ArrayList<TProduccionPapeletas>();
            for (TProduccionPapeletas TProduccionPapeletasListNewTProduccionPapeletasToAttach : TProduccionPapeletasListNew) {
                TProduccionPapeletasListNewTProduccionPapeletasToAttach = em.getReference(TProduccionPapeletasListNewTProduccionPapeletasToAttach.getClass(), TProduccionPapeletasListNewTProduccionPapeletasToAttach.getIdRegistro());
                attachedTProduccionPapeletasListNew.add(TProduccionPapeletasListNewTProduccionPapeletasToAttach);
            }
            TProduccionPapeletasListNew = attachedTProduccionPapeletasListNew;
            TCanton.setTProduccionPapeletasList(TProduccionPapeletasListNew);
            TCanton = em.merge(TCanton);
            if (idProvinciaOld != null && !idProvinciaOld.equals(idProvinciaNew)) {
                idProvinciaOld.getTCantonList().remove(TCanton);
                idProvinciaOld = em.merge(idProvinciaOld);
            }
            if (idProvinciaNew != null && !idProvinciaNew.equals(idProvinciaOld)) {
                idProvinciaNew.getTCantonList().add(TCanton);
                idProvinciaNew = em.merge(idProvinciaNew);
            }
            for (TParroquia TParroquiaListOldTParroquia : TParroquiaListOld) {
                if (!TParroquiaListNew.contains(TParroquiaListOldTParroquia)) {
                    TParroquiaListOldTParroquia.setIdCanton(null);
                    TParroquiaListOldTParroquia = em.merge(TParroquiaListOldTParroquia);
                }
            }
            for (TParroquia TParroquiaListNewTParroquia : TParroquiaListNew) {
                if (!TParroquiaListOld.contains(TParroquiaListNewTParroquia)) {
                    TCanton oldIdCantonOfTParroquiaListNewTParroquia = TParroquiaListNewTParroquia.getIdCanton();
                    TParroquiaListNewTParroquia.setIdCanton(TCanton);
                    TParroquiaListNewTParroquia = em.merge(TParroquiaListNewTParroquia);
                    if (oldIdCantonOfTParroquiaListNewTParroquia != null && !oldIdCantonOfTParroquiaListNewTParroquia.equals(TCanton)) {
                        oldIdCantonOfTParroquiaListNewTParroquia.getTParroquiaList().remove(TParroquiaListNewTParroquia);
                        oldIdCantonOfTParroquiaListNewTParroquia = em.merge(oldIdCantonOfTParroquiaListNewTParroquia);
                    }
                }
            }
            for (TProduccionDisenio TProduccionDisenioListOldTProduccionDisenio : TProduccionDisenioListOld) {
                if (!TProduccionDisenioListNew.contains(TProduccionDisenioListOldTProduccionDisenio)) {
                    TProduccionDisenioListOldTProduccionDisenio.setIdCanton(null);
                    TProduccionDisenioListOldTProduccionDisenio = em.merge(TProduccionDisenioListOldTProduccionDisenio);
                }
            }
            for (TProduccionDisenio TProduccionDisenioListNewTProduccionDisenio : TProduccionDisenioListNew) {
                if (!TProduccionDisenioListOld.contains(TProduccionDisenioListNewTProduccionDisenio)) {
                    TCanton oldIdCantonOfTProduccionDisenioListNewTProduccionDisenio = TProduccionDisenioListNewTProduccionDisenio.getIdCanton();
                    TProduccionDisenioListNewTProduccionDisenio.setIdCanton(TCanton);
                    TProduccionDisenioListNewTProduccionDisenio = em.merge(TProduccionDisenioListNewTProduccionDisenio);
                    if (oldIdCantonOfTProduccionDisenioListNewTProduccionDisenio != null && !oldIdCantonOfTProduccionDisenioListNewTProduccionDisenio.equals(TCanton)) {
                        oldIdCantonOfTProduccionDisenioListNewTProduccionDisenio.getTProduccionDisenioList().remove(TProduccionDisenioListNewTProduccionDisenio);
                        oldIdCantonOfTProduccionDisenioListNewTProduccionDisenio = em.merge(oldIdCantonOfTProduccionDisenioListNewTProduccionDisenio);
                    }
                }
            }
            for (TProduccionPapeletas TProduccionPapeletasListOldTProduccionPapeletas : TProduccionPapeletasListOld) {
                if (!TProduccionPapeletasListNew.contains(TProduccionPapeletasListOldTProduccionPapeletas)) {
                    TProduccionPapeletasListOldTProduccionPapeletas.setIdCanton(null);
                    TProduccionPapeletasListOldTProduccionPapeletas = em.merge(TProduccionPapeletasListOldTProduccionPapeletas);
                }
            }
            for (TProduccionPapeletas TProduccionPapeletasListNewTProduccionPapeletas : TProduccionPapeletasListNew) {
                if (!TProduccionPapeletasListOld.contains(TProduccionPapeletasListNewTProduccionPapeletas)) {
                    TCanton oldIdCantonOfTProduccionPapeletasListNewTProduccionPapeletas = TProduccionPapeletasListNewTProduccionPapeletas.getIdCanton();
                    TProduccionPapeletasListNewTProduccionPapeletas.setIdCanton(TCanton);
                    TProduccionPapeletasListNewTProduccionPapeletas = em.merge(TProduccionPapeletasListNewTProduccionPapeletas);
                    if (oldIdCantonOfTProduccionPapeletasListNewTProduccionPapeletas != null && !oldIdCantonOfTProduccionPapeletasListNewTProduccionPapeletas.equals(TCanton)) {
                        oldIdCantonOfTProduccionPapeletasListNewTProduccionPapeletas.getTProduccionPapeletasList().remove(TProduccionPapeletasListNewTProduccionPapeletas);
                        oldIdCantonOfTProduccionPapeletasListNewTProduccionPapeletas = em.merge(oldIdCantonOfTProduccionPapeletasListNewTProduccionPapeletas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = TCanton.getIdCanton();
                if (findTCanton(id) == null) {
                    throw new NonexistentEntityException("The tCanton with id " + id + " no longer exists.");
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
            TCanton TCanton;
            try {
                TCanton = em.getReference(TCanton.class, id);
                TCanton.getIdCanton();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TCanton with id " + id + " no longer exists.", enfe);
            }
            TProvincia idProvincia = TCanton.getIdProvincia();
            if (idProvincia != null) {
                idProvincia.getTCantonList().remove(TCanton);
                idProvincia = em.merge(idProvincia);
            }
            List<TParroquia> TParroquiaList = TCanton.getTParroquiaList();
            for (TParroquia TParroquiaListTParroquia : TParroquiaList) {
                TParroquiaListTParroquia.setIdCanton(null);
                TParroquiaListTParroquia = em.merge(TParroquiaListTParroquia);
            }
            List<TProduccionDisenio> TProduccionDisenioList = TCanton.getTProduccionDisenioList();
            for (TProduccionDisenio TProduccionDisenioListTProduccionDisenio : TProduccionDisenioList) {
                TProduccionDisenioListTProduccionDisenio.setIdCanton(null);
                TProduccionDisenioListTProduccionDisenio = em.merge(TProduccionDisenioListTProduccionDisenio);
            }
            List<TProduccionPapeletas> TProduccionPapeletasList = TCanton.getTProduccionPapeletasList();
            for (TProduccionPapeletas TProduccionPapeletasListTProduccionPapeletas : TProduccionPapeletasList) {
                TProduccionPapeletasListTProduccionPapeletas.setIdCanton(null);
                TProduccionPapeletasListTProduccionPapeletas = em.merge(TProduccionPapeletasListTProduccionPapeletas);
            }
            em.remove(TCanton);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TCanton> findTCantonEntities() {
        return findTCantonEntities(true, -1, -1);
    }

    public List<TCanton> findTCantonEntities(int maxResults, int firstResult) {
        return findTCantonEntities(false, maxResults, firstResult);
    }

    private List<TCanton> findTCantonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TCanton.class));
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

    public TCanton findTCanton(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TCanton.class, id);
        } finally {
            em.close();
        }
    }

    public int getTCantonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TCanton> rt = cq.from(TCanton.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
