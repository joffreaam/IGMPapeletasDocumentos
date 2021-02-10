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
import ec.gob.igm.cne.entidades.TPerfil;
import ec.gob.igm.cne.entidades.TUsuarios;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desarrollo
 */
public class TUsuariosJpaController implements Serializable {

    public TUsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TUsuarios TUsuarios) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDignidad idDignidad = TUsuarios.getIdDignidad();
            if (idDignidad != null) {
                idDignidad = em.getReference(idDignidad.getClass(), idDignidad.getCodDignidad());
                TUsuarios.setIdDignidad(idDignidad);
            }
            TPerfil idPerfil = TUsuarios.getIdPerfil();
            if (idPerfil != null) {
                idPerfil = em.getReference(idPerfil.getClass(), idPerfil.getIdperfil());
                TUsuarios.setIdPerfil(idPerfil);
            }
            em.persist(TUsuarios);
            if (idDignidad != null) {
                idDignidad.getTUsuariosList().add(TUsuarios);
                idDignidad = em.merge(idDignidad);
            }
            if (idPerfil != null) {
                idPerfil.getTUsuariosList().add(TUsuarios);
                idPerfil = em.merge(idPerfil);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTUsuarios(TUsuarios.getIdUsuario()) != null) {
                throw new PreexistingEntityException("TUsuarios " + TUsuarios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TUsuarios TUsuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TUsuarios persistentTUsuarios = em.find(TUsuarios.class, TUsuarios.getIdUsuario());
            TDignidad idDignidadOld = persistentTUsuarios.getIdDignidad();
            TDignidad idDignidadNew = TUsuarios.getIdDignidad();
            TPerfil idPerfilOld = persistentTUsuarios.getIdPerfil();
            TPerfil idPerfilNew = TUsuarios.getIdPerfil();
            if (idDignidadNew != null) {
                idDignidadNew = em.getReference(idDignidadNew.getClass(), idDignidadNew.getCodDignidad());
                TUsuarios.setIdDignidad(idDignidadNew);
            }
            if (idPerfilNew != null) {
                idPerfilNew = em.getReference(idPerfilNew.getClass(), idPerfilNew.getIdperfil());
                TUsuarios.setIdPerfil(idPerfilNew);
            }
            TUsuarios = em.merge(TUsuarios);
            if (idDignidadOld != null && !idDignidadOld.equals(idDignidadNew)) {
                idDignidadOld.getTUsuariosList().remove(TUsuarios);
                idDignidadOld = em.merge(idDignidadOld);
            }
            if (idDignidadNew != null && !idDignidadNew.equals(idDignidadOld)) {
                idDignidadNew.getTUsuariosList().add(TUsuarios);
                idDignidadNew = em.merge(idDignidadNew);
            }
            if (idPerfilOld != null && !idPerfilOld.equals(idPerfilNew)) {
                idPerfilOld.getTUsuariosList().remove(TUsuarios);
                idPerfilOld = em.merge(idPerfilOld);
            }
            if (idPerfilNew != null && !idPerfilNew.equals(idPerfilOld)) {
                idPerfilNew.getTUsuariosList().add(TUsuarios);
                idPerfilNew = em.merge(idPerfilNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TUsuarios.getIdUsuario();
                if (findTUsuarios(id) == null) {
                    throw new NonexistentEntityException("The tUsuarios with id " + id + " no longer exists.");
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
            TUsuarios TUsuarios;
            try {
                TUsuarios = em.getReference(TUsuarios.class, id);
                TUsuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TUsuarios with id " + id + " no longer exists.", enfe);
            }
            TDignidad idDignidad = TUsuarios.getIdDignidad();
            if (idDignidad != null) {
                idDignidad.getTUsuariosList().remove(TUsuarios);
                idDignidad = em.merge(idDignidad);
            }
            TPerfil idPerfil = TUsuarios.getIdPerfil();
            if (idPerfil != null) {
                idPerfil.getTUsuariosList().remove(TUsuarios);
                idPerfil = em.merge(idPerfil);
            }
            em.remove(TUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TUsuarios> findTUsuariosEntities() {
        return findTUsuariosEntities(true, -1, -1);
    }

    public List<TUsuarios> findTUsuariosEntities(int maxResults, int firstResult) {
        return findTUsuariosEntities(false, maxResults, firstResult);
    }

    private List<TUsuarios> findTUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TUsuarios.class));
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

    public TUsuarios findTUsuarios(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TUsuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getTUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TUsuarios> rt = cq.from(TUsuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
