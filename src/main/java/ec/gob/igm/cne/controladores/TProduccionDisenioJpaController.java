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
import ec.gob.igm.cne.entidades.TProduccionDisenio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desarrollo
 */
public class TProduccionDisenioJpaController implements Serializable {

    public TProduccionDisenioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TProduccionDisenio TProduccionDisenio) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TCanton idCanton = TProduccionDisenio.getIdCanton();
            if (idCanton != null) {
                idCanton = em.getReference(idCanton.getClass(), idCanton.getIdCanton());
                TProduccionDisenio.setIdCanton(idCanton);
            }
            TDignidad codDignidad = TProduccionDisenio.getCodDignidad();
            if (codDignidad != null) {
                codDignidad = em.getReference(codDignidad.getClass(), codDignidad.getCodDignidad());
                TProduccionDisenio.setCodDignidad(codDignidad);
            }
            TParroquia idParroquia = TProduccionDisenio.getIdParroquia();
            if (idParroquia != null) {
                idParroquia = em.getReference(idParroquia.getClass(), idParroquia.getIdParroquia());
                TProduccionDisenio.setIdParroquia(idParroquia);
            }
            TPrensas idPrensa = TProduccionDisenio.getIdPrensa();
            if (idPrensa != null) {
                idPrensa = em.getReference(idPrensa.getClass(), idPrensa.getIdPrensa());
                TProduccionDisenio.setIdPrensa(idPrensa);
            }
            em.persist(TProduccionDisenio);
            if (idCanton != null) {
                idCanton.getTProduccionDisenioList().add(TProduccionDisenio);
                idCanton = em.merge(idCanton);
            }
            if (codDignidad != null) {
                codDignidad.getTProduccionDisenioList().add(TProduccionDisenio);
                codDignidad = em.merge(codDignidad);
            }
            if (idParroquia != null) {
                idParroquia.getTProduccionDisenioList().add(TProduccionDisenio);
                idParroquia = em.merge(idParroquia);
            }
            if (idPrensa != null) {
                idPrensa.getTProduccionDisenioList().add(TProduccionDisenio);
                idPrensa = em.merge(idPrensa);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTProduccionDisenio(TProduccionDisenio.getIdRegistro()) != null) {
                throw new PreexistingEntityException("TProduccionDisenio " + TProduccionDisenio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TProduccionDisenio TProduccionDisenio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TProduccionDisenio persistentTProduccionDisenio = em.find(TProduccionDisenio.class, TProduccionDisenio.getIdRegistro());
            TCanton idCantonOld = persistentTProduccionDisenio.getIdCanton();
            TCanton idCantonNew = TProduccionDisenio.getIdCanton();
            TDignidad codDignidadOld = persistentTProduccionDisenio.getCodDignidad();
            TDignidad codDignidadNew = TProduccionDisenio.getCodDignidad();
            TParroquia idParroquiaOld = persistentTProduccionDisenio.getIdParroquia();
            TParroquia idParroquiaNew = TProduccionDisenio.getIdParroquia();
            TPrensas idPrensaOld = persistentTProduccionDisenio.getIdPrensa();
            TPrensas idPrensaNew = TProduccionDisenio.getIdPrensa();
            if (idCantonNew != null) {
                idCantonNew = em.getReference(idCantonNew.getClass(), idCantonNew.getIdCanton());
                TProduccionDisenio.setIdCanton(idCantonNew);
            }
            if (codDignidadNew != null) {
                codDignidadNew = em.getReference(codDignidadNew.getClass(), codDignidadNew.getCodDignidad());
                TProduccionDisenio.setCodDignidad(codDignidadNew);
            }
            if (idParroquiaNew != null) {
                idParroquiaNew = em.getReference(idParroquiaNew.getClass(), idParroquiaNew.getIdParroquia());
                TProduccionDisenio.setIdParroquia(idParroquiaNew);
            }
            if (idPrensaNew != null) {
                idPrensaNew = em.getReference(idPrensaNew.getClass(), idPrensaNew.getIdPrensa());
                TProduccionDisenio.setIdPrensa(idPrensaNew);
            }
            TProduccionDisenio = em.merge(TProduccionDisenio);
            if (idCantonOld != null && !idCantonOld.equals(idCantonNew)) {
                idCantonOld.getTProduccionDisenioList().remove(TProduccionDisenio);
                idCantonOld = em.merge(idCantonOld);
            }
            if (idCantonNew != null && !idCantonNew.equals(idCantonOld)) {
                idCantonNew.getTProduccionDisenioList().add(TProduccionDisenio);
                idCantonNew = em.merge(idCantonNew);
            }
            if (codDignidadOld != null && !codDignidadOld.equals(codDignidadNew)) {
                codDignidadOld.getTProduccionDisenioList().remove(TProduccionDisenio);
                codDignidadOld = em.merge(codDignidadOld);
            }
            if (codDignidadNew != null && !codDignidadNew.equals(codDignidadOld)) {
                codDignidadNew.getTProduccionDisenioList().add(TProduccionDisenio);
                codDignidadNew = em.merge(codDignidadNew);
            }
            if (idParroquiaOld != null && !idParroquiaOld.equals(idParroquiaNew)) {
                idParroquiaOld.getTProduccionDisenioList().remove(TProduccionDisenio);
                idParroquiaOld = em.merge(idParroquiaOld);
            }
            if (idParroquiaNew != null && !idParroquiaNew.equals(idParroquiaOld)) {
                idParroquiaNew.getTProduccionDisenioList().add(TProduccionDisenio);
                idParroquiaNew = em.merge(idParroquiaNew);
            }
            if (idPrensaOld != null && !idPrensaOld.equals(idPrensaNew)) {
                idPrensaOld.getTProduccionDisenioList().remove(TProduccionDisenio);
                idPrensaOld = em.merge(idPrensaOld);
            }
            if (idPrensaNew != null && !idPrensaNew.equals(idPrensaOld)) {
                idPrensaNew.getTProduccionDisenioList().add(TProduccionDisenio);
                idPrensaNew = em.merge(idPrensaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = TProduccionDisenio.getIdRegistro();
                if (findTProduccionDisenio(id) == null) {
                    throw new NonexistentEntityException("The tProduccionDisenio with id " + id + " no longer exists.");
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
            TProduccionDisenio TProduccionDisenio;
            try {
                TProduccionDisenio = em.getReference(TProduccionDisenio.class, id);
                TProduccionDisenio.getIdRegistro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TProduccionDisenio with id " + id + " no longer exists.", enfe);
            }
            TCanton idCanton = TProduccionDisenio.getIdCanton();
            if (idCanton != null) {
                idCanton.getTProduccionDisenioList().remove(TProduccionDisenio);
                idCanton = em.merge(idCanton);
            }
            TDignidad codDignidad = TProduccionDisenio.getCodDignidad();
            if (codDignidad != null) {
                codDignidad.getTProduccionDisenioList().remove(TProduccionDisenio);
                codDignidad = em.merge(codDignidad);
            }
            TParroquia idParroquia = TProduccionDisenio.getIdParroquia();
            if (idParroquia != null) {
                idParroquia.getTProduccionDisenioList().remove(TProduccionDisenio);
                idParroquia = em.merge(idParroquia);
            }
            TPrensas idPrensa = TProduccionDisenio.getIdPrensa();
            if (idPrensa != null) {
                idPrensa.getTProduccionDisenioList().remove(TProduccionDisenio);
                idPrensa = em.merge(idPrensa);
            }
            em.remove(TProduccionDisenio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TProduccionDisenio> findTProduccionDisenioEntities() {
        return findTProduccionDisenioEntities(true, -1, -1);
    }

    public List<TProduccionDisenio> findTProduccionDisenioEntities(int maxResults, int firstResult) {
        return findTProduccionDisenioEntities(false, maxResults, firstResult);
    }

    private List<TProduccionDisenio> findTProduccionDisenioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TProduccionDisenio.class));
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

    public TProduccionDisenio findTProduccionDisenio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TProduccionDisenio.class, id);
        } finally {
            em.close();
        }
    }

    public int getTProduccionDisenioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TProduccionDisenio> rt = cq.from(TProduccionDisenio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
