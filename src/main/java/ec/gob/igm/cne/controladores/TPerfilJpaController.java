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
import ec.gob.igm.cne.entidades.TUsuarios;
import java.util.ArrayList;
import java.util.List;
import ec.gob.igm.cne.entidades.TPaginaperfil;
import ec.gob.igm.cne.entidades.TPerfil;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author desarrollo
 */
public class TPerfilJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public TPerfilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TPerfil TPerfil) throws PreexistingEntityException, Exception {
        if (TPerfil.getTUsuariosList() == null) {
            TPerfil.setTUsuariosList(new ArrayList<TUsuarios>());
        }
        if (TPerfil.getTPaginaperfilList() == null) {
            TPerfil.setTPaginaperfilList(new ArrayList<TPaginaperfil>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<TUsuarios> attachedTUsuariosList = new ArrayList<TUsuarios>();
            for (TUsuarios TUsuariosListTUsuariosToAttach : TPerfil.getTUsuariosList()) {
                TUsuariosListTUsuariosToAttach = em.getReference(TUsuariosListTUsuariosToAttach.getClass(), TUsuariosListTUsuariosToAttach.getIdUsuario());
                attachedTUsuariosList.add(TUsuariosListTUsuariosToAttach);
            }
            TPerfil.setTUsuariosList(attachedTUsuariosList);
            List<TPaginaperfil> attachedTPaginaperfilList = new ArrayList<TPaginaperfil>();
            for (TPaginaperfil TPaginaperfilListTPaginaperfilToAttach : TPerfil.getTPaginaperfilList()) {
                TPaginaperfilListTPaginaperfilToAttach = em.getReference(TPaginaperfilListTPaginaperfilToAttach.getClass(), TPaginaperfilListTPaginaperfilToAttach.getTPaginaperfilPK());
                attachedTPaginaperfilList.add(TPaginaperfilListTPaginaperfilToAttach);
            }
            TPerfil.setTPaginaperfilList(attachedTPaginaperfilList);
            em.persist(TPerfil);
            for (TUsuarios TUsuariosListTUsuarios : TPerfil.getTUsuariosList()) {
                TPerfil oldIdPerfilOfTUsuariosListTUsuarios = TUsuariosListTUsuarios.getIdPerfil();
                TUsuariosListTUsuarios.setIdPerfil(TPerfil);
                TUsuariosListTUsuarios = em.merge(TUsuariosListTUsuarios);
                if (oldIdPerfilOfTUsuariosListTUsuarios != null) {
                    oldIdPerfilOfTUsuariosListTUsuarios.getTUsuariosList().remove(TUsuariosListTUsuarios);
                    oldIdPerfilOfTUsuariosListTUsuarios = em.merge(oldIdPerfilOfTUsuariosListTUsuarios);
                }
            }
            for (TPaginaperfil TPaginaperfilListTPaginaperfil : TPerfil.getTPaginaperfilList()) {
                TPerfil oldTPerfilOfTPaginaperfilListTPaginaperfil = TPaginaperfilListTPaginaperfil.getTPerfil();
                TPaginaperfilListTPaginaperfil.setTPerfil(TPerfil);
                TPaginaperfilListTPaginaperfil = em.merge(TPaginaperfilListTPaginaperfil);
                if (oldTPerfilOfTPaginaperfilListTPaginaperfil != null) {
                    oldTPerfilOfTPaginaperfilListTPaginaperfil.getTPaginaperfilList().remove(TPaginaperfilListTPaginaperfil);
                    oldTPerfilOfTPaginaperfilListTPaginaperfil = em.merge(oldTPerfilOfTPaginaperfilListTPaginaperfil);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTPerfil(TPerfil.getIdperfil()) != null) {
                throw new PreexistingEntityException("TPerfil " + TPerfil + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TPerfil TPerfil) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TPerfil persistentTPerfil = em.find(TPerfil.class, TPerfil.getIdperfil());
            List<TUsuarios> TUsuariosListOld = persistentTPerfil.getTUsuariosList();
            List<TUsuarios> TUsuariosListNew = TPerfil.getTUsuariosList();
            List<TPaginaperfil> TPaginaperfilListOld = persistentTPerfil.getTPaginaperfilList();
            List<TPaginaperfil> TPaginaperfilListNew = TPerfil.getTPaginaperfilList();
            List<String> illegalOrphanMessages = null;
            for (TPaginaperfil TPaginaperfilListOldTPaginaperfil : TPaginaperfilListOld) {
                if (!TPaginaperfilListNew.contains(TPaginaperfilListOldTPaginaperfil)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TPaginaperfil " + TPaginaperfilListOldTPaginaperfil + " since its TPerfil field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<TUsuarios> attachedTUsuariosListNew = new ArrayList<TUsuarios>();
            for (TUsuarios TUsuariosListNewTUsuariosToAttach : TUsuariosListNew) {
                TUsuariosListNewTUsuariosToAttach = em.getReference(TUsuariosListNewTUsuariosToAttach.getClass(), TUsuariosListNewTUsuariosToAttach.getIdUsuario());
                attachedTUsuariosListNew.add(TUsuariosListNewTUsuariosToAttach);
            }
            TUsuariosListNew = attachedTUsuariosListNew;
            TPerfil.setTUsuariosList(TUsuariosListNew);
            List<TPaginaperfil> attachedTPaginaperfilListNew = new ArrayList<TPaginaperfil>();
            for (TPaginaperfil TPaginaperfilListNewTPaginaperfilToAttach : TPaginaperfilListNew) {
                TPaginaperfilListNewTPaginaperfilToAttach = em.getReference(TPaginaperfilListNewTPaginaperfilToAttach.getClass(), TPaginaperfilListNewTPaginaperfilToAttach.getTPaginaperfilPK());
                attachedTPaginaperfilListNew.add(TPaginaperfilListNewTPaginaperfilToAttach);
            }
            TPaginaperfilListNew = attachedTPaginaperfilListNew;
            TPerfil.setTPaginaperfilList(TPaginaperfilListNew);
            TPerfil = em.merge(TPerfil);
            for (TUsuarios TUsuariosListOldTUsuarios : TUsuariosListOld) {
                if (!TUsuariosListNew.contains(TUsuariosListOldTUsuarios)) {
                    TUsuariosListOldTUsuarios.setIdPerfil(null);
                    TUsuariosListOldTUsuarios = em.merge(TUsuariosListOldTUsuarios);
                }
            }
            for (TUsuarios TUsuariosListNewTUsuarios : TUsuariosListNew) {
                if (!TUsuariosListOld.contains(TUsuariosListNewTUsuarios)) {
                    TPerfil oldIdPerfilOfTUsuariosListNewTUsuarios = TUsuariosListNewTUsuarios.getIdPerfil();
                    TUsuariosListNewTUsuarios.setIdPerfil(TPerfil);
                    TUsuariosListNewTUsuarios = em.merge(TUsuariosListNewTUsuarios);
                    if (oldIdPerfilOfTUsuariosListNewTUsuarios != null && !oldIdPerfilOfTUsuariosListNewTUsuarios.equals(TPerfil)) {
                        oldIdPerfilOfTUsuariosListNewTUsuarios.getTUsuariosList().remove(TUsuariosListNewTUsuarios);
                        oldIdPerfilOfTUsuariosListNewTUsuarios = em.merge(oldIdPerfilOfTUsuariosListNewTUsuarios);
                    }
                }
            }
            for (TPaginaperfil TPaginaperfilListNewTPaginaperfil : TPaginaperfilListNew) {
                if (!TPaginaperfilListOld.contains(TPaginaperfilListNewTPaginaperfil)) {
                    TPerfil oldTPerfilOfTPaginaperfilListNewTPaginaperfil = TPaginaperfilListNewTPaginaperfil.getTPerfil();
                    TPaginaperfilListNewTPaginaperfil.setTPerfil(TPerfil);
                    TPaginaperfilListNewTPaginaperfil = em.merge(TPaginaperfilListNewTPaginaperfil);
                    if (oldTPerfilOfTPaginaperfilListNewTPaginaperfil != null && !oldTPerfilOfTPaginaperfilListNewTPaginaperfil.equals(TPerfil)) {
                        oldTPerfilOfTPaginaperfilListNewTPaginaperfil.getTPaginaperfilList().remove(TPaginaperfilListNewTPaginaperfil);
                        oldTPerfilOfTPaginaperfilListNewTPaginaperfil = em.merge(oldTPerfilOfTPaginaperfilListNewTPaginaperfil);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TPerfil.getIdperfil();
                if (findTPerfil(id) == null) {
                    throw new NonexistentEntityException("The tPerfil with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TPerfil TPerfil;
            try {
                TPerfil = em.getReference(TPerfil.class, id);
                TPerfil.getIdperfil();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TPerfil with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<TPaginaperfil> TPaginaperfilListOrphanCheck = TPerfil.getTPaginaperfilList();
            for (TPaginaperfil TPaginaperfilListOrphanCheckTPaginaperfil : TPaginaperfilListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TPerfil (" + TPerfil + ") cannot be destroyed since the TPaginaperfil " + TPaginaperfilListOrphanCheckTPaginaperfil + " in its TPaginaperfilList field has a non-nullable TPerfil field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<TUsuarios> TUsuariosList = TPerfil.getTUsuariosList();
            for (TUsuarios TUsuariosListTUsuarios : TUsuariosList) {
                TUsuariosListTUsuarios.setIdPerfil(null);
                TUsuariosListTUsuarios = em.merge(TUsuariosListTUsuarios);
            }
            em.remove(TPerfil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TPerfil> findTPerfilEntities() {
        return findTPerfilEntities(true, -1, -1);
    }

    public List<TPerfil> findTPerfilEntities(int maxResults, int firstResult) {
        return findTPerfilEntities(false, maxResults, firstResult);
    }

    private List<TPerfil> findTPerfilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TPerfil.class));
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

    public TPerfil findTPerfil(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TPerfil.class, id);
        } finally {
            em.close();
        }
    }

    public int getTPerfilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TPerfil> rt = cq.from(TPerfil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
