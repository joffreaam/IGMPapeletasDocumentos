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
import ec.gob.igm.cne.entidades.TPagina;
import ec.gob.igm.cne.entidades.TPaginaperfil;
import ec.gob.igm.cne.entidades.TPaginaperfilPK;
import ec.gob.igm.cne.entidades.TPerfil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desarrollo
 */
public class TPaginaperfilJpaController implements Serializable {

    public TPaginaperfilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TPaginaperfil TPaginaperfil) throws PreexistingEntityException, Exception {
        if (TPaginaperfil.getTPaginaperfilPK() == null) {
            TPaginaperfil.setTPaginaperfilPK(new TPaginaperfilPK());
        }
        //Añadido ".longValue()"
        TPaginaperfil.getTPaginaperfilPK().setIdPerfil(TPaginaperfil.getTPerfil().getIdperfil().longValue());
        TPaginaperfil.getTPaginaperfilPK().setIdPagina(TPaginaperfil.getTPagina().getIdPagina());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TPagina TPagina = TPaginaperfil.getTPagina();
            if (TPagina != null) {
                TPagina = em.getReference(TPagina.getClass(), TPagina.getIdPagina());
                TPaginaperfil.setTPagina(TPagina);
            }
            TPerfil TPerfil = TPaginaperfil.getTPerfil();
            if (TPerfil != null) {
                TPerfil = em.getReference(TPerfil.getClass(), TPerfil.getIdperfil());
                TPaginaperfil.setTPerfil(TPerfil);
            }
            em.persist(TPaginaperfil);
            if (TPagina != null) {
                TPagina.getTPaginaperfilList().add(TPaginaperfil);
                TPagina = em.merge(TPagina);
            }
            if (TPerfil != null) {
                TPerfil.getTPaginaperfilList().add(TPaginaperfil);
                TPerfil = em.merge(TPerfil);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTPaginaperfil(TPaginaperfil.getTPaginaperfilPK()) != null) {
                throw new PreexistingEntityException("TPaginaperfil " + TPaginaperfil + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TPaginaperfil TPaginaperfil) throws NonexistentEntityException, Exception {
        //Añado la converción con ".longValue()"
        TPaginaperfil.getTPaginaperfilPK().setIdPerfil(TPaginaperfil.getTPerfil().getIdperfil().longValue());
        TPaginaperfil.getTPaginaperfilPK().setIdPagina(TPaginaperfil.getTPagina().getIdPagina());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TPaginaperfil persistentTPaginaperfil = em.find(TPaginaperfil.class, TPaginaperfil.getTPaginaperfilPK());
            TPagina TPaginaOld = persistentTPaginaperfil.getTPagina();
            TPagina TPaginaNew = TPaginaperfil.getTPagina();
            TPerfil TPerfilOld = persistentTPaginaperfil.getTPerfil();
            TPerfil TPerfilNew = TPaginaperfil.getTPerfil();
            if (TPaginaNew != null) {
                TPaginaNew = em.getReference(TPaginaNew.getClass(), TPaginaNew.getIdPagina());
                TPaginaperfil.setTPagina(TPaginaNew);
            }
            if (TPerfilNew != null) {
                TPerfilNew = em.getReference(TPerfilNew.getClass(), TPerfilNew.getIdperfil());
                TPaginaperfil.setTPerfil(TPerfilNew);
            }
            TPaginaperfil = em.merge(TPaginaperfil);
            if (TPaginaOld != null && !TPaginaOld.equals(TPaginaNew)) {
                TPaginaOld.getTPaginaperfilList().remove(TPaginaperfil);
                TPaginaOld = em.merge(TPaginaOld);
            }
            if (TPaginaNew != null && !TPaginaNew.equals(TPaginaOld)) {
                TPaginaNew.getTPaginaperfilList().add(TPaginaperfil);
                TPaginaNew = em.merge(TPaginaNew);
            }
            if (TPerfilOld != null && !TPerfilOld.equals(TPerfilNew)) {
                TPerfilOld.getTPaginaperfilList().remove(TPaginaperfil);
                TPerfilOld = em.merge(TPerfilOld);
            }
            if (TPerfilNew != null && !TPerfilNew.equals(TPerfilOld)) {
                TPerfilNew.getTPaginaperfilList().add(TPaginaperfil);
                TPerfilNew = em.merge(TPerfilNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TPaginaperfilPK id = TPaginaperfil.getTPaginaperfilPK();
                if (findTPaginaperfil(id) == null) {
                    throw new NonexistentEntityException("The tPaginaperfil with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TPaginaperfilPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TPaginaperfil TPaginaperfil;
            try {
                TPaginaperfil = em.getReference(TPaginaperfil.class, id);
                TPaginaperfil.getTPaginaperfilPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TPaginaperfil with id " + id + " no longer exists.", enfe);
            }
            TPagina TPagina = TPaginaperfil.getTPagina();
            if (TPagina != null) {
                TPagina.getTPaginaperfilList().remove(TPaginaperfil);
                TPagina = em.merge(TPagina);
            }
            TPerfil TPerfil = TPaginaperfil.getTPerfil();
            if (TPerfil != null) {
                TPerfil.getTPaginaperfilList().remove(TPaginaperfil);
                TPerfil = em.merge(TPerfil);
            }
            em.remove(TPaginaperfil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TPaginaperfil> findTPaginaperfilEntities() {
        return findTPaginaperfilEntities(true, -1, -1);
    }

    public List<TPaginaperfil> findTPaginaperfilEntities(int maxResults, int firstResult) {
        return findTPaginaperfilEntities(false, maxResults, firstResult);
    }

    private List<TPaginaperfil> findTPaginaperfilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TPaginaperfil.class));
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

    public TPaginaperfil findTPaginaperfil(TPaginaperfilPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TPaginaperfil.class, id);
        } finally {
            em.close();
        }
    }

    public int getTPaginaperfilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TPaginaperfil> rt = cq.from(TPaginaperfil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
