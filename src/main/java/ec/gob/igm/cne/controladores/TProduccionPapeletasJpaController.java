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
import ec.gob.igm.cne.entidades.TDignidad;
import ec.gob.igm.cne.entidades.TParroquia;
import ec.gob.igm.cne.entidades.TPrensas;
import ec.gob.igm.cne.entidades.TProduccionPapeletas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desarrollo
 */
public class TProduccionPapeletasJpaController implements Serializable {

    public TProduccionPapeletasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TProduccionPapeletas TProduccionPapeletas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TCanton idCanton = TProduccionPapeletas.getIdCanton();
            if (idCanton != null) {
                idCanton = em.getReference(idCanton.getClass(), idCanton.getIdCanton());
                TProduccionPapeletas.setIdCanton(idCanton);
            }
            TDignidad codDignidad = TProduccionPapeletas.getCodDignidad();
            if (codDignidad != null) {
                codDignidad = em.getReference(codDignidad.getClass(), codDignidad.getCodDignidad());
                TProduccionPapeletas.setCodDignidad(codDignidad);
            }
            TParroquia idParroquia = TProduccionPapeletas.getIdParroquia();
            if (idParroquia != null) {
                idParroquia = em.getReference(idParroquia.getClass(), idParroquia.getIdParroquia());
                TProduccionPapeletas.setIdParroquia(idParroquia);
            }
            TPrensas idPrensa = TProduccionPapeletas.getIdPrensa();
            if (idPrensa != null) {
                idPrensa = em.getReference(idPrensa.getClass(), idPrensa.getIdPrensa());
                TProduccionPapeletas.setIdPrensa(idPrensa);
            }
            em.persist(TProduccionPapeletas);
            if (idCanton != null) {
                idCanton.getTProduccionPapeletasList().add(TProduccionPapeletas);
                idCanton = em.merge(idCanton);
            }
            if (codDignidad != null) {
                codDignidad.getTProduccionPapeletasList().add(TProduccionPapeletas);
                codDignidad = em.merge(codDignidad);
            }
            if (idParroquia != null) {
                idParroquia.getTProduccionPapeletasList().add(TProduccionPapeletas);
                idParroquia = em.merge(idParroquia);
            }
            if (idPrensa != null) {
                idPrensa.getTProduccionPapeletasList().add(TProduccionPapeletas);
                idPrensa = em.merge(idPrensa);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTProduccionPapeletas(TProduccionPapeletas.getIdRegistro()) != null) {
                throw new PreexistingEntityException("TProduccionPapeletas " + TProduccionPapeletas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TProduccionPapeletas TProduccionPapeletas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TProduccionPapeletas persistentTProduccionPapeletas = em.find(TProduccionPapeletas.class, TProduccionPapeletas.getIdRegistro());
            TCanton idCantonOld = persistentTProduccionPapeletas.getIdCanton();
            TCanton idCantonNew = TProduccionPapeletas.getIdCanton();
            TDignidad codDignidadOld = persistentTProduccionPapeletas.getCodDignidad();
            TDignidad codDignidadNew = TProduccionPapeletas.getCodDignidad();
            TParroquia idParroquiaOld = persistentTProduccionPapeletas.getIdParroquia();
            TParroquia idParroquiaNew = TProduccionPapeletas.getIdParroquia();
            TPrensas idPrensaOld = persistentTProduccionPapeletas.getIdPrensa();
            TPrensas idPrensaNew = TProduccionPapeletas.getIdPrensa();
            if (idCantonNew != null) {
                idCantonNew = em.getReference(idCantonNew.getClass(), idCantonNew.getIdCanton());
                TProduccionPapeletas.setIdCanton(idCantonNew);
            }
            if (codDignidadNew != null) {
                codDignidadNew = em.getReference(codDignidadNew.getClass(), codDignidadNew.getCodDignidad());
                TProduccionPapeletas.setCodDignidad(codDignidadNew);
            }
            if (idParroquiaNew != null) {
                idParroquiaNew = em.getReference(idParroquiaNew.getClass(), idParroquiaNew.getIdParroquia());
                TProduccionPapeletas.setIdParroquia(idParroquiaNew);
            }
            if (idPrensaNew != null) {
                idPrensaNew = em.getReference(idPrensaNew.getClass(), idPrensaNew.getIdPrensa());
                TProduccionPapeletas.setIdPrensa(idPrensaNew);
            }
            TProduccionPapeletas = em.merge(TProduccionPapeletas);
            if (idCantonOld != null && !idCantonOld.equals(idCantonNew)) {
                idCantonOld.getTProduccionPapeletasList().remove(TProduccionPapeletas);
                idCantonOld = em.merge(idCantonOld);
            }
            if (idCantonNew != null && !idCantonNew.equals(idCantonOld)) {
                idCantonNew.getTProduccionPapeletasList().add(TProduccionPapeletas);
                idCantonNew = em.merge(idCantonNew);
            }
            if (codDignidadOld != null && !codDignidadOld.equals(codDignidadNew)) {
                codDignidadOld.getTProduccionPapeletasList().remove(TProduccionPapeletas);
                codDignidadOld = em.merge(codDignidadOld);
            }
            if (codDignidadNew != null && !codDignidadNew.equals(codDignidadOld)) {
                codDignidadNew.getTProduccionPapeletasList().add(TProduccionPapeletas);
                codDignidadNew = em.merge(codDignidadNew);
            }
            if (idParroquiaOld != null && !idParroquiaOld.equals(idParroquiaNew)) {
                idParroquiaOld.getTProduccionPapeletasList().remove(TProduccionPapeletas);
                idParroquiaOld = em.merge(idParroquiaOld);
            }
            if (idParroquiaNew != null && !idParroquiaNew.equals(idParroquiaOld)) {
                idParroquiaNew.getTProduccionPapeletasList().add(TProduccionPapeletas);
                idParroquiaNew = em.merge(idParroquiaNew);
            }
            if (idPrensaOld != null && !idPrensaOld.equals(idPrensaNew)) {
                idPrensaOld.getTProduccionPapeletasList().remove(TProduccionPapeletas);
                idPrensaOld = em.merge(idPrensaOld);
            }
            if (idPrensaNew != null && !idPrensaNew.equals(idPrensaOld)) {
                idPrensaNew.getTProduccionPapeletasList().add(TProduccionPapeletas);
                idPrensaNew = em.merge(idPrensaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = TProduccionPapeletas.getIdRegistro();
                if (findTProduccionPapeletas(id) == null) {
                    throw new NonexistentEntityException("The tProduccionPapeletas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TProduccionPapeletas TProduccionPapeletas;
            try {
                TProduccionPapeletas = em.getReference(TProduccionPapeletas.class, id);
                TProduccionPapeletas.getIdRegistro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TProduccionPapeletas with id " + id + " no longer exists.", enfe);
            }
            TCanton idCanton = TProduccionPapeletas.getIdCanton();
            if (idCanton != null) {
                idCanton.getTProduccionPapeletasList().remove(TProduccionPapeletas);
                idCanton = em.merge(idCanton);
            }
            TDignidad codDignidad = TProduccionPapeletas.getCodDignidad();
            if (codDignidad != null) {
                codDignidad.getTProduccionPapeletasList().remove(TProduccionPapeletas);
                codDignidad = em.merge(codDignidad);
            }
            TParroquia idParroquia = TProduccionPapeletas.getIdParroquia();
            if (idParroquia != null) {
                idParroquia.getTProduccionPapeletasList().remove(TProduccionPapeletas);
                idParroquia = em.merge(idParroquia);
            }
            TPrensas idPrensa = TProduccionPapeletas.getIdPrensa();
            if (idPrensa != null) {
                idPrensa.getTProduccionPapeletasList().remove(TProduccionPapeletas);
                idPrensa = em.merge(idPrensa);
            }
            em.remove(TProduccionPapeletas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TProduccionPapeletas> findTProduccionPapeletasEntities() {
        return findTProduccionPapeletasEntities(true, -1, -1);
    }

    public List<TProduccionPapeletas> findTProduccionPapeletasEntities(int maxResults, int firstResult) {
        return findTProduccionPapeletasEntities(false, maxResults, firstResult);
    }

    private List<TProduccionPapeletas> findTProduccionPapeletasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TProduccionPapeletas.class));
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

    public TProduccionPapeletas findTProduccionPapeletas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TProduccionPapeletas.class, id);
        } finally {
            em.close();
        }
    }

    public int getTProduccionPapeletasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TProduccionPapeletas> rt = cq.from(TProduccionPapeletas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
