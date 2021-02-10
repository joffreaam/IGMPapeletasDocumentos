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
import ec.gob.igm.cne.entidades.TDignidad;
import ec.gob.igm.cne.entidades.TDignidadXUsuario;
import ec.gob.igm.cne.entidades.TUsuario;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desarrollo
 */
public class TDignidadXUsuarioJpaController implements Serializable {

    public TDignidadXUsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TDignidadXUsuario TDignidadXUsuario) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDignidad idDignidad = TDignidadXUsuario.getIdDignidad();
            if (idDignidad != null) {
                idDignidad = em.getReference(idDignidad.getClass(), idDignidad.getCodDignidad());
                TDignidadXUsuario.setIdDignidad(idDignidad);
            }
            TUsuario idUsuario = TDignidadXUsuario.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                TDignidadXUsuario.setIdUsuario(idUsuario);
            }
            em.persist(TDignidadXUsuario);
            if (idDignidad != null) {
                idDignidad.getTDignidadXUsuarioList().add(TDignidadXUsuario);
                idDignidad = em.merge(idDignidad);
            }
            if (idUsuario != null) {
                idUsuario.getTDignidadXUsuarioList().add(TDignidadXUsuario);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTDignidadXUsuario(TDignidadXUsuario.getId()) != null) {
                throw new PreexistingEntityException("TDignidadXUsuario " + TDignidadXUsuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TDignidadXUsuario TDignidadXUsuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDignidadXUsuario persistentTDignidadXUsuario = em.find(TDignidadXUsuario.class, TDignidadXUsuario.getId());
            TDignidad idDignidadOld = persistentTDignidadXUsuario.getIdDignidad();
            TDignidad idDignidadNew = TDignidadXUsuario.getIdDignidad();
            TUsuario idUsuarioOld = persistentTDignidadXUsuario.getIdUsuario();
            TUsuario idUsuarioNew = TDignidadXUsuario.getIdUsuario();
            if (idDignidadNew != null) {
                idDignidadNew = em.getReference(idDignidadNew.getClass(), idDignidadNew.getCodDignidad());
                TDignidadXUsuario.setIdDignidad(idDignidadNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                TDignidadXUsuario.setIdUsuario(idUsuarioNew);
            }
            TDignidadXUsuario = em.merge(TDignidadXUsuario);
            if (idDignidadOld != null && !idDignidadOld.equals(idDignidadNew)) {
                idDignidadOld.getTDignidadXUsuarioList().remove(TDignidadXUsuario);
                idDignidadOld = em.merge(idDignidadOld);
            }
            if (idDignidadNew != null && !idDignidadNew.equals(idDignidadOld)) {
                idDignidadNew.getTDignidadXUsuarioList().add(TDignidadXUsuario);
                idDignidadNew = em.merge(idDignidadNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getTDignidadXUsuarioList().remove(TDignidadXUsuario);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getTDignidadXUsuarioList().add(TDignidadXUsuario);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TDignidadXUsuario.getId();
                if (findTDignidadXUsuario(id) == null) {
                    throw new NonexistentEntityException("The tDignidadXUsuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDignidadXUsuario TDignidadXUsuario;
            try {
                TDignidadXUsuario = em.getReference(TDignidadXUsuario.class, id);
                TDignidadXUsuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TDignidadXUsuario with id " + id + " no longer exists.", enfe);
            }
            TDignidad idDignidad = TDignidadXUsuario.getIdDignidad();
            if (idDignidad != null) {
                idDignidad.getTDignidadXUsuarioList().remove(TDignidadXUsuario);
                idDignidad = em.merge(idDignidad);
            }
            TUsuario idUsuario = TDignidadXUsuario.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getTDignidadXUsuarioList().remove(TDignidadXUsuario);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(TDignidadXUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TDignidadXUsuario> findTDignidadXUsuarioEntities() {
        return findTDignidadXUsuarioEntities(true, -1, -1);
    }

    public List<TDignidadXUsuario> findTDignidadXUsuarioEntities(int maxResults, int firstResult) {
        return findTDignidadXUsuarioEntities(false, maxResults, firstResult);
    }

    private List<TDignidadXUsuario> findTDignidadXUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TDignidadXUsuario.class));
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

    public TDignidadXUsuario findTDignidadXUsuario(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TDignidadXUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getTDignidadXUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TDignidadXUsuario> rt = cq.from(TDignidadXUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
