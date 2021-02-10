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
import ec.gob.igm.cne.entidades.TPaginaperfil;
import java.util.ArrayList;
import java.util.List;
import ec.gob.igm.cne.entidades.TMenu;
import ec.gob.igm.cne.entidades.TPagina;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desarrollo
 */
public class TPaginaJpaController implements Serializable {

    public TPaginaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TPagina TPagina) throws PreexistingEntityException, Exception {
        if (TPagina.getTPaginaperfilList() == null) {
            TPagina.setTPaginaperfilList(new ArrayList<TPaginaperfil>());
        }
        if (TPagina.getTMenuList() == null) {
            TPagina.setTMenuList(new ArrayList<TMenu>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<TPaginaperfil> attachedTPaginaperfilList = new ArrayList<TPaginaperfil>();
            for (TPaginaperfil TPaginaperfilListTPaginaperfilToAttach : TPagina.getTPaginaperfilList()) {
                TPaginaperfilListTPaginaperfilToAttach = em.getReference(TPaginaperfilListTPaginaperfilToAttach.getClass(), TPaginaperfilListTPaginaperfilToAttach.getTPaginaperfilPK());
                attachedTPaginaperfilList.add(TPaginaperfilListTPaginaperfilToAttach);
            }
            TPagina.setTPaginaperfilList(attachedTPaginaperfilList);
            List<TMenu> attachedTMenuList = new ArrayList<TMenu>();
            for (TMenu TMenuListTMenuToAttach : TPagina.getTMenuList()) {
                TMenuListTMenuToAttach = em.getReference(TMenuListTMenuToAttach.getClass(), TMenuListTMenuToAttach.getIdMenu());
                attachedTMenuList.add(TMenuListTMenuToAttach);
            }
            TPagina.setTMenuList(attachedTMenuList);
            em.persist(TPagina);
            for (TPaginaperfil TPaginaperfilListTPaginaperfil : TPagina.getTPaginaperfilList()) {
                TPagina oldTPaginaOfTPaginaperfilListTPaginaperfil = TPaginaperfilListTPaginaperfil.getTPagina();
                TPaginaperfilListTPaginaperfil.setTPagina(TPagina);
                TPaginaperfilListTPaginaperfil = em.merge(TPaginaperfilListTPaginaperfil);
                if (oldTPaginaOfTPaginaperfilListTPaginaperfil != null) {
                    oldTPaginaOfTPaginaperfilListTPaginaperfil.getTPaginaperfilList().remove(TPaginaperfilListTPaginaperfil);
                    oldTPaginaOfTPaginaperfilListTPaginaperfil = em.merge(oldTPaginaOfTPaginaperfilListTPaginaperfil);
                }
            }
            for (TMenu TMenuListTMenu : TPagina.getTMenuList()) {
                TPagina oldIdPaginaOfTMenuListTMenu = TMenuListTMenu.getIdPagina();
                TMenuListTMenu.setIdPagina(TPagina);
                TMenuListTMenu = em.merge(TMenuListTMenu);
                if (oldIdPaginaOfTMenuListTMenu != null) {
                    oldIdPaginaOfTMenuListTMenu.getTMenuList().remove(TMenuListTMenu);
                    oldIdPaginaOfTMenuListTMenu = em.merge(oldIdPaginaOfTMenuListTMenu);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTPagina(TPagina.getIdPagina()) != null) {
                throw new PreexistingEntityException("TPagina " + TPagina + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TPagina TPagina) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TPagina persistentTPagina = em.find(TPagina.class, TPagina.getIdPagina());
            List<TPaginaperfil> TPaginaperfilListOld = persistentTPagina.getTPaginaperfilList();
            List<TPaginaperfil> TPaginaperfilListNew = TPagina.getTPaginaperfilList();
            List<TMenu> TMenuListOld = persistentTPagina.getTMenuList();
            List<TMenu> TMenuListNew = TPagina.getTMenuList();
            List<String> illegalOrphanMessages = null;
            for (TPaginaperfil TPaginaperfilListOldTPaginaperfil : TPaginaperfilListOld) {
                if (!TPaginaperfilListNew.contains(TPaginaperfilListOldTPaginaperfil)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TPaginaperfil " + TPaginaperfilListOldTPaginaperfil + " since its TPagina field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<TPaginaperfil> attachedTPaginaperfilListNew = new ArrayList<TPaginaperfil>();
            for (TPaginaperfil TPaginaperfilListNewTPaginaperfilToAttach : TPaginaperfilListNew) {
                TPaginaperfilListNewTPaginaperfilToAttach = em.getReference(TPaginaperfilListNewTPaginaperfilToAttach.getClass(), TPaginaperfilListNewTPaginaperfilToAttach.getTPaginaperfilPK());
                attachedTPaginaperfilListNew.add(TPaginaperfilListNewTPaginaperfilToAttach);
            }
            TPaginaperfilListNew = attachedTPaginaperfilListNew;
            TPagina.setTPaginaperfilList(TPaginaperfilListNew);
            List<TMenu> attachedTMenuListNew = new ArrayList<TMenu>();
            for (TMenu TMenuListNewTMenuToAttach : TMenuListNew) {
                TMenuListNewTMenuToAttach = em.getReference(TMenuListNewTMenuToAttach.getClass(), TMenuListNewTMenuToAttach.getIdMenu());
                attachedTMenuListNew.add(TMenuListNewTMenuToAttach);
            }
            TMenuListNew = attachedTMenuListNew;
            TPagina.setTMenuList(TMenuListNew);
            TPagina = em.merge(TPagina);
            for (TPaginaperfil TPaginaperfilListNewTPaginaperfil : TPaginaperfilListNew) {
                if (!TPaginaperfilListOld.contains(TPaginaperfilListNewTPaginaperfil)) {
                    TPagina oldTPaginaOfTPaginaperfilListNewTPaginaperfil = TPaginaperfilListNewTPaginaperfil.getTPagina();
                    TPaginaperfilListNewTPaginaperfil.setTPagina(TPagina);
                    TPaginaperfilListNewTPaginaperfil = em.merge(TPaginaperfilListNewTPaginaperfil);
                    if (oldTPaginaOfTPaginaperfilListNewTPaginaperfil != null && !oldTPaginaOfTPaginaperfilListNewTPaginaperfil.equals(TPagina)) {
                        oldTPaginaOfTPaginaperfilListNewTPaginaperfil.getTPaginaperfilList().remove(TPaginaperfilListNewTPaginaperfil);
                        oldTPaginaOfTPaginaperfilListNewTPaginaperfil = em.merge(oldTPaginaOfTPaginaperfilListNewTPaginaperfil);
                    }
                }
            }
            for (TMenu TMenuListOldTMenu : TMenuListOld) {
                if (!TMenuListNew.contains(TMenuListOldTMenu)) {
                    TMenuListOldTMenu.setIdPagina(null);
                    TMenuListOldTMenu = em.merge(TMenuListOldTMenu);
                }
            }
            for (TMenu TMenuListNewTMenu : TMenuListNew) {
                if (!TMenuListOld.contains(TMenuListNewTMenu)) {
                    TPagina oldIdPaginaOfTMenuListNewTMenu = TMenuListNewTMenu.getIdPagina();
                    TMenuListNewTMenu.setIdPagina(TPagina);
                    TMenuListNewTMenu = em.merge(TMenuListNewTMenu);
                    if (oldIdPaginaOfTMenuListNewTMenu != null && !oldIdPaginaOfTMenuListNewTMenu.equals(TPagina)) {
                        oldIdPaginaOfTMenuListNewTMenu.getTMenuList().remove(TMenuListNewTMenu);
                        oldIdPaginaOfTMenuListNewTMenu = em.merge(oldIdPaginaOfTMenuListNewTMenu);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = TPagina.getIdPagina();
                if (findTPagina(id) == null) {
                    throw new NonexistentEntityException("The tPagina with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TPagina TPagina;
            try {
                TPagina = em.getReference(TPagina.class, id);
                TPagina.getIdPagina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TPagina with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<TPaginaperfil> TPaginaperfilListOrphanCheck = TPagina.getTPaginaperfilList();
            for (TPaginaperfil TPaginaperfilListOrphanCheckTPaginaperfil : TPaginaperfilListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TPagina (" + TPagina + ") cannot be destroyed since the TPaginaperfil " + TPaginaperfilListOrphanCheckTPaginaperfil + " in its TPaginaperfilList field has a non-nullable TPagina field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<TMenu> TMenuList = TPagina.getTMenuList();
            for (TMenu TMenuListTMenu : TMenuList) {
                TMenuListTMenu.setIdPagina(null);
                TMenuListTMenu = em.merge(TMenuListTMenu);
            }
            em.remove(TPagina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TPagina> findTPaginaEntities() {
        return findTPaginaEntities(true, -1, -1);
    }

    public List<TPagina> findTPaginaEntities(int maxResults, int firstResult) {
        return findTPaginaEntities(false, maxResults, firstResult);
    }

    private List<TPagina> findTPaginaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TPagina.class));
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

    public TPagina findTPagina(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TPagina.class, id);
        } finally {
            em.close();
        }
    }

    public int getTPaginaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TPagina> rt = cq.from(TPagina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
